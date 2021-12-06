package com.parkinglot.objects;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedParkingTicketException;

import java.util.ArrayList;

import static com.parkinglot.exception.ErrorMessage.NO_AVAILABLE_POSITION;
import static com.parkinglot.exception.ErrorMessage.UNRECOGNIZED_PARKING_TICKET;

public class ParkingBoy {
    private ArrayList<ParkingLot> parkingLots;

    public ParkingBoy(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ArrayList<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public Ticket park(Car car) {
        return parkingLots.stream()
                .reduce((parkingLot, nextParkingLot) -> parkingLot.getAvailablePositionCount() > 0 ? parkingLot : nextParkingLot)
                .orElseThrow(() -> new NoAvailablePositionException(NO_AVAILABLE_POSITION))
                .park(car);
    }

    public Car fetch(Ticket ticket) {
        return parkingLots.stream()
                .reduce((parkingLot, nextParkingLot) -> parkingLot.validateTicket(ticket) ? parkingLot : nextParkingLot)
                .orElseThrow(() -> new UnrecognizedParkingTicketException(UNRECOGNIZED_PARKING_TICKET))
                .fetch(ticket);
    }
}
