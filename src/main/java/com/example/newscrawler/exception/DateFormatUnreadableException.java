package com.example.newscrawler.exception;

public class DateFormatUnreadableException extends RuntimeException {

  public DateFormatUnreadableException(String date, String format) {
    super("Can not parse date: " + date + " with format: " + format);
  }

}
