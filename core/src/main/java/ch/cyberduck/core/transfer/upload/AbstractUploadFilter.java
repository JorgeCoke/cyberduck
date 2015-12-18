package ch.cyberduck.core.transfer.upload;

/*
 * Copyright (c) 2002-2013 David Kocher. All rights reserved.
 * http://cyberduck.ch/
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * Bug fixes, suggestions and comments should be sent to feedback@cyberduck.ch
 */

import ch.cyberduck.core.Acl;
import ch.cyberduck.core.Local;
import ch.cyberduck.core.LocaleFactory;
import ch.cyberduck.core.MappingMimeTypeService;
import ch.cyberduck.core.MimeTypeService;
import ch.cyberduck.core.Path;
import ch.cyberduck.core.PathAttributes;
import ch.cyberduck.core.PathCache;
import ch.cyberduck.core.Permission;
import ch.cyberduck.core.ProgressListener;
import ch.cyberduck.core.Session;
import ch.cyberduck.core.UserDateFormatterFactory;
import ch.cyberduck.core.exception.AccessDeniedException;
import ch.cyberduck.core.exception.BackgroundException;
import ch.cyberduck.core.exception.NotfoundException;
import ch.cyberduck.core.features.AclPermission;
import ch.cyberduck.core.features.Attributes;
import ch.cyberduck.core.features.Delete;
import ch.cyberduck.core.features.Find;
import ch.cyberduck.core.features.Headers;
import ch.cyberduck.core.features.Move;
import ch.cyberduck.core.features.Timestamp;
import ch.cyberduck.core.features.UnixPermission;
import ch.cyberduck.core.preferences.Preferences;
import ch.cyberduck.core.preferences.PreferencesFactory;
import ch.cyberduck.core.shared.DefaultAttributesFeature;
import ch.cyberduck.core.shared.DefaultFindFeature;
import ch.cyberduck.core.transfer.TransferOptions;
import ch.cyberduck.core.transfer.TransferPathFilter;
import ch.cyberduck.core.transfer.TransferStatus;
import ch.cyberduck.core.transfer.symlink.SymlinkResolver;

import org.apache.log4j.Logger;

import java.text.MessageFormat;
import java.util.EnumSet;
import java.util.UUID;

/**
 * @version $Id$
 */
public abstract class AbstractUploadFilter implements TransferPathFilter {
    private static final Logger log = Logger.getLogger(AbstractUploadFilter.class);

    private SymlinkResolver<Local> symlinkResolver;

    private Session<?> session;

    private UploadFilterOptions options;

    protected Find find;

    protected Attributes attribute;

    private MimeTypeService mapping
            = new MappingMimeTypeService();

    private Preferences preferences
            = PreferencesFactory.get();

    public AbstractUploadFilter(final SymlinkResolver<Local> symlinkResolver, final Session<?> session,
                                final UploadFilterOptions options) {
        this.symlinkResolver = symlinkResolver;
        this.session = session;
        this.options = options;
        this.find = new DefaultFindFeature(session);
        this.attribute = new DefaultAttributesFeature(session);
    }

    @Override
    public AbstractUploadFilter withCache(final PathCache cache) {
        find.withCache(cache);
        attribute.withCache(cache);
        return this;
    }

    public AbstractUploadFilter withFinder(final Find finder) {
        this.find = finder;
        return this;
    }

    public AbstractUploadFilter withAttributes(final Attributes attribute) {
        this.attribute = attribute;
        return this;
    }

    public AbstractUploadFilter withOptions(final UploadFilterOptions options) {
        this.options = options;
        return this;
    }

    @Override
    public boolean accept(final Path file, final Local local, final TransferStatus parent) throws BackgroundException {
        if(!local.exists()) {
            // Local file is no more here
            throw new NotfoundException(local.getAbsolute());
        }
        return true;
    }

