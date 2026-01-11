package com.docencia.hotel_puerto.mapper.jpa;

import com.docencia.hotel_puerto.domain.model.Hotel;
import com.docencia.hotel_puerto.domain.model.Room;
import com.docencia.hotel_puerto.persistence.jpa.entity.HotelEntity;
import com.docencia.hotel_puerto.persistence.jpa.entity.RoomEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class HotelMapperTest {

    private final HotelMapperImpl mapper = new HotelMapperImpl();

    @Test
    @DisplayName("Debe mapear de dominio a entidad")
    void toEntity_Success() {
        Hotel domain = new Hotel();
        domain.setId(1L);

        HotelEntity entity = mapper.toEntity(domain);

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
        HotelEntity entity = new HotelEntity();
        entity.setId(1L);

        Hotel result = mapper.toDomain(entity);

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
        Hotel domain = new Hotel();
        List<HotelEntity> result = mapper.toEntities(List.of(domain));

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Debe retornar null si la lista de dominios es null")
    void toEntities_Null() {
        assertNull(mapper.toEntities(null));
    }

    @Test
    @DisplayName("Debe mapear Hotel y su set de habitaciones")
    void toEntity_WithRooms() {
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        hotel.setRooms(Set.of(new Room()));

        HotelEntity entity = mapper.toEntity(hotel);

        assertNotNull(entity);
        assertNotNull(entity.getRooms());
        assertFalse(entity.getRooms().isEmpty());
    }

    @Test
    @DisplayName("Debe mapear lista de entidades a lista de dominios")
    void toDomains_Success() {
        HotelEntity entity = new HotelEntity();
        List<Hotel> result = mapper.toDomains(List.of(entity));

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
        assertNotNull(mapper.roomEntityToRoom(new RoomEntity()));

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
        assertNull(mapper.roomToRoomEntity(null));
        assertNull(mapper.roomEntityToRoom(null));

        Hotel hotelWithNulls = new Hotel();
        hotelWithNulls.setRooms(null);

        HotelEntity entityWithNulls = new HotelEntity();
        entityWithNulls.setRooms(null);

        assertNotNull(mapper.toEntity(hotelWithNulls));
        assertNotNull(mapper.toDomain(entityWithNulls));
    }
}