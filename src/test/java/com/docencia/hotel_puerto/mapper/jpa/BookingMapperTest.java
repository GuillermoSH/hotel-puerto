package com.docencia.hotel_puerto.mapper.jpa;

import com.docencia.hotel_puerto.domain.model.*;
import com.docencia.hotel_puerto.persistence.jpa.entity.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BookingMapperTest {

    private final BookingMapperImpl mapper = new BookingMapperImpl();

    @Test
    @DisplayName("Debe mapear BookingEntity a dominio con todas las relaciones")
    void toDomain_FullExecution() {
        HotelEntity hotelEntity = new HotelEntity();
        hotelEntity.setId(10L);
        hotelEntity.setName("Hotel Test");
        hotelEntity.setRooms(null);

        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setId(20L);
        roomEntity.setHotel(hotelEntity);

        GuestEntity guestEntity = new GuestEntity();
        guestEntity.setId(30L);

        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setId(1L);
        bookingEntity.setRoom(roomEntity);
        bookingEntity.setGuest(guestEntity);

        Booking result = mapper.toDomain(bookingEntity);

        assertNotNull(result);
        assertNotNull(result.getRoom().getHotel());
        assertNull(result.getRoom().getHotel().getRooms());
    }

    @Test
    @DisplayName("Debe mapear los Sets")
    void testSetsCoverage() {
        Room room = new Room();
        room.setId(20L);
        room.setHotel(null);

        Hotel hotel = new Hotel();
        hotel.setRooms(Collections.singleton(room));

        HotelEntity result = mapper.hotelToHotelEntity(hotel);

        assertNotNull(result.getRooms());
        assertEquals(1, result.getRooms().size());

        Set<Room> rooms = mapper.roomEntitySetToRoomSet(Collections.singleton(new RoomEntity()));

        assertNotNull(rooms);
        assertEquals(1, rooms.size());

        GuestEntity guestEntity = mapper.guestToGuestEntity(new Guest());

        assertNotNull(guestEntity);
        assertEquals(new GuestEntity(), guestEntity);
    }

    @Test
    @DisplayName("Debe cubrir todos los casos de nulos en métodos principales y secundarios")
    void nullChecks() {
        assertNull(mapper.toEntity(null));
        assertNull(mapper.toDomain(null));
        assertNull(mapper.toEntities(null));
        assertNull(mapper.toDomains(null));

        Booking bookingWithNulls = new Booking();
        bookingWithNulls.setRoom(null);
        bookingWithNulls.setGuest(null);

        BookingEntity entityWithNulls = new BookingEntity();
        entityWithNulls.setRoom(null);
        entityWithNulls.setGuest(null);

        assertNotNull(mapper.toEntity(bookingWithNulls));
        assertNotNull(mapper.toDomain(entityWithNulls));

        Hotel hotel = new Hotel();
        hotel.setRooms(null);
        HotelEntity hotelEntity = mapper.hotelToHotelEntity(hotel);
        assertNull(hotelEntity.getRooms());
    }

    @Test
    @DisplayName("Debe mapear listas de objetos")
    void listMapping_Success() {
        Booking domain = new Booking();
        domain.setId(1L);

        List<BookingEntity> entities = mapper.toEntities(List.of(domain));
        List<Booking> domains = mapper.toDomains(entities);

        assertEquals(1, entities.size());
        assertEquals(1, domains.size());
        assertEquals(1L, domains.getFirst().getId());
    }
}