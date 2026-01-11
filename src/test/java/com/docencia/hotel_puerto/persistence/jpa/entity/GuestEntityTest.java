package com.docencia.hotel_puerto.persistence.jpa.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuestEntityTest {

    @Test
    @DisplayName("Debe probar constructores, getters y setters de GuestEntity")
    void testGettersSettersAndConstructors() {
        GuestEntity guest = new GuestEntity(1L, "Juan Pérez", "juan@example.com", "123456789");

        assertEquals(1L, guest.getId());
        assertEquals("Juan Pérez", guest.getFullName());
        assertEquals("juan@example.com", guest.getEmail());
        assertEquals("123456789", guest.getPhone());

        GuestEntity emptyGuest = new GuestEntity();
        emptyGuest.setId(2L);
        emptyGuest.setFullName("Maria Garcia");
        emptyGuest.setEmail("maria@example.com");
        emptyGuest.setPhone("987654321");

        assertEquals(2L, emptyGuest.getId());
        assertEquals("Maria Garcia", emptyGuest.getFullName());
        assertEquals("maria@example.com", emptyGuest.getEmail());
        assertEquals("987654321", emptyGuest.getPhone());

        GuestEntity idGuest = new GuestEntity(3L);
        assertEquals(3L, idGuest.getId());
    }

    @Test
    @DisplayName("Debe probar el funcionamiento de equals y hashCode basados en ID")
    void testEqualsAndHashCode() {
        GuestEntity guest1 = new GuestEntity(1L);
        GuestEntity guest2 = new GuestEntity(1L);
        GuestEntity guest3 = new GuestEntity(2L);

        assertEquals(guest1, guest2);
        assertEquals(guest1.hashCode(), guest2.hashCode());

        assertEquals(guest1, guest1);

        assertNotEquals(guest1, guest3);
        assertNotEquals(guest1.hashCode(), guest3.hashCode());

        assertNotEquals(null, guest1);
        assertNotEquals("no soy un huésped", guest1);
    }
}