package com.docencia.hotel_puerto.service.api;

import com.docencia.hotel_puerto.domain.model.Guest;

import java.util.List;

public interface GuestService {
    boolean exists(Long id);
    Guest findById(Long id);
    List<Guest> findAll();
    Guest save(Guest guest);
    boolean delete(Long id);
}
