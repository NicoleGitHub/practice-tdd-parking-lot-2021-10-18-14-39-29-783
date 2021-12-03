package com.parkinglot.objects;

import java.util.ArrayList;

public class SmartParkingBoy {

    private ArrayList<ParkingLot> parkingLots;

    public SmartParkingBoy(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        return parkingLots.stream().reduce((parkingLot, nextParkingLot) -> nextParkingLot.getAvailablePosition() > parkingLot.getAvailablePosition() ? nextParkingLot : parkingLot).get().park(car);
    }

    public Car fetch(Ticket ticket1) {
        return null;
    }
}
