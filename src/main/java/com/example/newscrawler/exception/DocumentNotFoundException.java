package com.example.newscrawler.exception;

public class DocumentNotFoundException extends RuntimeException {

  public DocumentNotFoundException(String url) {
    super("Can not get document from url " + url);
  }

}
