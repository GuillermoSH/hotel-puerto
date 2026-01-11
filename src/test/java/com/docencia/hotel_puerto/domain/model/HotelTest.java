package com.docencia.hotel_puerto.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class HotelTest {

    @Test
    @DisplayName("Debe probar constructores, getters y setters de Hotel")
    void testGettersSettersAndConstructors() {
        Set<Room> rooms = new HashSet<>();
        rooms.add(new Room());

        Hotel hotel = new Hotel(1L, "Hotel Puerto", "Calle Real 123", rooms);

        assertEquals(1L, hotel.getId());
        assertEquals("Hotel Puerto", hotel.getName());
        assertEquals("Calle Real 123", hotel.getAddress());
        assertEquals(rooms, hotel.getRooms());
        assertEquals(1, hotel.getRooms().size());

        Hotel emptyHotel = new Hotel();
        emptyHotel.setId(2L);
        emptyHotel.setName("Hotel Playa");
        emptyHotel.setAddress("Avenida Maritima 1");

        Set<Room> newRooms = new HashSet<>();
        emptyHotel.setRooms(newRooms);

        assertEquals(2L, emptyHotel.getId());
        assertEquals("Hotel Playa", emptyHotel.getName());
        assertEquals("Avenida Maritima 1", emptyHotel.getAddress());
        assertEquals(newRooms, emptyHotel.getRooms());

        Hotel idHotel = new Hotel(3L);
        assertEquals(3L, idHotel.getId());
    }

    @Test
    @DisplayName("Debe probar todas las ramas de equals y hashCode")
    void testEqualsAndHashCode() {
        Hotel hotel1 = new Hotel(1L);
        Hotel hotel2 = new Hotel(1L);
        Hotel hotel3 = new Hotel(2L);

        assertEquals(hotel1, hotel2);
        assertEquals(hotel1.hashCode(), hotel2.hashCode());

        assertEquals(hotel1, hotel1);

        assertNotEquals(hotel1, hotel3);
        if (hotel1.getId() != null && hotel3.getId() != null) {
            assertNotEquals(hotel1.hashCode(), hotel3.hashCode());
        }

        assertNotEquals(null, hotel1);

        assertNotEquals("No soy un hotel", hotel1);

        Hotel nullIdHotel1 = new Hotel(null);
        Hotel nullIdHotel2 = new Hotel(null);
        assertEquals(nullIdHotel1, nullIdHotel2);
        assertEquals(nullIdHotel1.hashCode(), nullIdHotel2.hashCode());
    }
}