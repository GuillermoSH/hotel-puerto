package com.docencia.hotel_puerto.domain.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Hotel {
    private Long id;
    private String name;
    private String address;
    private Set<Room> rooms = new HashSet<>();

    public Hotel() {
    }

    public Hotel(Long id) {
        this.id = id;
    }

    public Hotel(Long id, String name, String address, Set<Room> rooms) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rooms = rooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(getId(), hotel.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
