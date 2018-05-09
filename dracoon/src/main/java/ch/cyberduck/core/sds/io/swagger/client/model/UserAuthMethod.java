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
import ch.cyberduck.core.sds.io.swagger.client.model.KeyValueEntry;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * UserAuthMethod
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-05-03T10:55:56.129+02:00")
public class UserAuthMethod {
  @JsonProperty("authId")
  private String authId = null;

  @JsonProperty("isEnabled")
  private Boolean isEnabled = null;

  @JsonProperty("options")
  private List<KeyValueEntry> options = null;

  public UserAuthMethod authId(String authId) {
    this.authId = authId;
    return this;
  }

   /**
   * Authentication method Id
   * @return authId
  **/
  @ApiModelProperty(required = true, value = "Authentication method Id")
  public String getAuthId() {
    return authId;
  }

  public void setAuthId(String authId) {
    this.authId = authId;
  }

  public UserAuthMethod isEnabled(Boolean isEnabled) {
    this.isEnabled = isEnabled;
    return this;
  }

   /**
   * Is enabled
   * @return isEnabled
  **/
  @ApiModelProperty(required = true, value = "Is enabled")
  public Boolean getIsEnabled() {
    return isEnabled;
  }

  public void setIsEnabled(Boolean isEnabled) {
    this.isEnabled = isEnabled;
  }

  public UserAuthMethod options(List<KeyValueEntry> options) {
    this.options = options;
    return this;
  }

  public UserAuthMethod addOptionsItem(KeyValueEntry optionsItem) {
    if (this.options == null) {
      this.options = new ArrayList<KeyValueEntry>();
    }
    this.options.add(optionsItem);
    return this;
  }

   /**
   * Authentication method options
   * @return options
  **/
  @ApiModelProperty(value = "Authentication method options")
  public List<KeyValueEntry> getOptions() {
    return options;
  }

  public void setOptions(List<KeyValueEntry> options) {
    this.options = options;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserAuthMethod userAuthMethod = (UserAuthMethod) o;
    return Objects.equals(this.authId, userAuthMethod.authId) &&
        Objects.equals(this.isEnabled, userAuthMethod.isEnabled) &&
        Objects.equals(this.options, userAuthMethod.options);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authId, isEnabled, options);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserAuthMethod {\n");
    
    sb.append("    authId: ").append(toIndentedString(authId)).append("\n");
    sb.append("    isEnabled: ").append(toIndentedString(isEnabled)).append("\n");
    sb.append("    options: ").append(toIndentedString(options)).append("\n");
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

