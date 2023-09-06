package com.pushpinder.exception;

public class NoCommandFoundException extends Exception{
    public NoCommandFoundException() {
        super("No Command Found");
    }
}
