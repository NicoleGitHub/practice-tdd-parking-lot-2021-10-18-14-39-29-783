package com.parkinglot;

import com.parkinglot.objects.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SuperSmartParkingBoyTest {

    @Test
    void should_parked_first_parking_lot_when_park_car_given_super_smart_boy_manage_two_parking_lots_both_with_same_available_position_rate_and_car() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ArrayList<>(List.of(parkingLot1, parkingLot2)));

        //when
        Ticket ticket = superSmartParkingBoy.park(new Car());

        //then
        assertNotNull(ticket);
        assertEquals(9, parkingLot1.getAvailablePosition());
        assertEquals(10, parkingLot2.getAvailablePosition());
        assertEquals(Double.valueOf(9/parkingLot1.getCapacity()), parkingLot1.getAvailablePositionRate());
        assertEquals(Double.valueOf(10/parkingLot2.getCapacity()), parkingLot2.getAvailablePositionRate());
    }

    @Test
    void should_parked_second_parking_lot_when_park_car_given_super_smart_boy_manage_two_parking_lots_and_second_has_available_position_rate_and_car() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ArrayList<>(List.of(parkingLot1, parkingLot2)));
        while (parkingLot1.getAvailablePositionRate() >= parkingLot2.getAvailablePositionRate()) {
            superSmartParkingBoy.park(new Car());
        }

        //when
        Ticket ticket = superSmartParkingBoy.park(new Car());

        //then
        assertNotNull(ticket);
        assertEquals(9, parkingLot1.getAvailablePosition());
        assertEquals(9, parkingLot2.getAvailablePosition());
        assertEquals(Double.valueOf(9/parkingLot1.getCapacity()), parkingLot1.getAvailablePositionRate());
        assertEquals(Double.valueOf(9/parkingLot2.getCapacity()), parkingLot2.getAvailablePositionRate());
    }

}
