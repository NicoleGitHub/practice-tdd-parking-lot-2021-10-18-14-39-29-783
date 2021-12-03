package com.parkinglot.objects;

import java.util.ArrayList;

public class SmartParkingBoy extends ParkingBoy{

    public SmartParkingBoy(ArrayList<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        return getParkingLots().stream().reduce((parkingLot, nextParkingLot) -> nextParkingLot.getAvailablePosition() > parkingLot.getAvailablePosition() ? nextParkingLot : parkingLot).get().park(car);
    }

}
