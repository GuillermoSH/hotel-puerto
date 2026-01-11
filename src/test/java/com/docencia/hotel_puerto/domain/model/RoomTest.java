package com.docencia.hotel_puerto.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    @DisplayName("Debe probar constructores, getters y setters de Room")
    void testGettersSettersAndConstructors() {
        Hotel hotel = new Hotel();

        Room room = new Room(1L, "101", "Individual", 50.0f, hotel);

        assertEquals(1L, room.getId());
        assertEquals("101", room.getNumber());
        assertEquals("Individual", room.getType());
        assertEquals(50.0f, room.getPricePerNight());
        assertEquals(hotel, room.getHotel());

        Room emptyRoom = new Room();
        emptyRoom.setId(2L);
        emptyRoom.setNumber("202");
        emptyRoom.setType("Doble");
        emptyRoom.setPricePerNight(85.5f);
        emptyRoom.setHotel(hotel);

        assertEquals(2L, emptyRoom.getId());
        assertEquals("202", emptyRoom.getNumber());
        assertEquals("Doble", emptyRoom.getType());
        assertEquals(85.5f, emptyRoom.getPricePerNight());
        assertEquals(hotel, emptyRoom.getHotel());

        Room idRoom = new Room(3L);
        assertEquals(3L, idRoom.getId());
    }

    @Test
    @DisplayName("Debe probar todas las ramas de equals y hashCode")
    void testEqualsAndHashCode() {
        Room room1 = new Room(1L);
        Room room2 = new Room(1L);
        Room room3 = new Room(2L);
        Room roomNullId1 = new Room(null);
        Room roomNullId2 = new Room(null);

        assertEquals(room1, room2);
        assertEquals(room1.hashCode(), room2.hashCode());

        assertEquals(room1, room1);

        assertNotEquals(room1, room3);

        assertNotEquals(null, room1);

        assertNotEquals("Objeto de otra clase", room1);

        assertEquals(roomNullId1, roomNullId2);
        assertEquals(roomNullId1.hashCode(), roomNullId2.hashCode());
    }
}