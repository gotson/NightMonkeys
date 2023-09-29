package com.github.gotson.nightmonkeys.heif;

public class HeifException extends Exception {
    public HeifException(String message) {
        super(message);
    }

    public HeifException(String message, Throwable cause) {
        super(message, cause);
    }

    public HeifException(Throwable e) {
        super(e);
    }
}
