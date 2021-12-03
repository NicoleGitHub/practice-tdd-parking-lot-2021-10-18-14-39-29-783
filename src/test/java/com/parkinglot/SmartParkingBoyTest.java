package com.parkinglot;

import com.parkinglot.objects.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingBoyTest {

    @Test
    void should_parked_car_in_first_parking_lot_when_park_car_given_smart_parking_boy_with_two_equal_size_parking_lot() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ArrayList<>(List.of(parkingLot1, parkingLot2)));

        //when
        Ticket ticket = smartParkingBoy.park(new Car());

        //then
        assertNotNull(ticket);
        assertEquals(9, parkingLot1.getAvailablePosition());
        assertEquals(10, parkingLot2.getAvailablePosition());
    }

    @Test
    void should_parked_car_in_second_parking_lot_when_park_car_given_smart_parking_boy_with_two_parking_lot_with_second_parking_lot_more_empty_position() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ArrayList<>(List.of(parkingLot1, parkingLot2)));
        while (parkingLot1.getAvailablePosition() >= parkingLot2.getAvailablePosition()) {
            smartParkingBoy.park(new Car());
        }

        //when
        Ticket ticket = smartParkingBoy.park(new Car());

        //then
        assertNotNull(ticket);
        assertEquals(9, parkingLot1.getAvailablePosition());
        assertEquals(9, parkingLot2.getAvailablePosition());
    }

    @Test
    void should_return_right_car_with_ticket_when_fetch_car_twice_given_smart_parking_boy_with_two_parking_lot_both_with_parked_cars_and_two_parking_tickets() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticket1 = parkingLot1.park(car1);
        Ticket ticket2 = parkingLot2.park(car2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ArrayList<>(List.of(parkingLot1, parkingLot2)));

        //when
        Car fetchedCar1 = smartParkingBoy.fetch(ticket1);
        Car fetchedCar2 = smartParkingBoy.fetch(ticket2);

        //then
        assertEquals(fetchedCar1, car1);
        assertEquals(fetchedCar2, car2);
    }
}
