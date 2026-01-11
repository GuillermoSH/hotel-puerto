package com.docencia.hotel_puerto.mapper.nosql;

import com.docencia.hotel_puerto.domain.model.GuestPreferences;
import com.docencia.hotel_puerto.persistence.nosql.document.GuestPreferencesDocument;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuestPreferencesMapperTest {

    private final GuestPreferencesMapperImpl mapper = new GuestPreferencesMapperImpl();

    @Test
    @DisplayName("Debe mapear de dominio a documento NoSQL")
    void toDocument_Success() {
        GuestPreferences domain = new GuestPreferences();
        domain.setId("pref-123");
        domain.setGuestId(1L);
        domain.setRoomType("DELUXE");
        domain.setBoardType("ALL_INCLUSIVE");

        GuestPreferencesDocument result = mapper.toDocument(domain);

        assertNotNull(result);
        assertEquals("pref-123", result.getId());
        assertEquals(1L, result.getGuestId());
        assertEquals("DELUXE", result.getRoomType());
        assertEquals("ALL_INCLUSIVE", result.getBoardType());
    }

    @Test
    @DisplayName("Debe retornar null al mapear documento si el dominio es null")
    void toDocument_Null() {
        assertNull(mapper.toDocument(null));
    }

    @Test
    @DisplayName("Debe mapear de documento NoSQL a dominio")
    void toDomain_Success() {
        GuestPreferencesDocument doc = new GuestPreferencesDocument();
        doc.setId("pref-123");
        doc.setGuestId(1L);
        doc.setRoomType("SINGLE");
        doc.setBoardType("BREAKFAST_ONLY");

        GuestPreferences result = mapper.toDomain(doc);

        assertNotNull(result);
        assertEquals("pref-123", result.getId());
        assertEquals(1L, result.getGuestId());
        assertEquals("SINGLE", result.getRoomType());
        assertEquals("BREAKFAST_ONLY", result.getBoardType());
    }

    @Test
    @DisplayName("Debe retornar null al mapear dominio si el documento es null")
    void toDomain_Null() {
        assertNull(mapper.toDomain(null));
    }
}