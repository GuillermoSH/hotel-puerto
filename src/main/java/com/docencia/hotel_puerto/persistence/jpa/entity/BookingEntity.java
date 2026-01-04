package com.docencia.hotel_puerto.persistence.jpa.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "booking")
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "check_in")
    private String checkIn;

    @Column(name ="check_out")
    private String checkOut;

    @ManyToOne
    @JoinColumn(name="room_id", nullable = false)
    private RoomEntity room;

    @ManyToOne
    @JoinColumn(name = "guest_id", nullable = false)
    private GuestEntity guest;

    public BookingEntity() {
    }

    public BookingEntity(Long id) {
        this.id = id;
    }

    public BookingEntity(Long id, String checkIn, String checkOut, RoomEntity room, GuestEntity guest) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.room = room;
        this.guest = guest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    public GuestEntity getGuest() {
        return guest;
    }

    public void setGuest(GuestEntity guest) {
        this.guest = guest;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BookingEntity that = (BookingEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
