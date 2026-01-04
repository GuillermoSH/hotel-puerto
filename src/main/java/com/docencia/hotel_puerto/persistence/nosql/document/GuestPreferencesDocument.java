package com.docencia.hotel_puerto.persistence.nosql.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "guest_preferences")
public class GuestPreferencesDocument {
    @Id
    private String id;
    private Long guestId;
    private String roomType;
    private String boardType;

    public GuestPreferencesDocument() {
    }

    public GuestPreferencesDocument(String id) {
        this.id = id;
    }

    /**
     * Constructor para inicializar las preferencias de un huesped.
     *
     * @param id El identificador unico del documento en MongoDB.
     * @param guestId El ID de referencia del huésped.
     * @param roomType El tipo de habitacion preferido (ej: "Suite", "Double", etc).
     * @param boardType El tipo de pension seleccionado (ej: "Full Board", "Half Board", etc).
     */
    public GuestPreferencesDocument(String id, Long guestId, String roomType, String boardType) {
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
        GuestPreferencesDocument that = (GuestPreferencesDocument) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