    @Override
    public TransferStatus prepare(final Path file, final Local local, final TransferStatus parent) throws BackgroundException {
        final TransferStatus status = new TransferStatus();
        // Read remote attributes first
        if(parent.isExists()) {
            if(find.find(file)) {
                status.setExists(true);
                // Read remote attributes
                final PathAttributes attributes = attribute.find(file);
                status.setRemote(attributes);
            }
            else {
                // Look if there is directory or file that clashes with this upload
                if(file.getType().contains(Path.Type.file)) {
                    if(find.find(new Path(file.getAbsolute(), EnumSet.of(Path.Type.directory), file.attributes()))) {
                        throw new AccessDeniedException(String.format("Cannot replace folder %s with file %s", file.getAbsolute(), local.getName()));
                    }
                }
                if(file.getType().contains(Path.Type.directory)) {
                    if(find.find(new Path(file.getAbsolute(), EnumSet.of(Path.Type.file), file.attributes()))) {
                        throw new AccessDeniedException(String.format("Cannot replace file %s with folder %s", file.getAbsolute(), local.getName()));
                    }
                }
            }
        }
        if(local.isFile()) {
            // Set content length from local file
            if(local.isSymbolicLink()) {
                if(!symlinkResolver.resolve(local)) {
                    // Will resolve the symbolic link when the file is requested.
                    final Local target = local.getSymlinkTarget();
                    status.setLength(target.attributes().getSize());
                }
                // No file size increase for symbolic link to be created on the server
            }
            else {
                // Read file size from filesystem
                status.setLength(local.attributes().getSize());
            }
            if(options.temporary) {
                final Path renamed = new Path(file.getParent(),
                        MessageFormat.format(preferences.getProperty("queue.upload.file.temporary.format"),
                                file.getName(), UUID.randomUUID().toString()), file.getType());
                // File attributes should not change after calculate the hash code of the file reference
                status.rename(renamed);
            }
            status.setMime(mapping.getMime(file.getName()));
        }
        if(options.permissions) {
            final Permission permission;
            if(status.isExists()) {
                permission = status.getRemote().getPermission();
            }
            else {
                if(preferences.getBoolean("queue.upload.permissions.default")) {
                    if(local.isFile()) {
                        permission = new Permission(
                                preferences.getInteger("queue.upload.permissions.file.default"));
                    }
                    else {
                        permission = new Permission(
                                preferences.getInteger("queue.upload.permissions.folder.default"));
                    }
                }
                else {
                    // Read permissions from local file
                    permission = local.attributes().getPermission();
                }
            }
            // Setting target UNIX permissions in transfer status
            status.setPermission(permission);
        }
        if(options.acl) {
            Acl acl = Acl.EMPTY;
            if(status.isExists()) {
                final AclPermission feature = session.getFeature(AclPermission.class);
                if(feature != null) {
                    acl = feature.getPermission(file);
                }
            }
            else {
                final Permission permission;
                if(preferences.getBoolean("queue.upload.permissions.default")) {
                    if(local.isFile()) {
                        permission = new Permission(
                                preferences.getInteger("queue.upload.permissions.file.default"));
                    }
                    else {
                        permission = new Permission(
                                preferences.getInteger("queue.upload.permissions.folder.default"));
                    }
                }
                else {
                    // Read permissions from local file
                    permission = local.attributes().getPermission();
                }
                acl = new Acl();
                if(permission.getOther().implies(Permission.Action.read)) {
                    acl.addAll(new Acl.GroupUser(Acl.GroupUser.EVERYONE), new Acl.Role(Acl.Role.READ));
                }
                if(permission.getGroup().implies(Permission.Action.read)) {
                    acl.addAll(new Acl.GroupUser(Acl.GroupUser.AUTHENTICATED), new Acl.Role(Acl.Role.READ));
                }
                if(permission.getGroup().implies(Permission.Action.write)) {
                    acl.addAll(new Acl.GroupUser(Acl.GroupUser.AUTHENTICATED), new Acl.Role(Acl.Role.WRITE));
                }
            }
            // Setting target ACL in transfer status
            status.setAcl(acl);
        }
        if(options.timestamp) {
            // Read timestamps from local file
            status.setTimestamp(local.attributes().getModificationDate());
        }
        if(options.metadata) {
            if(status.isExists()) {
                final Headers feature = session.getFeature(Headers.class);
                if(feature != null) {
                    status.setMetadata(feature.getMetadata(file));
                }
            }
        }
        return status;
    }

    @Override
    public void apply(final Path file, final Local local, final TransferStatus status,
                      final ProgressListener listener) throws BackgroundException {
        //
    }

    @Override
    public void complete(final Path file, final Local local,
                         final TransferOptions options, final TransferStatus status,
                         final ProgressListener listener) throws BackgroundException {
        if(log.isDebugEnabled()) {
            log.debug(String.format("Complete %s with status %s", file.getAbsolute(), status));
        }
        if(status.isComplete()) {
            if(file.isFile()) {
                if(this.options.temporary) {
                    final Move move = session.getFeature(Move.class);
                    move.move(status.getRename().remote, file, status.isExists(), new Delete.Callback() {
                        @Override
                        public void delete(final Path file) {
                            //
                        }
                    });
                }
            }
            if(!Permission.EMPTY.equals(status.getPermission())) {
                final UnixPermission feature = session.getFeature(UnixPermission.class);
                if(feature != null) {
                    try {
                        listener.message(MessageFormat.format(LocaleFactory.localizedString("Changing permission of {0} to {1}", "Status"),
                                file.getName(), status.getPermission()));
                        feature.setUnixPermission(file, status.getPermission());
                    }
                    catch(BackgroundException e) {
                        // Ignore
                        log.warn(e.getMessage());
                    }
                }
            }
            if(!Acl.EMPTY.equals(status.getAcl())) {
                final AclPermission feature = session.getFeature(AclPermission.class);
                if(feature != null) {
                    try {
                        listener.message(MessageFormat.format(LocaleFactory.localizedString("Changing permission of {0} to {1}", "Status"),
                                file.getName(), status.getAcl()));
                        feature.setPermission(file, status.getAcl());
                    }
                    catch(BackgroundException e) {
                        // Ignore
                        log.warn(e.getMessage());
                    }
                }
            }
            if(status.getTimestamp() != null) {
                final Timestamp feature = session.getFeature(Timestamp.class);
                if(feature != null) {
                    try {
                        listener.message(MessageFormat.format(LocaleFactory.localizedString("Changing timestamp of {0} to {1}", "Status"),
                                file.getName(), UserDateFormatterFactory.get().getShortFormat(status.getTimestamp())));
                        feature.setTimestamp(file, status.getTimestamp());
                    }
                    catch(BackgroundException e) {
                        // Ignore
                        log.warn(e.getMessage());
                    }
                }
            }
        }
    }
}