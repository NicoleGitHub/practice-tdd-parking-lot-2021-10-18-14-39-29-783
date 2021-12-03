package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    // Case 1 - Given a parking lot, and a car, When park the car, Then return a parking ticket

    @Test
    void should_return_ticket_when_park_car_given_parking_lot_and_car_() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        //when
        Ticket ticket = parkingLot.park(car);

        //then
        assertNotNull(ticket);
    }

    // Case 2 - Given a parking lot without any position, and a car, When park the car, Then return nothing

    @Test
    void should_return_null_when_park_car_given_full_parking_lot_and_car_() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        parkingLot.park(car); // full parkingLot

        //when
        Ticket ticket = parkingLot.park(car);

        //then
        assertNull(ticket);
    }

    @Test
    void should_return_parked_car_when_fetch_car_given_parking_lot_and_parked_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        parkingLot.park(car);

        //when
        Car fetchedCar = parkingLot.fetch(car.getTicket());

        //then
        assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_right_car_with_right_ticket_when_fetch_twice_given_two_parked_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car1 = new Car();
        Car car2 = new Car();
        parkingLot.park(car1);
        parkingLot.park(car2);

        //when
        Car fetchedCar1 = parkingLot.fetch(car1.getTicket());
        Car fetchedCar2 = parkingLot.fetch(car2.getTicket());

        //then
        assertEquals(car1, fetchedCar1);
        assertEquals(car1, fetchedCar2);
    }
}
