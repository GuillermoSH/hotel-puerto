package com.docencia.hotel_puerto.service.impl;

import com.docencia.hotel_puerto.domain.model.Room;
import com.docencia.hotel_puerto.mapper.jpa.RoomMapper;
import com.docencia.hotel_puerto.persistence.jpa.entity.RoomEntity;
import com.docencia.hotel_puerto.persistence.repository.jpa.RoomJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomServiceImplTest {

    @Mock
    private RoomJpaRepository roomRepository;

    @Mock
    private RoomMapper roomMapper;

    @InjectMocks
    private RoomServiceImpl roomService;

    private Room room;
    private RoomEntity roomEntity;

    @BeforeEach
    void setUp() {
        room = new Room();
        roomEntity = new RoomEntity();
        roomEntity.setId(1L);
    }

    @Test
    @DisplayName("Debe retornar true si la habitación existe")
    void exists_ReturnsTrue() {
        when(roomRepository.existsById(1L)).thenReturn(true);
        boolean result = roomService.exists(1L);
        assertTrue(result);
    }

    @Test
    @DisplayName("Debe retornar la habitación cuando el ID existe")
    void findById_Success() {
        when(roomRepository.findById(1L)).thenReturn(Optional.of(roomEntity));
        when(roomMapper.toDomain(roomEntity)).thenReturn(room);

        Room result = roomService.findById(1L);

        assertNotNull(result);
        assertEquals(room, result);
    }

    @Test
    @DisplayName("Debe lanzar IllegalArgumentException cuando la habitación no existe")
    void findById_ThrowsException() {
        when(roomRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> roomService.findById(1L));
    }

    @Test
    @DisplayName("Debe retornar la lista de todas las habitaciones")
    void findAll_Success() {
        List<RoomEntity> entities = List.of(roomEntity);
        List<Room> domains = List.of(room);
        when(roomRepository.findAll()).thenReturn(entities);
        when(roomMapper.toDomains(entities)).thenReturn(domains);

        List<Room> result = roomService.findAll();

        assertEquals(1, result.size());
        assertEquals(room, result.get(0));
    }

    @Test
    @DisplayName("Debe persistir y retornar la habitación guardada")
    void save_Success() {
        when(roomMapper.toEntity(any(Room.class))).thenReturn(roomEntity);
        when(roomRepository.save(any(RoomEntity.class))).thenReturn(roomEntity);
        when(roomMapper.toDomain(roomEntity)).thenReturn(room);

        Room result = roomService.save(room);

        assertNotNull(result);
        verify(roomRepository).save(roomEntity);
    }

    @Test
    @DisplayName("Debe retornar true si la habitación se elimina correctamente")
    void delete_Success() {
        doNothing().when(roomRepository).deleteById(1L);
        when(roomRepository.existsById(1L)).thenReturn(false);

        boolean result = roomService.delete(1L);

        assertTrue(result);
    }

    @Test
    @DisplayName("Debe retornar false si la habitación sigue existiendo tras el borrado")
    void delete_Failure() {
        doNothing().when(roomRepository).deleteById(1L);
        when(roomRepository.existsById(1L)).thenReturn(true);

        boolean result = roomService.delete(1L);

        assertFalse(result);
    }
}