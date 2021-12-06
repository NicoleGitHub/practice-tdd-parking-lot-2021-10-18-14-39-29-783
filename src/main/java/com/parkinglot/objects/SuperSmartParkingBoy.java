package com.parkinglot.objects;

import com.parkinglot.exception.NoAvailablePositionException;

import java.util.ArrayList;
import java.util.Comparator;

import static com.parkinglot.exception.ErrorMessage.NO_AVAILABLE_POSITION;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(ArrayList<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        return getParkingLots().stream()
                .max(Comparator.comparing((ParkingLot::getAvailablePositionRate)))
                .orElseThrow(() -> new NoAvailablePositionException(NO_AVAILABLE_POSITION))
                .park(car);
    }

}
