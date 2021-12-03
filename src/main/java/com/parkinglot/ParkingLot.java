package com.parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParkingLot {

    public static int DEFAULT_CAPACITY = 10;

    HashMap<Ticket, Car> ticketCarMap = new HashMap<>();
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
            Car fetchedCar = ticketCarMap.entrySet().stream().filter(key -> key.getKey().equals(ticket)).findFirst().get().getValue();
            ticketCarMap.remove(fetchedCar);
            usedTickets.add(ticket);
            return fetchedCar;
        }
        return null;
    }

}