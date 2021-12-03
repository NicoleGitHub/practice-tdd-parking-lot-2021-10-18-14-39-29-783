package com.parkinglot;

import com.parkinglot.objects.Car;
import com.parkinglot.objects.ParkingBoy;
import com.parkinglot.objects.ParkingLot;
import com.parkinglot.objects.Ticket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingBoyTest {
    @Test
    void should_return_ticket_when_park_car_given_standard_parking_boy_manage_one_parking_lot_and_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        //when
        Ticket ticket = parkingBoy.park(new Car());

        //then
        assertNotNull(ticket);
    }

}
