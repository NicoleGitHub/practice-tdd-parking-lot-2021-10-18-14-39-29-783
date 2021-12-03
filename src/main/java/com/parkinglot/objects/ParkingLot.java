package com.parkinglot.objects;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedParkingTicketException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.parkinglot.exception.ErrorMessage.*;

public class ParkingLot {

    public static int DEFAULT_CAPACITY = 10;

    private HashMap<Ticket, Car> ticketCarMap = new HashMap<>();
    private List<Ticket> usedTickets = new ArrayList<>();
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
        throw new NoAvailablePositionException(NO_AVAILABLE_POSITION);
    }

    private boolean hasAvailablePosition() {
        return ticketCarMap.size() < capacity;
    }

    private boolean validateTicket(Ticket ticket) {
        return ticketCarMap.containsKey(ticket) && !usedTickets.contains(ticket);
    }

    public Car fetch(Ticket ticket) {
        if (validateTicket(ticket)) {
            Car fetchedCar = ticketCarMap.get(ticket);
            ticketCarMap.remove(fetchedCar);
            usedTickets.add(ticket);
            return fetchedCar;
        }
        throw new UnrecognizedParkingTicketException(UNRECOGNIZED_PARKING_TICKET);
    }

    public int getAvailablePosition() {
        return capacity - ticketCarMap.size();
    }
}