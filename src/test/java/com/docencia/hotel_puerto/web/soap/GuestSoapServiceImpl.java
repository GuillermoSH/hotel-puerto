package com.docencia.hotel_puerto.web.soap;

import com.docencia.hotel_puerto.domain.model.Guest;
import com.docencia.hotel_puerto.domain.model.GuestPreferences;
import com.docencia.hotel_puerto.service.api.GuestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GuestSoapServiceImplTest {

    @Mock
    private GuestService guestService;

    @InjectMocks
    private GuestSoapServiceImpl guestSoapService;

    private Guest sampleGuest;

    @BeforeEach
    void setUp() {
        sampleGuest = new Guest(1L, "Juan Perez", "juan@test.com", "123456", null);
    }

    @Test
    @DisplayName("Debe retornar una lista de todos los huéspedes")
    void findAllTest() {
        when(guestService.findAll()).thenReturn(Collections.singletonList(sampleGuest));

        List<Guest> result = guestSoapService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(guestService, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe buscar un huésped por ID")
    void findByIdTest() {
        when(guestService.findById(1L)).thenReturn(sampleGuest);

        Guest result = guestSoapService.findById(1L);

        assertNotNull(result);
        assertEquals("Juan Perez", result.getFullName());
        verify(guestService).findById(1L);
    }

    @Test
    @DisplayName("Debe crear un nuevo huésped con sus preferencias")
    void createTest() {
        when(guestService.save(any(Guest.class))).thenReturn(sampleGuest);

        Guest result = guestSoapService.create("Juan Perez", "juan@test.com", "123456", "Suite", "All Inclusive");

        assertNotNull(result);
        verify(guestService).save(any(Guest.class));
    }

    @Test
    @DisplayName("Debe actualizar un huésped existente y sus preferencias")
    void updateTest() {
        GuestPreferences prefs = new GuestPreferences(null, 1L, "Standard", "Half Board");
        sampleGuest.setGuestPreferences(prefs);

        when(guestService.findById(1L)).thenReturn(sampleGuest);
        when(guestService.save(any(Guest.class))).thenReturn(sampleGuest);

        Guest result = guestSoapService.update(1L, "Juan Actualizado", "nuevo@test.com", "999", "Deluxe", "Full Board");

        assertNotNull(result);
        assertEquals("Juan Actualizado", result.getFullName());
        assertEquals("Deluxe", result.getGuestPreferences().getRoomType());
        verify(guestService).save(sampleGuest);
    }

    @Test
    @DisplayName("Debe eliminar un huésped con éxito (retornar true)")
    void deleteSuccessTest() {
        when(guestService.delete(1L)).thenReturn(true);

        boolean result = guestSoapService.delete(1L);

        assertTrue(result, "El borrado debería haber sido exitoso");
        verify(guestService, times(1)).delete(1L);
    }

    @Test
    @DisplayName("Debe retornar false si el huésped no pudo ser eliminado")
    void deleteFailTest() {
        when(guestService.delete(99L)).thenReturn(false);

        boolean result = guestSoapService.delete(99L);

        assertFalse(result, "El borrado debería haber fallado");
        verify(guestService).delete(99L);
    }
}