package com.docencia.hotel_puerto.persistence.jpa.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookingEntityTest {

    @Test
    @DisplayName("Debe probar constructores, getters y setters")
    void testGettersSettersAndConstructors() {
        RoomEntity room = new RoomEntity();
        GuestEntity guest = new GuestEntity();

        BookingEntity booking = new BookingEntity(1L, "2026-01-01", "2026-01-10", room, guest);

        assertEquals(1L, booking.getId());
        assertEquals("2026-01-01", booking.getCheckIn());
        assertEquals("2026-01-10", booking.getCheckOut());
        assertEquals(room, booking.getRoom());
        assertEquals(guest, booking.getGuest());

        BookingEntity emptyBooking = new BookingEntity();
        emptyBooking.setId(2L);
        emptyBooking.setCheckIn("2026-02-01");

        assertEquals(2L, emptyBooking.getId());
        assertEquals("2026-02-01", emptyBooking.getCheckIn());
    }

    @Test
    @DisplayName("Debe probar el funcionamiento de equals y hashCode")
    void testEqualsAndHashCode() {
        BookingEntity booking1 = new BookingEntity(1L);
        BookingEntity booking2 = new BookingEntity(1L);
        BookingEntity booking3 = new BookingEntity(2L);

        assertEquals(booking1, booking2);
        assertEquals(booking1.hashCode(), booking2.hashCode());

        assertEquals(booking1, booking1);

        assertNotEquals(booking1, booking3);
        assertNotEquals(booking1.hashCode(), booking3.hashCode());

        assertNotEquals(null, booking1);
        assertNotEquals("no soy una reserva", booking1);
    }
}