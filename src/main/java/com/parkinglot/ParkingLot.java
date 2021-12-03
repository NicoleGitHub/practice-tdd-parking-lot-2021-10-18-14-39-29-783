package com.parkinglot;

import java.util.HashMap;

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
            ticketCarMap.put(ticket, car);

            return ticket;
        }
        return null;
    }

    private boolean hasAvailablePosition() {
        return ticketCarMap.size() < capacity;
    }

    public Car fetch(Ticket ticket) {
        return new Car();
    }
}