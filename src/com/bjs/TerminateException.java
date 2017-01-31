package com.bjs;

/**
 * Created by U-1 on 30.01.2017.
 */
public class TerminateException extends Exception {
    private String message;

    public TerminateException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
