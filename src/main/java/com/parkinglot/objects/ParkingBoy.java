package com.parkinglot.objects;

import java.util.ArrayList;

public class ParkingBoy {
    private ArrayList<ParkingLot> parkingLots;

    public ParkingBoy(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
//        return parkingLots.stream().reduce((parkingLot, nextParkingLot) -> parkingLot.getAvailablePosition() > 0 ? parkingLot : nextParkingLot).get().park(car);
        return parkingLots.stream().filter(parkingLot -> parkingLot.getAvailablePosition() > 0).findFirst().get().park(car);
    }

    public Car fetch(Ticket ticket) {
        return parkingLots.stream().reduce((parkingLot, nextParkingLot) -> parkingLot.validateTicket(ticket) ? parkingLot : nextParkingLot).get().fetch(ticket);
    }
}
