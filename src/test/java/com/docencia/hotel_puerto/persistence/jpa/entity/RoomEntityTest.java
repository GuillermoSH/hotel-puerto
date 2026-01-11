package com.docencia.hotel_puerto.persistence.jpa.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomEntityTest {

    @Test
    @DisplayName("Debe probar constructores, getters y setters de RoomEntity")
    void testGettersSettersAndConstructors() {
        HotelEntity hotel = new HotelEntity();

        RoomEntity room = new RoomEntity(1L, "101", "Suite", 150.50f, hotel);

        assertEquals(1L, room.getId());
        assertEquals("101", room.getNumber());
        assertEquals("Suite", room.getType());
        assertEquals(150.50f, room.getPricePerNight());
        assertEquals(hotel, room.getHotel());

        RoomEntity emptyRoom = new RoomEntity();
        emptyRoom.setId(2L);
        emptyRoom.setNumber("202");
        emptyRoom.setType("Double");
        emptyRoom.setPricePerNight(85.0f);

        assertEquals(2L, emptyRoom.getId());
        assertEquals("202", emptyRoom.getNumber());
        assertEquals("Double", emptyRoom.getType());
        assertEquals(85.0f, emptyRoom.getPricePerNight());

        RoomEntity idRoom = new RoomEntity(3L);
        assertEquals(3L, idRoom.getId());
    }

    @Test
    @DisplayName("Debe probar el funcionamiento de equals y hashCode basados en ID")
    void testEqualsAndHashCode() {
        RoomEntity room1 = new RoomEntity(1L);
        RoomEntity room2 = new RoomEntity(1L);
        RoomEntity room3 = new RoomEntity(2L);

        assertEquals(room1, room2);
        assertEquals(room1.hashCode(), room2.hashCode());

        assertEquals(room1, room1);

        assertNotEquals(room1, room3);
        assertNotEquals(room1.hashCode(), room3.hashCode());

        assertNotEquals(null, room1);
        assertNotEquals(new Object(), room1);
    }
}