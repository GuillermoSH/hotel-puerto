package com.docencia.hotel_puerto.domain.model;

import java.util.Objects;

public class GuestPreferences {
    private String id;
    private Long guestId;
    private String roomType;
    private String boardType;

    public GuestPreferences() {
    }

    public GuestPreferences(String id) {
        this.id = id;
    }

    public GuestPreferences(String id, Long guestId, String roomType, String boardType) {
        this.id = id;
        this.guestId = guestId;
        this.roomType = roomType;
        this.boardType = boardType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getBoardType() {
        return boardType;
    }

    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GuestPreferences that = (GuestPreferences) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
