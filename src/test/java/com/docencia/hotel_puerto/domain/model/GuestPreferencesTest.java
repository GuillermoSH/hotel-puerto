package com.docencia.hotel_puerto.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuestPreferencesTest {

    @Test
    @DisplayName("Debe probar constructores, getters y setters de GuestPreferences")
    void testGettersSettersAndConstructors() {
        GuestPreferences preferences = new GuestPreferences("pref-123", 1L, "Suite", "All Inclusive");

        assertEquals("pref-123", preferences.getId());
        assertEquals(1L, preferences.getGuestId());
        assertEquals("Suite", preferences.getRoomType());
        assertEquals("All Inclusive", preferences.getBoardType());

        GuestPreferences emptyPrefs = new GuestPreferences();
        emptyPrefs.setId("pref-456");
        emptyPrefs.setGuestId(2L);
        emptyPrefs.setRoomType("Double");
        emptyPrefs.setBoardType("Half Board");

        assertEquals("pref-456", emptyPrefs.getId());
        assertEquals(2L, emptyPrefs.getGuestId());
        assertEquals("Double", emptyPrefs.getRoomType());
        assertEquals("Half Board", emptyPrefs.getBoardType());

        GuestPreferences idPrefs = new GuestPreferences("pref-789");
        assertEquals("pref-789", idPrefs.getId());
    }

    @Test
    @DisplayName("Debe probar todas las ramas de equals y hashCode")
    void testEqualsAndHashCode() {
        GuestPreferences pref1 = new GuestPreferences("id-1");
        GuestPreferences pref2 = new GuestPreferences("id-1");
        GuestPreferences pref3 = new GuestPreferences("id-2");
        GuestPreferences prefNullId1 = new GuestPreferences(null);
        GuestPreferences prefNullId2 = new GuestPreferences(null);

        assertEquals(pref1, pref2);
        assertEquals(pref1.hashCode(), pref2.hashCode());

        assertEquals(pref1, pref1);

        assertNotEquals(pref1, pref3);

        assertNotEquals(null, pref1);

        assertNotEquals(new Object(), pref1);

        assertEquals(prefNullId1, prefNullId2);
        assertEquals(prefNullId1.hashCode(), prefNullId2.hashCode());
    }
}