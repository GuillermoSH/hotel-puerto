package com.docencia.hotel_puerto.service.api;

import com.docencia.hotel_puerto.domain.model.Hotel;

import java.util.List;

public interface HotelService {
    boolean exists(Long id);
    Hotel findById(Long id);
    List<Hotel> findAll();
    Hotel save(Hotel hotel);
    boolean delete(Long id);
}
