package com.parkinglot;

import java.util.HashMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ParkingLot {

    public static int DEFAULT_CAPACITY = 10;

    HashMap<Ticket, Car> ticketCarMap = new HashMap<>();   //   [[key, value],[key, value],[key, value] ... ]
    private int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingLot() {
        this.capacity = DEFAULT_CAPACITY;
    }

    public Ticket park(Car car) {
        if (hasAvailablePosition()) {
            Ticket ticket = new Ticket();
            car.setTicket(ticket);
            ticketCarMap.put(ticket, car);

            return ticket;
        }
        return null;
    }

    private boolean hasAvailablePosition() {
        return ticketCarMap.size() < capacity;
    }

    private boolean validateTicket(Ticket ticket) {
        return ticketCarMap.containsKey(ticket);
    }

    public Car fetch(Ticket ticket) {
        return validateTicket(ticket)
                ? ticketCarMap.entrySet().stream().filter(key -> key.getKey().equals(ticket)).collect(Collectors.toList()).get(0).getValue()
                : null;
    }
}