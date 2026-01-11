package com.docencia.hotel_puerto.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    @Test
    @DisplayName("Debe probar constructores, getters y setters de Booking")
    void testGettersSettersAndConstructors() {
        Room room = new Room();
        Guest guest = new Guest();

        Booking booking = new Booking(1L, "2026-05-01", "2026-05-10", room, guest);

        assertEquals(1L, booking.getId());
        assertEquals("2026-05-01", booking.getCheckIn());
        assertEquals("2026-05-10", booking.getCheckOut());
        assertEquals(room, booking.getRoom());
        assertEquals(guest, booking.getGuest());

        Booking emptyBooking = new Booking();
        emptyBooking.setId(2L);
        emptyBooking.setCheckIn("2026-06-01");
        emptyBooking.setCheckOut("2026-06-05");
        emptyBooking.setRoom(room);
        emptyBooking.setGuest(guest);

        assertEquals(2L, emptyBooking.getId());
        assertEquals("2026-06-01", emptyBooking.getCheckIn());
        assertEquals("2026-06-05", emptyBooking.getCheckOut());
        assertEquals(room, emptyBooking.getRoom());
        assertEquals(guest, emptyBooking.getGuest());

        Booking idBooking = new Booking(3L);
        assertEquals(3L, idBooking.getId());
    }

    @Test
    @DisplayName("Debe probar todas las ramas de equals y hashCode")
    void testEqualsAndHashCode() {
        Booking booking1 = new Booking(100L);
        Booking booking2 = new Booking(100L);
        Booking booking3 = new Booking(200L);
        Booking bookingNullId1 = new Booking(null);
        Booking bookingNullId2 = new Booking(null);

        assertEquals(booking1, booking2);
        assertEquals(booking1.hashCode(), booking2.hashCode());

        assertEquals(booking1, booking1);

        assertNotEquals(booking1, booking3);

        assertNotEquals(null, booking1);

        assertNotEquals("StringObject", booking1);

        assertEquals(bookingNullId1, bookingNullId2);
        assertEquals(bookingNullId1.hashCode(), bookingNullId2.hashCode());
    }
}