package com.parkinglot;

import org.junit.jupiter.api.Test;

import static com.parkinglot.ErrorMessage.*;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    void should_return_ticket_when_park_car_given_parking_lot_and_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        //when
        Ticket ticket = parkingLot.park(car);

        //then
        assertNotNull(ticket);
    }

//    STORY 1
//    @Test
//    void should_return_null_when_park_car_given_full_parking_lot_and_car() {
//        //given
//        ParkingLot parkingLot = new ParkingLot(1);
//        Car car = new Car();
//        parkingLot.park(car);
//
//        //when
//        Ticket ticket = parkingLot.park(car);
//
//        //then
//        assertNull(ticket);
//    }

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
        assertEquals(car2, fetchedCar2);
    }

    @Test
    void should_return_nothing_when_fetch_car_given_parking_lot_and_wrong_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        parkingLot.park(car);

        //when
        Ticket wrongTicket = new Ticket();
        Car fetchedCar = parkingLot.fetch(wrongTicket);

        //then
        assertNull(fetchedCar);
    }

    @Test
    void should_return_nothing_when_fetch_car_given_parking_lot_and_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        parkingLot.park(car);
        parkingLot.fetch(car.getTicket());
        Ticket usedTicket = car.getTicket();

        //when
        Car fetchedCar = parkingLot.fetch(usedTicket);

        //then
        assertNull(fetchedCar);
    }

    @Test
    void should_throw_no_available_position_when_park_car_given_parking_lot_without_position_and_car() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car());
        Car car = new Car();

        //when
        //then
        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class, () -> { parkingLot.park(car); });
        assertEquals(NO_AVAILABLE_POSITION, noAvailablePositionException.getMessage());
    }
}
