package com.d2c.template.dto.response;

import com.d2c.template.util.StringUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponseDto implements Serializable {

  protected Integer code;
  protected String message;
  protected HashMap<String, String> errors;
  protected String apiName;
  protected String version;

  /**
   * Take First argument as API Name and Second as Version
   *
   * @param apiNameAndVersion
   * @return
   */
  public static StatusResponseDto successStatus(String... apiNameAndVersion) {
    return new StatusResponseDto(200, "Request Processed Successfully.", null,
        getApiName(apiNameAndVersion), getVersion(apiNameAndVersion));
  }

  private static String getApiName(String[] apiNameAndVersion) {
    return Objects.nonNull(apiNameAndVersion) && apiNameAndVersion.length >= 1
        ? apiNameAndVersion[0] : null;
  }

  private static String getVersion(String[] apiNameAndVersion) {
    return Objects.nonNull(apiNameAndVersion) && apiNameAndVersion.length >= 2
        ? apiNameAndVersion[1] : null;
  }


  public static StatusResponseDto errorStatus(Integer errorCode, String message,
      String... apiNameAndVersion) {
    return new StatusResponseDto(errorCode,
        StringUtil.isBlank(message) ? "Oops! Something went wrong." : message, null,
        getApiName(apiNameAndVersion), getVersion(apiNameAndVersion));
  }

  public static StatusResponseDto errorStatus(Integer errorCode, String message,
      Map<String, String> errors, String... apiNameAndVersion) {
    return new StatusResponseDto(errorCode,
        StringUtil.isBlank(message) ? "Oops! Something went wrong." : message,
        (HashMap<String, String>) errors,
        getApiName(apiNameAndVersion), getVersion(apiNameAndVersion));
  }
}
