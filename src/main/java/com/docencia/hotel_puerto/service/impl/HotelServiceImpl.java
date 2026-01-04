package com.docencia.hotel_puerto.service.impl;

import com.docencia.hotel_puerto.domain.model.Hotel;
import com.docencia.hotel_puerto.mapper.jpa.HotelMapper;
import com.docencia.hotel_puerto.persistence.jpa.entity.HotelEntity;
import com.docencia.hotel_puerto.persistence.repository.jpa.HotelJpaRepository;
import com.docencia.hotel_puerto.service.api.HotelService;

import java.util.List;

public class HotelServiceImpl implements HotelService {
    private final HotelJpaRepository hotelRepository;
    private final HotelMapper hotelMapper;

    public HotelServiceImpl(HotelJpaRepository hotelRepository, HotelMapper hotelMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
    }

    @Override
    public boolean exists(Long id) {
        return hotelRepository.existsById(id);
    }

    @Override
    public Hotel findById(Long id) {
        HotelEntity hotelEntity = hotelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Hotel by id '" + id + "' not found"));
        return hotelMapper.toDomain(hotelEntity);
    }

    @Override
    public List<Hotel> findAll() {
        return hotelMapper.toDomains(hotelRepository.findAll());
    }

    @Override
    public Hotel save(Hotel hotel) {
        return hotelMapper.toDomain(hotelRepository.save(hotelMapper.toEntity(hotel)));
    }

    @Override
    public boolean delete(Long id) {
        hotelRepository.deleteById(id);
        return !exists(id);
    }
}
