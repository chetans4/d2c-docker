package com.d2c.template.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Utility class for Date operations.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class DateUtil {

  /**
   * The current date.
   */
  public static final Date NOW = new Date(System.currentTimeMillis());

  public static final Date add(Date date, Integer leadDays) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.DAY_OF_MONTH, leadDays);
    return cal.getTime();
  }

  public static final String getFormattedDate() {
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy, hh:mm:ss aa");
    Calendar cal = Calendar.getInstance();
    TimeZone zone = TimeZone.getTimeZone("UTC");
    formatter.setTimeZone(zone);
    Date date = cal.getTime();
    return formatter.format(date);
  }

}
