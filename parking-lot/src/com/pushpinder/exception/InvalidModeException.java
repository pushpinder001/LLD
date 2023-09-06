package com.pushpinder.exception;

public class InvalidModeException extends Exception{
    public InvalidModeException() {
        super("Input Mode is not valid");
    }
}
