package com.example.newscrawler.exception;

public class MainElementNotFoundException extends RuntimeException {

    public MainElementNotFoundException(String url) {
        super("Can not find main element on url " + url);
    }

}
