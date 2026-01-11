package com.docencia.hotel_puerto.persistence.nosql.document;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuestPreferencesDocumentTest {

    @Test
    @DisplayName("Debe probar constructores, getters y setters de GuestPreferencesDocument")
    void testGettersSettersAndConstructors() {
        GuestPreferencesDocument doc = new GuestPreferencesDocument(
                "mongo-id-123",
                1L,
                "Suite",
                "Full Board"
        );

        assertEquals("mongo-id-123", doc.getId());
        assertEquals(1L, doc.getGuestId());
        assertEquals("Suite", doc.getRoomType());
        assertEquals("Full Board", doc.getBoardType());

        GuestPreferencesDocument emptyDoc = new GuestPreferencesDocument();
        emptyDoc.setId("mongo-id-456");
        emptyDoc.setGuestId(2L);
        emptyDoc.setRoomType("Double");
        emptyDoc.setBoardType("Half Board");

        assertEquals("mongo-id-456", emptyDoc.getId());
        assertEquals(2L, emptyDoc.getGuestId());
        assertEquals("Double", emptyDoc.getRoomType());
        assertEquals("Half Board", emptyDoc.getBoardType());

        GuestPreferencesDocument idDoc = new GuestPreferencesDocument("mongo-id-789");
        assertEquals("mongo-id-789", idDoc.getId());
    }

    @Test
    @DisplayName("Debe probar el funcionamiento de equals y hashCode basados en el ID de MongoDB")
    void testEqualsAndHashCode() {
        GuestPreferencesDocument doc1 = new GuestPreferencesDocument("id-1");
        GuestPreferencesDocument doc2 = new GuestPreferencesDocument("id-1");
        GuestPreferencesDocument doc3 = new GuestPreferencesDocument("id-2");

        assertEquals(doc1, doc2);
        assertEquals(doc1.hashCode(), doc2.hashCode());

        assertEquals(doc1, doc1);

        assertNotEquals(doc1, doc3);
        assertNotEquals(doc1.hashCode(), doc3.hashCode());

        assertNotEquals(null, doc1);
        assertNotEquals("un string cualquiera", doc1);
    }
}