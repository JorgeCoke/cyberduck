/*
 * DRACOON
 * Version 4.4.0 - built at: 2017-12-04 04:14:43, API server: https://demo.dracoon.com/api/v4
 *
 * OpenAPI spec version: 4.4.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package ch.cyberduck.core.sds.io.swagger.client.model;

import java.util.Objects;
import ch.cyberduck.core.sds.io.swagger.client.model.NodePermissions;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * RoomGroupsAddBatchRequestItem
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-05-03T10:55:56.129+02:00")
public class RoomGroupsAddBatchRequestItem {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("newGroupMemberAcceptance")
  private String newGroupMemberAcceptance = null;

  @JsonProperty("permissions")
  private NodePermissions permissions = null;

  public RoomGroupsAddBatchRequestItem id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * Group Id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Group Id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public RoomGroupsAddBatchRequestItem newGroupMemberAcceptance(String newGroupMemberAcceptance) {
    this.newGroupMemberAcceptance = newGroupMemberAcceptance;
    return this;
  }

   /**
   * autoallow|pending; default:autoallow
   * @return newGroupMemberAcceptance
  **/
  @ApiModelProperty(value = "autoallow|pending; default:autoallow")
  public String getNewGroupMemberAcceptance() {
    return newGroupMemberAcceptance;
  }

  public void setNewGroupMemberAcceptance(String newGroupMemberAcceptance) {
    this.newGroupMemberAcceptance = newGroupMemberAcceptance;
  }

  public RoomGroupsAddBatchRequestItem permissions(NodePermissions permissions) {
    this.permissions = permissions;
    return this;
  }

   /**
   * Group permissions
   * @return permissions
  **/
  @ApiModelProperty(required = true, value = "Group permissions")
  public NodePermissions getPermissions() {
    return permissions;
  }

  public void setPermissions(NodePermissions permissions) {
    this.permissions = permissions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoomGroupsAddBatchRequestItem roomGroupsAddBatchRequestItem = (RoomGroupsAddBatchRequestItem) o;
    return Objects.equals(this.id, roomGroupsAddBatchRequestItem.id) &&
        Objects.equals(this.newGroupMemberAcceptance, roomGroupsAddBatchRequestItem.newGroupMemberAcceptance) &&
        Objects.equals(this.permissions, roomGroupsAddBatchRequestItem.permissions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, newGroupMemberAcceptance, permissions);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RoomGroupsAddBatchRequestItem {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    newGroupMemberAcceptance: ").append(toIndentedString(newGroupMemberAcceptance)).append("\n");
    sb.append("    permissions: ").append(toIndentedString(permissions)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
}

