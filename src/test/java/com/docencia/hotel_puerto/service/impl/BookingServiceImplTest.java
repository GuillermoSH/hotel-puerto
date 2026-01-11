package com.docencia.hotel_puerto.service.impl;

import com.docencia.hotel_puerto.domain.model.Booking;
import com.docencia.hotel_puerto.mapper.jpa.BookingMapper;
import com.docencia.hotel_puerto.persistence.jpa.entity.BookingEntity;
import com.docencia.hotel_puerto.persistence.repository.jpa.BookingJpaRepository;
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
class BookingServiceImplTest {

    @Mock
    private BookingJpaRepository bookingRepository;

    @Mock
    private BookingMapper bookingMapper;

    @InjectMocks
    private BookingServiceImpl bookingService;

    private Booking booking;
    private BookingEntity bookingEntity;

    @BeforeEach
    void setUp() {
        booking = new Booking();
        bookingEntity = new BookingEntity();
    }

    @Test
    @DisplayName("Debe retornar true si la reserva existe")
    void exists_ReturnsTrue() {
        when(bookingRepository.existsById(1L)).thenReturn(true);

        boolean exists = bookingService.exists(1L);

        assertTrue(exists);
        verify(bookingRepository).existsById(1L);
    }

    @Test
    @DisplayName("Debe encontrar una reserva por ID y mapearla a dominio")
    void findById_Success() {
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(bookingEntity));
        when(bookingMapper.toDomain(bookingEntity)).thenReturn(booking);

        Booking result = bookingService.findById(1L);

        assertNotNull(result);
        assertEquals(booking, result);
    }

    @Test
    @DisplayName("Debe lanzar excepción si la reserva no existe")
    void findById_ThrowsException() {
        when(bookingRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> bookingService.findById(1L));
    }

    @Test
    @DisplayName("Debe guardar una reserva correctamente")
    void save_Success() {
        when(bookingMapper.toEntity(any(Booking.class))).thenReturn(bookingEntity);
        when(bookingRepository.save(any(BookingEntity.class))).thenReturn(bookingEntity);
        when(bookingMapper.toDomain(any(BookingEntity.class))).thenReturn(booking);

        Booking savedBooking = bookingService.save(booking);

        assertNotNull(savedBooking);
        verify(bookingRepository, times(1)).save(bookingEntity);
    }

    @Test
    @DisplayName("Debe retornar true tras eliminar si la reserva ya no existe")
    void delete_Success() {
        Long id = 1L;
        doNothing().when(bookingRepository).deleteById(id);
        when(bookingRepository.existsById(id)).thenReturn(false);

        boolean result = bookingService.delete(id);

        assertTrue(result);
        verify(bookingRepository).deleteById(id);
    }

    @Test
    @DisplayName("Debe retornar una lista de bookings convertidos a dominio")
    void findAll_ReturnsList() {
        List<BookingEntity> entities = List.of(bookingEntity);
        List<Booking> domains = List.of(booking);

        when(bookingRepository.findAll()).thenReturn(entities);
        when(bookingMapper.toDomains(entities)).thenReturn(domains);

        List<Booking> result = bookingService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(booking, result.getFirst());
    }

    @Test
    @DisplayName("Debe filtrar por habitación y fechas")
    void findByRoomAndDateRange_ReturnsResults() {
        Long roomId = 101L;
        String start = "2024-01-01";
        String end = "2024-01-05";
        when(bookingRepository.findByRoomEntityAndDateRange(roomId, start, end))
                .thenReturn(List.of(bookingEntity));

        List<BookingEntity> result = bookingService.findByRoomAndDateRange(roomId, start, end);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(bookingRepository).findByRoomEntityAndDateRange(roomId, start, end);
    }

    @Test
    @DisplayName("Debe retornar false si el objeto sigue existiendo tras borrar")
    void delete_Failure_StillExists() {
        Long id = 1L;
        doNothing().when(bookingRepository).deleteById(id);

        when(bookingRepository.existsById(id)).thenReturn(true);

        boolean result = bookingService.delete(id);

        assertFalse(result, "Debe ser false porque !exists(id) donde exists es true es false");
        verify(bookingRepository).deleteById(id);
        verify(bookingRepository).existsById(id);
    }
}