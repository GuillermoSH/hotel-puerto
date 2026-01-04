package com.docencia.hotel_puerto.service.api;

import com.docencia.hotel_puerto.domain.model.Room;

import java.util.List;

public interface RoomService {
    boolean exists(Long id);
    Room findById(Long id);
    List<Room> findAll();
    Room save(Room room);
    boolean delete(Long id);
}
