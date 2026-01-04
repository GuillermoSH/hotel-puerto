package com.docencia.hotel_puerto.service.api;

import com.docencia.hotel_puerto.domain.model.Booking;
import com.docencia.hotel_puerto.persistence.jpa.entity.BookingEntity;

import java.util.List;

public interface BookingService {
    boolean exists(Long id);
    Booking findById(Long id);
    List<BookingEntity> findByRoomAndDateRange(Long id, String startDate, String endDate);
    List<Booking> findAll();
    Booking save(Booking booking);
    boolean delete(Long id);
}
