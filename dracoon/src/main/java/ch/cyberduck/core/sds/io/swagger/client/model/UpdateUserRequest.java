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
import ch.cyberduck.core.sds.io.swagger.client.model.ObjectExpiration;
import ch.cyberduck.core.sds.io.swagger.client.model.UserAuthMethod;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * UpdateUserRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-05-03T10:55:56.129+02:00")
public class UpdateUserRequest {
  @JsonProperty("title")
  private String title = null;

  @JsonProperty("firstName")
  private String firstName = null;

  @JsonProperty("lastName")
  private String lastName = null;

  /**
   * Gender key, one of [m|f|n]
   */
  public enum GenderEnum {
    M("m"),
    
    F("f"),
    
    N("n");

    private String value;

    GenderEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static GenderEnum fromValue(String text) {
      for (GenderEnum b : GenderEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("gender")
  private GenderEnum gender = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("lockStatus")
  private Integer lockStatus = null;

  @JsonProperty("expiration")
  private ObjectExpiration expiration = null;

  @JsonProperty("authMethods")
  private List<UserAuthMethod> authMethods = null;

  public UpdateUserRequest title(String title) {
    this.title = title;
    return this;
  }

   /**
   * Job title
   * @return title
  **/
  @ApiModelProperty(value = "Job title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public UpdateUserRequest firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

   /**
   * First name
   * @return firstName
  **/
  @ApiModelProperty(value = "First name")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public UpdateUserRequest lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

   /**
   * Last name
   * @return lastName
  **/
  @ApiModelProperty(value = "Last name")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public UpdateUserRequest gender(GenderEnum gender) {
    this.gender = gender;
    return this;
  }

   /**
   * Gender key, one of [m|f|n]
   * @return gender
  **/
  @ApiModelProperty(value = "Gender key, one of [m|f|n]")
  public GenderEnum getGender() {
    return gender;
  }

  public void setGender(GenderEnum gender) {
    this.gender = gender;
  }

  public UpdateUserRequest email(String email) {
    this.email = email;
    return this;
  }

   /**
   * Email [NOT USED]
   * @return email
  **/
  @ApiModelProperty(value = "Email [NOT USED]")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UpdateUserRequest lockStatus(Integer lockStatus) {
    this.lockStatus = lockStatus;
    return this;
  }

   /**
   * Lock status: 0 - Locked, 1 - Web access allowed, 2 - Web and mobile access allowed
   * @return lockStatus
  **/
  @ApiModelProperty(value = "Lock status: 0 - Locked, 1 - Web access allowed, 2 - Web and mobile access allowed")
  public Integer getLockStatus() {
    return lockStatus;
  }

  public void setLockStatus(Integer lockStatus) {
    this.lockStatus = lockStatus;
  }

  public UpdateUserRequest expiration(ObjectExpiration expiration) {
    this.expiration = expiration;
    return this;
  }

   /**
   * User expiration. &lt;b&gt;For a user with last role, no expiration date can be set&lt;/b&gt;
   * @return expiration
  **/
  @ApiModelProperty(value = "User expiration. <b>For a user with last role, no expiration date can be set</b>")
  public ObjectExpiration getExpiration() {
    return expiration;
  }

  public void setExpiration(ObjectExpiration expiration) {
    this.expiration = expiration;
  }

  public UpdateUserRequest authMethods(List<UserAuthMethod> authMethods) {
    this.authMethods = authMethods;
    return this;
  }

  public UpdateUserRequest addAuthMethodsItem(UserAuthMethod authMethodsItem) {
    if (this.authMethods == null) {
      this.authMethods = new ArrayList<UserAuthMethod>();
    }
    this.authMethods.add(authMethodsItem);
    return this;
  }

   /**
   * Authentication methods
   * @return authMethods
  **/
  @ApiModelProperty(value = "Authentication methods")
  public List<UserAuthMethod> getAuthMethods() {
    return authMethods;
  }

  public void setAuthMethods(List<UserAuthMethod> authMethods) {
    this.authMethods = authMethods;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateUserRequest updateUserRequest = (UpdateUserRequest) o;
    return Objects.equals(this.title, updateUserRequest.title) &&
        Objects.equals(this.firstName, updateUserRequest.firstName) &&
        Objects.equals(this.lastName, updateUserRequest.lastName) &&
        Objects.equals(this.gender, updateUserRequest.gender) &&
        Objects.equals(this.email, updateUserRequest.email) &&
        Objects.equals(this.lockStatus, updateUserRequest.lockStatus) &&
        Objects.equals(this.expiration, updateUserRequest.expiration) &&
        Objects.equals(this.authMethods, updateUserRequest.authMethods);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, firstName, lastName, gender, email, lockStatus, expiration, authMethods);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateUserRequest {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    lockStatus: ").append(toIndentedString(lockStatus)).append("\n");
    sb.append("    expiration: ").append(toIndentedString(expiration)).append("\n");
    sb.append("    authMethods: ").append(toIndentedString(authMethods)).append("\n");
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

