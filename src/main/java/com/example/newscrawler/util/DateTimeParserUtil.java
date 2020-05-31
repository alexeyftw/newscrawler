package com.example.newscrawler.util;

import com.example.newscrawler.exception.DateFormatUnreadableException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class DateTimeParserUtil {

  private static final String DEFAULT_TIMESTAMP = "timestamp";

  @SuppressWarnings("CheckStyle")
  public static Date parseTimestamp(String timeStamp, String inputFormat) {
    if (DEFAULT_TIMESTAMP.equals(inputFormat)) {
      Instant unixTime = Instant.ofEpochSecond(Long.parseLong(timeStamp));
      return Date.from(unixTime);
    } else {
      return formatTimeStampWithTemplate(timeStamp, inputFormat);
    }
  }

  private static Date formatTimeStampWithTemplate(String timeStamp, String inputFormat) {
    try {
      return new SimpleDateFormat(inputFormat).parse(timeStamp);
    } catch (ParseException e) {
      throw new DateFormatUnreadableException(timeStamp, inputFormat);
    }
  }

  @SuppressWarnings("CheckStyle")
  public static Date parseDate(String date, String inputFormat) {
    try {
      return new SimpleDateFormat(inputFormat).parse(date);
    } catch (ParseException e) {
      throw new DateFormatUnreadableException(date, inputFormat);
    }
  }
}
