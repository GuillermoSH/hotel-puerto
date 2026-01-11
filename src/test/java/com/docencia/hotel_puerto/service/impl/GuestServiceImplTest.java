package com.docencia.hotel_puerto.service.impl;

import com.docencia.hotel_puerto.domain.model.Guest;
import com.docencia.hotel_puerto.domain.model.GuestPreferences;
import com.docencia.hotel_puerto.mapper.jpa.GuestMapper;
import com.docencia.hotel_puerto.mapper.nosql.GuestPreferencesMapper;
import com.docencia.hotel_puerto.persistence.jpa.entity.GuestEntity;
import com.docencia.hotel_puerto.persistence.nosql.document.GuestPreferencesDocument;
import com.docencia.hotel_puerto.persistence.repository.jpa.GuestJpaRepository;
import com.docencia.hotel_puerto.persistence.repository.nosql.GuestPreferencesRepository;
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
class GuestServiceImplTest {

    @Mock private GuestJpaRepository guestRepository;
    @Mock private GuestMapper guestMapper;
    @Mock private GuestPreferencesRepository guestPreferencesRepository;
    @Mock private GuestPreferencesMapper guestPreferencesMapper;

    @InjectMocks private GuestServiceImpl guestService;

    private GuestEntity guestEntity;
    private GuestPreferencesDocument guestDocument;
    private Guest guestDomain;

    @BeforeEach
    void setUp() {
        guestEntity = new GuestEntity();
        guestEntity.setId(1L);

        guestDocument = new GuestPreferencesDocument();
        guestDocument.setGuestId(1L);

        guestDomain = new Guest();
    }

    @Test
    @DisplayName("Debe retornar Guest con preferencias si existen")
    void findById_WithPreferences() {
        when(guestRepository.findById(1L)).thenReturn(Optional.of(guestEntity));
        when(guestPreferencesRepository.findByGuestId(1L)).thenReturn(Optional.of(guestDocument));
        when(guestMapper.toDomain(guestEntity, guestDocument)).thenReturn(guestDomain);

        Guest result = guestService.findById(1L);

        assertNotNull(result);
        verify(guestPreferencesRepository).findByGuestId(1L);
    }

    @Test
    @DisplayName("Debe retornar Guest aunque NO tenga preferencias (Optional.empty)")
    void findById_WithoutPreferences() {
        when(guestRepository.findById(1L)).thenReturn(Optional.of(guestEntity));
        when(guestPreferencesRepository.findByGuestId(1L)).thenReturn(Optional.empty());
        when(guestMapper.toDomain(guestEntity, null)).thenReturn(guestDomain);

        Guest result = guestService.findById(1L);

        assertNotNull(result);
        verify(guestMapper).toDomain(guestEntity, null);
    }

    @Test
    @DisplayName("Debe lanzar IllegalArgumentException si el Guest no existe")
    void findById_ThrowsException() {
        when(guestRepository.findById(1L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            guestService.findById(1L);
        });

        assertEquals("Guest by id '1' not found", exception.getMessage());

        verifyNoInteractions(guestPreferencesRepository);
    }

    @Test
    @DisplayName("Debe guardar entidad y documento si vienen preferencias")
    void save_FullFlow_WithPreferences() {
        guestDomain.setGuestPreferences(new GuestPreferences());
        when(guestMapper.toEntity(any())).thenReturn(guestEntity);
        when(guestRepository.save(any())).thenReturn(guestEntity);
        when(guestPreferencesMapper.toDocument(any())).thenReturn(guestDocument);
        when(guestPreferencesRepository.findByGuestId(1L)).thenReturn(Optional.of(guestDocument));
        when(guestMapper.toDomain(any(), any())).thenReturn(guestDomain);

        guestService.save(guestDomain);

        verify(guestPreferencesRepository).save(any(GuestPreferencesDocument.class));
        verify(guestPreferencesMapper).toDocument(any());
    }

    @Test
    @DisplayName("Solo guarda entidad si NO vienen preferencias")
    void save_OnlyEntity_WhenPreferencesNull() {
        guestDomain.setGuestPreferences(null);
        when(guestMapper.toEntity(any())).thenReturn(guestEntity);
        when(guestRepository.save(any())).thenReturn(guestEntity);
        when(guestPreferencesRepository.findByGuestId(any())).thenReturn(Optional.empty());

        guestService.save(guestDomain);

        verify(guestPreferencesRepository, never()).save(any());
        verifyNoInteractions(guestPreferencesMapper);
    }

    @Test
    @DisplayName("Debe mapear la lista combinando SQL y NoSQL")
    void findAll_Success() {
        when(guestRepository.findAll()).thenReturn(List.of(guestEntity));
        when(guestPreferencesRepository.findByGuestId(1L)).thenReturn(Optional.of(guestDocument));
        when(guestMapper.toDomain(any(), any())).thenReturn(guestDomain);

        List<Guest> result = guestService.findAll();

        assertEquals(1, result.size());
        verify(guestPreferencesRepository, times(1)).findByGuestId(any());
    }

    @Test
    @DisplayName("Debe borrar en ambos repositorios")
    void delete_Success() {
        Long id = 1L;
        when(guestRepository.existsById(id)).thenReturn(false);

        boolean result = guestService.delete(id);

        assertTrue(result);
        verify(guestRepository).deleteById(id);
        verify(guestPreferencesRepository).deleteByGuestId(id);
    }

    @Test
    @DisplayName("Debe retornar false si el Guest aún existe después de intentar borrarlo")
    void delete_ReturnsFalse_WhenStillExists() {
        Long id = 1L;
        when(guestRepository.existsById(id)).thenReturn(true);

        boolean result = guestService.delete(id);

        assertFalse(result, "Debe ser false porque !exists(true) es false");
        verify(guestRepository).deleteById(id);
        verify(guestPreferencesRepository).deleteByGuestId(id);
        verify(guestRepository).existsById(id);
    }
}