package com.docencia.hotel_puerto.mapper.jpa;

import com.docencia.hotel_puerto.domain.model.Booking;
import com.docencia.hotel_puerto.domain.model.Guest;
import com.docencia.hotel_puerto.domain.model.Hotel;
import com.docencia.hotel_puerto.domain.model.Room;
import com.docencia.hotel_puerto.persistence.jpa.entity.BookingEntity;
import com.docencia.hotel_puerto.persistence.jpa.entity.GuestEntity;
import com.docencia.hotel_puerto.persistence.jpa.entity.HotelEntity;
import com.docencia.hotel_puerto.persistence.jpa.entity.RoomEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RoomMapperTest {

    private final RoomMapperImpl mapper = new RoomMapperImpl();

    @Test
    @DisplayName("Debe mapear de dominio a entidad")
    void toEntity_Success() {
        Room domain = new Room();
        domain.setId(1L);

        RoomEntity entity = mapper.toEntity(domain);

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
    }

    @Test
    @DisplayName("Debe retornar null al mapear entidad si el dominio es null")
    void toEntity_Null() {
        assertNull(mapper.toEntity(null));
    }

    @Test
    @DisplayName("Debe mapear de entidad a dominio")
    void toDomain_Success() {
        RoomEntity entity = new RoomEntity();
        entity.setId(1L);

        Room result = mapper.toDomain(entity);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    @DisplayName("Debe retornar null al mapear dominio si la entidad es null")
    void toDomain_Null() {
        assertNull(mapper.toDomain(null));
    }

    @Test
    @DisplayName("Debe mapear lista de dominios a lista de entidades")
    void toEntities_Success() {
        Room domain = new Room();
        List<RoomEntity> result = mapper.toEntities(List.of(domain));

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Debe mapear Room y su relación con Hotel")
    void toEntity_WithHotel() {
        Room room = new Room();
        room.setHotel(new Hotel());

        RoomEntity entity = mapper.toEntity(room);

        assertNotNull(entity);
        assertNotNull(entity.getHotel());
    }

    @Test
    @DisplayName("Debe retornar null si la lista de dominios es null")
    void toEntities_Null() {
        assertNull(mapper.toEntities(null));
    }

    @Test
    @DisplayName("Debe mapear lista de entidades a lista de dominios")
    void toDomains_Success() {
        RoomEntity entity = new RoomEntity();
        List<Room> result = mapper.toDomains(List.of(entity));

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Debe retornar null si la lista de entidades es null")
    void toDomains_Null() {
        assertNull(mapper.toDomains(null));
    }

    @Test
    @DisplayName("Debe mapear los Sets")
    void testSetsCoverage() {
        RoomEntity room = new RoomEntity();
        room.setId(20L);
        room.setHotel(null);

        HotelEntity hotel = new HotelEntity();
        hotel.setRooms(Collections.singleton(room));

        Hotel result = mapper.hotelEntityToHotel(hotel);

        assertNotNull(result.getRooms());
        assertEquals(1, result.getRooms().size());

        Set<RoomEntity> roomEntities = new HashSet<>();
        roomEntities.add(new RoomEntity());
        roomEntities.add(new RoomEntity());

        Set<Room> rooms = mapper.roomEntitySetToRoomSet(roomEntities);

        assertNotNull(rooms);
        assertEquals(1, rooms.size());
        assertEquals(roomEntities, mapper.roomSetToRoomEntitySet(rooms));
    }

    @Test
    @DisplayName("Debe cubrir todos los casos de nulos en métodos principales y secundarios")
    void nullChecks() {
        assertNull(mapper.toEntity(null));
        assertNull(mapper.toDomain(null));
        assertNull(mapper.toEntities(null));
        assertNull(mapper.toDomains(null));
        assertNull(mapper.roomEntitySetToRoomSet(null));

        Room roomWithNulls = new Room();
        roomWithNulls.setHotel(null);

        RoomEntity entityWithNulls = new RoomEntity();
        entityWithNulls.setHotel(null);

        assertNotNull(mapper.toEntity(roomWithNulls));
        assertNotNull(mapper.toDomain(entityWithNulls));

        Hotel hotel = new Hotel();
        hotel.setRooms(null);
        HotelEntity hotelEntity = mapper.hotelToHotelEntity(hotel);
        assertNull(hotelEntity.getRooms());
    }
}