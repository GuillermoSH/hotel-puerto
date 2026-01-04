package com.docencia.hotel_puerto.domain.model;

import java.util.Objects;

public class Guest {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private GuestPreferences guestPreferences;

    public Guest() {
    }

    public Guest(Long id) {
        this.id = id;
    }

    public Guest(Long id, String fullName, String email, String phone, GuestPreferences guestPreferences) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.guestPreferences = guestPreferences;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public GuestPreferences getGuestPreferences() {
        return guestPreferences;
    }

    public void setGuestPreferences(GuestPreferences guestPreferences) {
        this.guestPreferences = guestPreferences;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return Objects.equals(getId(), guest.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
