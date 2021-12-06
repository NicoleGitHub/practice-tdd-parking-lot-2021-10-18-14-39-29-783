package com.parkinglot.objects;

import com.parkinglot.exception.NoAvailablePositionException;

import java.util.ArrayList;
import java.util.Comparator;

import static com.parkinglot.exception.ErrorMessage.NO_AVAILABLE_POSITION;

public class SmartParkingBoy extends ParkingBoy{

    public SmartParkingBoy(ArrayList<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        return getParkingLots().stream()
                .max(Comparator.comparing((ParkingLot::getAvailablePositionCount)))
                .orElseThrow(() -> new NoAvailablePositionException(NO_AVAILABLE_POSITION))
                .park(car);
    }

}
