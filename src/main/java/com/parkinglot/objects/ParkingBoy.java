package com.parkinglot.objects;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    private ArrayList<ParkingLot> parkingLots;

    public ParkingBoy(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        return parkingLots.stream().filter(parkingLot -> parkingLot.getAvailablePosition() > 0).findFirst().get().park(car);
    }

    public Car fetch(Ticket ticket) {
        return new Car();
    }
}
