package com.docencia.hotel_puerto.mapper.jpa;

import com.docencia.hotel_puerto.domain.model.Guest;
import com.docencia.hotel_puerto.persistence.jpa.entity.GuestEntity;
import com.docencia.hotel_puerto.persistence.nosql.document.GuestPreferencesDocument;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuestMapperTest {

    private final GuestMapper mapper = new GuestMapperImpl();

    @Test
    @DisplayName("Debe mapear de dominio a entidad")
    void toEntity_Success() {
        Guest domain = new Guest();
        domain.setId(1L);
        GuestEntity entity = mapper.toEntity(domain);
        assertNotNull(entity);
        assertEquals(1L, entity.getId());
    }

    @Test
    @DisplayName("Debe retornar null al mapear entidad si el dominio es null")
    void toEntity_Null() {
        assertNull(mapper.toEntity(null));
    }

    @Test
    @DisplayName("Debe mapear de entidad a dominio simple")
    void toDomain_Simple_Success() {
        GuestEntity entity = new GuestEntity();
        entity.setId(1L);
        Guest result = mapper.toDomain(entity);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    @DisplayName("Debe mapear combinando entidad y documento de preferencias")
    void toDomain_WithDocument_Success() {
        GuestEntity entity = new GuestEntity();
        entity.setId(1L);
        GuestPreferencesDocument document = new GuestPreferencesDocument();
        document.setRoomType("Suite");

        Guest result = mapper.toDomain(entity, document);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertNotNull(result.getGuestPreferences());
        assertEquals("Suite", result.getGuestPreferences().getRoomType());
    }

    @Test
    @DisplayName("Debe manejar nulos en el mapeo combinado")
    void toDomain_Combined_Nulls() {
        GuestEntity entity = new GuestEntity();
        entity.setId(1L);

        Guest resultOnlyEntity = mapper.toDomain(entity, null);
        assertNotNull(resultOnlyEntity);
        assertNull(resultOnlyEntity.getGuestPreferences());

        assertNull(mapper.toDomain(null, null));
    }

    @Test
    @DisplayName("Debe mapear listas de dominios a entidades")
    void toEntities_Success() {
        Guest domain = new Guest();
        List<GuestEntity> result = mapper.toEntities(List.of(domain));
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Debe retornar null si la lista de dominios es null")
    void toEntities_Null() {
        assertNull(mapper.toEntities(null));
    }

    @Test
    @DisplayName("Debe mapear listas de entidades a dominios")
    void toDomains_Success() {
        GuestEntity entity = new GuestEntity();
        List<Guest> result = mapper.toDomains(List.of(entity));
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Debe retornar null si la lista de entidades es null")
    void toDomains_Null() {
        assertNull(mapper.toDomains(null));
    }
}