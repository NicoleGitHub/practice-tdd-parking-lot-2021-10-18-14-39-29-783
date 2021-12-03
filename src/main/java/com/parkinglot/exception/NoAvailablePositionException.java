package com.parkinglot.exception;

public class NoAvailablePositionException extends RuntimeException {
    public NoAvailablePositionException(String message) {
        super(message);
    }
}
