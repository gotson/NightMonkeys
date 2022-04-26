package com.github.gotson.nightmonkeys.webp;

public class WebpException extends Exception {
    public WebpException(String message) {
        super(message);
    }

    public WebpException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebpException(Throwable e) {
        super(e);
    }
}
