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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * UserIdFileIdItem
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-05-03T10:55:56.129+02:00")
public class UserIdFileIdItem {
  @JsonProperty("userId")
  private Long userId = null;

  @JsonProperty("fileId")
  private Long fileId = null;

  public UserIdFileIdItem userId(Long userId) {
    this.userId = userId;
    return this;
  }

   /**
   * User Id
   * @return userId
  **/
  @ApiModelProperty(value = "User Id")
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public UserIdFileIdItem fileId(Long fileId) {
    this.fileId = fileId;
    return this;
  }

   /**
   * File Id
   * @return fileId
  **/
  @ApiModelProperty(value = "File Id")
  public Long getFileId() {
    return fileId;
  }

  public void setFileId(Long fileId) {
    this.fileId = fileId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserIdFileIdItem userIdFileIdItem = (UserIdFileIdItem) o;
    return Objects.equals(this.userId, userIdFileIdItem.userId) &&
        Objects.equals(this.fileId, userIdFileIdItem.fileId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, fileId);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserIdFileIdItem {\n");
    
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    fileId: ").append(toIndentedString(fileId)).append("\n");
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

