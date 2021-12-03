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
    }
}
