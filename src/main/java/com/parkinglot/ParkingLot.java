package com.parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingLot {

    public static int DEFAULT_CAPACITY = 10;

    HashMap<Ticket, Car> ticketCarMap = new HashMap<>();   //   [[key, value],[key, value],[key, value] ... ]
    List<Ticket> usedTickets = new ArrayList<>();
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
        return ticketCarMap.containsKey(ticket) && !usedTickets.contains(ticket);
    }

    public Car fetch(Ticket ticket) {
        if (validateTicket(ticket)) {
            Car fetchedCar = ticketCarMap.entrySet().stream().filter(key -> key.getKey().equals(ticket)).collect(Collectors.toList()).get(0).getValue();
            ticketCarMap.remove(fetchedCar);
            usedTickets.add(ticket);
            return fetchedCar;
        }
        return null;
    }

}