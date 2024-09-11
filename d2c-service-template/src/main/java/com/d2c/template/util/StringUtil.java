package com.d2c.template.util;

import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

/**
 * Utility class for String operations.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class StringUtil {

  public static final String Y_ABBR = "Y";
  public static final String N_ABBR = "N";

  /**
   * The empty string.
   */
  public static final String EMPTY = "";

  /**
   * @param str
   * @return true - If either argument string is null or has no content after trim.
   */
  public static boolean isBlank(String str) {
    return Objects.isNull(str) || str.trim().isEmpty();
  }

  /**
   * @param str
   * @return true - If either argument string is not null or has content after trim.
   */
  public static boolean isNotBlank(String str) {
    return !isBlank(str);
  }

  /**
   * This method will decode base 64 string as normal string.
   *
   * @param base64
   * @return
   */
  public static String base64ToString(String base64) {
    byte[] imageByte = Base64.decodeBase64(base64);
    return new String(imageByte);
  }

}
