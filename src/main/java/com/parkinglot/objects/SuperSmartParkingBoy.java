package com.parkinglot.objects;

import java.util.ArrayList;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(ArrayList<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        return getParkingLots().stream().reduce((parkingLot, nextParkingLot) -> nextParkingLot.getAvailablePositionRate() > parkingLot.getAvailablePositionRate() ? nextParkingLot : parkingLot).get().park(car);
    }
}
