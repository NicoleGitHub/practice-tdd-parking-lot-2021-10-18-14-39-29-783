package com.parkinglot;

import com.parkinglot.exception.UnrecognizedParkingTicketException;
import com.parkinglot.objects.Car;
import com.parkinglot.objects.ParkingBoy;
import com.parkinglot.objects.ParkingLot;
import com.parkinglot.objects.Ticket;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.parkinglot.exception.ErrorMessage.UNRECOGNIZED_PARKING_TICKET;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {

    @Test
    void should_return_ticket_when_park_car_given_standard_parking_boy_manage_one_parking_lot_and_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(List.of(parkingLot)));

        //when
        Ticket ticket = parkingBoy.park(new Car());

        //then
        assertNotNull(ticket);
    }

    @Test
    void should_park_to_first_parking_lot_when_park_car_given_standard_parking_boy_manage_two_parking_lots_both_available() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(List.of(parkingLot1, parkingLot2)));

        //when
        Ticket ticket = parkingBoy.park(new Car());

        //then
        assertNotNull(ticket);
        assertEquals(9, parkingLot1.getAvailablePosition());
        assertEquals(10, parkingLot2.getAvailablePosition());
    }

    @Test
    void should_park_to_second_parking_lot_when_park_car_given_standard_parking_boy_manage_two_parking_lots_both_available() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(List.of(parkingLot1, parkingLot2)));

        //when
        while (parkingLot1.getAvailablePosition() > 0) {
            parkingBoy.park(new Car());
        }
        Ticket ticket = parkingBoy.park(new Car());

        //then
        assertNotNull(ticket);
        assertEquals(0, parkingLot1.getAvailablePosition());
        assertEquals(9, parkingLot2.getAvailablePosition());
    }

    @Test
    void should_return_right_car_with_ticket_when_fetch_car_given_standard_parking_boy_manage_two_parking_lots_both_available_with_parked_car_and_two_parking_tickets() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticket1 = parkingLot1.park(car1);
        Ticket ticket2 = parkingLot2.park(car2);
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(List.of(parkingLot1, parkingLot2)));

        //when
        Car fetchedCar1 = parkingBoy.fetch(ticket1);
        Car fetchedCar2 = parkingBoy.fetch(ticket2);

        //then
        assertEquals(fetchedCar1, car1);
        assertEquals(fetchedCar2, car2);
    }

    @Test
    void should_throw_unrecognized_parking_ticket_when_fetch_car_given_standard_parking_boy_manage_two_parking_lots_both_available_and_wrong_parking_tickets() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(List.of(parkingLot1, parkingLot2)));
        Ticket wrongTicket = new Ticket();

        //when
        //then
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> {
            parkingBoy.fetch(wrongTicket);
        });
        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_throw_unrecognized_parking_ticket_when_fetch_car_given_standard_parking_boy_manage_two_parking_lots_both_available_and_used_parking_tickets() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        Car car1 = new Car();
        Ticket usedTicket = parkingLot1.park(car1);
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(List.of(parkingLot1, parkingLot2)));
        parkingLot1.fetch(usedTicket);

        //when
        //then
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> {
            parkingBoy.fetch(usedTicket);
        });
        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedParkingTicketException.getMessage());
    }

}
