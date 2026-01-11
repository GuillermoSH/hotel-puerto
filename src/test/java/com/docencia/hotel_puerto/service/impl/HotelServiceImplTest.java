package com.docencia.hotel_puerto.service.impl;

import com.docencia.hotel_puerto.domain.model.Hotel;
import com.docencia.hotel_puerto.mapper.jpa.HotelMapper;
import com.docencia.hotel_puerto.persistence.jpa.entity.HotelEntity;
import com.docencia.hotel_puerto.persistence.repository.jpa.HotelJpaRepository;
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
class HotelServiceImplTest {

    @Mock
    private HotelJpaRepository hotelRepository;

    @Mock
    private HotelMapper hotelMapper;

    @InjectMocks
    private HotelServiceImpl hotelService;

    private Hotel hotel;
    private HotelEntity hotelEntity;

    @BeforeEach
    void setUp() {
        hotel = new Hotel();
        hotelEntity = new HotelEntity();
        hotelEntity.setId(1L);
    }

    @Test
    @DisplayName("Debe retornar true si el hotel existe")
    void exists_ReturnsTrue() {
        when(hotelRepository.existsById(1L)).thenReturn(true);
        boolean result = hotelService.exists(1L);
        assertTrue(result);
    }

    @Test
    @DisplayName("Debe retornar el hotel cuando el ID existe")
    void findById_Success() {
        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotelEntity));
        when(hotelMapper.toDomain(hotelEntity)).thenReturn(hotel);

        Hotel result = hotelService.findById(1L);

        assertNotNull(result);
        assertEquals(hotel, result);
    }

    @Test
    @DisplayName("Debe lanzar IllegalArgumentException cuando el hotel no existe")
    void findById_ThrowsException() {
        when(hotelRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> hotelService.findById(1L));
    }

    @Test
    @DisplayName("Debe retornar la lista de todos los hoteles")
    void findAll_Success() {
        List<HotelEntity> entities = List.of(hotelEntity);
        List<Hotel> domains = List.of(hotel);
        when(hotelRepository.findAll()).thenReturn(entities);
        when(hotelMapper.toDomains(entities)).thenReturn(domains);

        List<Hotel> result = hotelService.findAll();

        assertEquals(1, result.size());
        assertEquals(hotel, result.get(0));
    }

    @Test
    @DisplayName("Debe persistir y retornar el hotel guardado")
    void save_Success() {
        when(hotelMapper.toEntity(any(Hotel.class))).thenReturn(hotelEntity);
        when(hotelRepository.save(any(HotelEntity.class))).thenReturn(hotelEntity);
        when(hotelMapper.toDomain(hotelEntity)).thenReturn(hotel);

        Hotel result = hotelService.save(hotel);

        assertNotNull(result);
        verify(hotelRepository).save(hotelEntity);
    }

    @Test
    @DisplayName("Debe retornar true si el hotel se elimina correctamente")
    void delete_Success() {
        doNothing().when(hotelRepository).deleteById(1L);
        when(hotelRepository.existsById(1L)).thenReturn(false);

        boolean result = hotelService.delete(1L);

        assertTrue(result);
    }

    @Test
    @DisplayName("Debe retornar false si el hotel sigue existiendo tras el borrado")
    void delete_Failure() {
        doNothing().when(hotelRepository).deleteById(1L);
        when(hotelRepository.existsById(1L)).thenReturn(true);

        boolean result = hotelService.delete(1L);

        assertFalse(result);
    }
}