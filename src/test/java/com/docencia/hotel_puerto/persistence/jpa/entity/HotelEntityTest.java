package com.docencia.hotel_puerto.persistence.jpa.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class HotelEntityTest {

    @Test
    @DisplayName("Debe probar constructores, getters y setters")
    void testGettersSettersAndConstructors() {
        Set<RoomEntity> rooms = new HashSet<>();

        HotelEntity hotel = new HotelEntity(1L, "Hotel Sol", "Calle Falsa 123", rooms);

        assertEquals(1L, hotel.getId());
        assertEquals("Hotel Sol", hotel.getName());
        assertEquals("Calle Falsa 123", hotel.getAddress());
        assertEquals(rooms, hotel.getRooms());

        HotelEntity emptyHotel = new HotelEntity();
        emptyHotel.setId(2L);
        emptyHotel.setName("Hotel Luna");

        assertEquals(2L, emptyHotel.getId());
        assertEquals("Hotel Luna", emptyHotel.getName());
    }

    @Test
    @DisplayName("Debe probar el funcionamiento de equals y hashCode")
    void testEqualsAndHashCode() {
        HotelEntity hotel1 = new HotelEntity(1L);
        HotelEntity hotel2 = new HotelEntity(1L);
        HotelEntity hotel3 = new HotelEntity(2L);

        assertEquals(hotel1, hotel2);
        assertEquals(hotel1.hashCode(), hotel2.hashCode());

        assertNotEquals(hotel1, hotel3);
        assertNotEquals(hotel1.hashCode(), hotel3.hashCode());

        assertNotEquals(null, hotel1);
        assertNotEquals("no soy un hotel", hotel1);

        assertEquals(hotel1, hotel1);
    }
}