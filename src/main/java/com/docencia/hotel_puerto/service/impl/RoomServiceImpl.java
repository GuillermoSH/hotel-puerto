package com.docencia.hotel_puerto.service.impl;

import com.docencia.hotel_puerto.domain.model.Room;
import com.docencia.hotel_puerto.mapper.jpa.RoomMapper;
import com.docencia.hotel_puerto.persistence.jpa.entity.RoomEntity;
import com.docencia.hotel_puerto.persistence.repository.jpa.RoomJpaRepository;
import com.docencia.hotel_puerto.service.api.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomJpaRepository roomRepository;
    private final RoomMapper roomMapper;

    public RoomServiceImpl(RoomJpaRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    @Override
    public boolean exists(Long id) {
        return roomRepository.existsById(id);
    }

    @Override
    public Room findById(Long id) {
        RoomEntity roomEntity = roomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Room by id '" + id + "' not found"));
        return roomMapper.toDomain(roomEntity);
    }

    @Override
    public List<Room> findAll() {
        return roomMapper.toDomains(roomRepository.findAll());
    }

    @Override
    public Room save(Room room) {
        return roomMapper.toDomain(roomRepository.save(roomMapper.toEntity(room)));
    }

    @Override
    public boolean delete(Long id) {
        roomRepository.deleteById(id);
        return !exists(id);
    }
}
