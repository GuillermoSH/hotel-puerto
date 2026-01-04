package com.docencia.hotel_puerto.service.impl;

import com.docencia.hotel_puerto.domain.model.Booking;
import com.docencia.hotel_puerto.mapper.jpa.BookingMapper;
import com.docencia.hotel_puerto.persistence.jpa.entity.BookingEntity;
import com.docencia.hotel_puerto.persistence.repository.jpa.BookingJpaRepository;
import com.docencia.hotel_puerto.service.api.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingJpaRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public BookingServiceImpl(BookingJpaRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }

    @Override
    public boolean exists(Long id) {
        return bookingRepository.existsById(id);
    }

    @Override
    public Booking findById(Long id) {
        BookingEntity bookingEntity = bookingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Booking by id '" + id + "' not found"));
        return bookingMapper.toDomain(bookingEntity);
    }

    @Override
    public List<BookingEntity> findByRoomAndDateRange(Long id, String startDate, String endDate) {
        return bookingRepository.findByRoomEntityAndDateRange(id, startDate, endDate);
    }

    @Override
    public List<Booking> findAll() {
        return bookingMapper.toDomains(bookingRepository.findAll());
    }

    @Override
    public Booking save(Booking booking) {
        return bookingMapper.toDomain(bookingRepository.save(bookingMapper.toEntity(booking)));
    }

    @Override
    public boolean delete(Long id) {
        bookingRepository.deleteById(id);
        return !exists(id);
    }
}
