package com.parkinglot.exception;

public class UnrecognizedParkingTicketException extends RuntimeException {
    public UnrecognizedParkingTicketException(String message) {
        super(message);
    }
}
