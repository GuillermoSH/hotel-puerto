package com.docencia.hotel_puerto.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuestTest {

    @Test
    @DisplayName("Debe probar constructores, getters y setters de Guest")
    void testGettersSettersAndConstructors() {
        GuestPreferences preferences = new GuestPreferences();

        Guest guest = new Guest(1L, "Juan Perez", "juan@test.com", "123456789", preferences);

        assertEquals(1L, guest.getId());
        assertEquals("Juan Perez", guest.getFullName());
        assertEquals("juan@test.com", guest.getEmail());
        assertEquals("123456789", guest.getPhone());
        assertEquals(preferences, guest.getGuestPreferences());

        Guest emptyGuest = new Guest();
        emptyGuest.setId(2L);
        emptyGuest.setFullName("Maria Garcia");
        emptyGuest.setEmail("maria@test.com");
        emptyGuest.setPhone("987654321");
        emptyGuest.setGuestPreferences(preferences);

        assertEquals(2L, emptyGuest.getId());
        assertEquals("Maria Garcia", emptyGuest.getFullName());
        assertEquals("maria@test.com", emptyGuest.getEmail());
        assertEquals("987654321", emptyGuest.getPhone());
        assertEquals(preferences, emptyGuest.getGuestPreferences());

        Guest idGuest = new Guest(3L);
        assertEquals(3L, idGuest.getId());
    }

    @Test
    @DisplayName("Debe probar todas las ramas de equals y hashCode")
    void testEqualsAndHashCode() {
        Guest guest1 = new Guest(1L);
        Guest guest2 = new Guest(1L);
        Guest guest3 = new Guest(2L);
        Guest guestNullId1 = new Guest(null);
        Guest guestNullId2 = new Guest(null);

        assertEquals(guest1, guest2);
        assertEquals(guest1.hashCode(), guest2.hashCode());

        assertEquals(guest1, guest1);

        assertNotEquals(guest1, guest3);

        assertNotEquals(null, guest1);

        assertNotEquals(new Object(), guest1);

        assertEquals(guestNullId1, guestNullId2);
        assertEquals(guestNullId1.hashCode(), guestNullId2.hashCode());
    }
}