package com.docencia.hotel_puerto.web.rest;

import com.docencia.hotel_puerto.domain.model.Guest;
import com.docencia.hotel_puerto.service.api.GuestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GuestControllerUnitTest {

    @Mock
    private GuestService guestService;

    @InjectMocks
    private GuestController guestController;

    private Guest guest;

    @BeforeEach
    void setUp() {
        guest = new Guest();
        guest.setId(1L);
        guest.setFullName("Juan Perez");
    }

    @Test
    @DisplayName("Debe retornar lista de huéspedes con status 200")
    void findAll_Success() {
        when(guestService.findAll()).thenReturn(List.of(guest));

        ResponseEntity<List<Guest>> response = guestController.findAll();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().size());
        verify(guestService, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe retornar un huésped por ID")
    void findById_Success() {
        when(guestService.findById(1L)).thenReturn(guest);

        ResponseEntity<Guest> response = guestController.findById(1L);

        assertNotNull(response.getBody());
        assertEquals("Juan Perez", response.getBody().getFullName());
    }

    @Test
    @DisplayName("Debe crear un huésped correctamente")
    void create_Success() {
        when(guestService.save(any(Guest.class))).thenReturn(guest);

        ResponseEntity<Guest> response = guestController.create(guest);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Debe llamar al servicio para eliminar")
    void delete_Success() {
        when(guestService.delete(1L)).thenReturn(true);

        ResponseEntity<Boolean> response = guestController.deleteById(1L);

        assertTrue(response.getBody());
        verify(guestService).delete(1L);
    }

    @Test
    @DisplayName("Debe actualizar un huésped correctamente")
    void update_Success() {
        Long id = 1L;
        when(guestService.save(any(Guest.class))).thenReturn(guest);

        ResponseEntity<Guest> response = guestController.update(guest, id);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Juan Perez", response.getBody().getFullName());
        verify(guestService, times(1)).save(guest);
    }
}