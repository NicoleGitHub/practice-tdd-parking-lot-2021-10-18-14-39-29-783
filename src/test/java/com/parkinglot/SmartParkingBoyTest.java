package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedParkingTicketException;
import com.parkinglot.objects.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.parkinglot.exception.ErrorMessage.NO_AVAILABLE_POSITION;
import static com.parkinglot.exception.ErrorMessage.UNRECOGNIZED_PARKING_TICKET;
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
        assertAll(
            () -> assertNotNull(ticket),
            () -> assertEquals(9, parkingLot1.getAvailablePositionCount()),
            () -> assertEquals(10, parkingLot2.getAvailablePositionCount())
        );

    }

    @Test
    void should_parked_car_in_second_parking_lot_when_park_car_given_smart_parking_boy_with_two_parking_lot_with_second_parking_lot_more_empty_position() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ArrayList<>(List.of(parkingLot1, parkingLot2)));
        while (parkingLot1.getAvailablePositionCount() >= parkingLot2.getAvailablePositionCount()) {
            smartParkingBoy.park(new Car());
        }

        //when
        Ticket ticket = smartParkingBoy.park(new Car());

        //then
        assertAll(
            () -> assertNotNull(ticket),
            () -> assertEquals(9, parkingLot1.getAvailablePositionCount()),
            () -> assertEquals(9, parkingLot2.getAvailablePositionCount())
        );

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
        assertAll(
            () -> assertEquals(fetchedCar1, car1),
            () -> assertEquals(fetchedCar2, car2)
        );

    }

    @Test
    void should_throw_unrecognized_parking_ticket_when_fetch_car_given_smart_parking_boy_manage_two_parking_lots_both_available_and_wrong_parking_tickets() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ArrayList<>(List.of(parkingLot1, parkingLot2)));
        Ticket wrongTicket = new Ticket();

        //when
        //then
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> {
            smartParkingBoy.fetch(wrongTicket);
        });
        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_throw_unrecognized_parking_ticket_when_fetch_car_given_smart_parking_boy_manage_two_parking_lots_both_available_and_used_parking_tickets() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        Car car1 = new Car();
        Ticket usedTicket = parkingLot1.park(car1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ArrayList<>(List.of(parkingLot1, parkingLot2)));
        parkingLot1.fetch(usedTicket);

        //when
        //then
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> {
            smartParkingBoy.fetch(usedTicket);
        });
        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_no_person_available_when_park_car_given_smart_parking_boy_manage_two_parking_lots_both_unavailable_and_full() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ArrayList<>(List.of(parkingLot1, parkingLot2)));
        while (parkingLot1.getAvailablePositionCount() > 0 || parkingLot2.getAvailablePositionCount() > 0) {
            smartParkingBoy.park(new Car());
        }

        //when
        //then
        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class, () -> {
            smartParkingBoy.park(new Car());
        });

        assertAll(
            () -> assertEquals(NO_AVAILABLE_POSITION, noAvailablePositionException.getMessage()),
            () -> assertEquals(0, parkingLot1.getAvailablePositionCount()),
            () -> assertEquals(0, parkingLot2.getAvailablePositionCount())
        );

    }
}
