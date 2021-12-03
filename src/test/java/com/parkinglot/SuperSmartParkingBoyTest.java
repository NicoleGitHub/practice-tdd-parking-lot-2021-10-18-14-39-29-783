package com.parkinglot;

import com.parkinglot.exception.UnrecognizedParkingTicketException;
import com.parkinglot.objects.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.parkinglot.exception.ErrorMessage.UNRECOGNIZED_PARKING_TICKET;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void should_return_right_car_with_ticket_when_fetch_car_twice_given_super_smart_parking_boy_with_two_parking_lot_both_with_parked_cars_and_two_parking_tickets() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticket1 = parkingLot1.park(car1);
        Ticket ticket2 = parkingLot2.park(car2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ArrayList<>(List.of(parkingLot1, parkingLot2)));

        //when
        Car fetchedCar1 = superSmartParkingBoy.fetch(ticket1);
        Car fetchedCar2 = superSmartParkingBoy.fetch(ticket2);

        //then
        assertEquals(fetchedCar1, car1);
        assertEquals(fetchedCar2, car2);
    }

    @Test
    void should_throw_unrecognized_parking_ticket_when_fetch_car_given_smart_parking_boy_manage_two_parking_lots_both_available_and_wrong_parking_tickets() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ArrayList<>(List.of(parkingLot1, parkingLot2)));
        Ticket wrongTicket = new Ticket();

        //when
        //then
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> {
            superSmartParkingBoy.fetch(wrongTicket);
        });
        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedParkingTicketException.getMessage());
    }




}
