package com.docencia.hotel_puerto.mapper.jpa;

import org.mapstruct.Mapper;
import com.docencia.hotel_puerto.domain.model.Booking;
import com.docencia.hotel_puerto.persistence.jpa.entity.BookingEntity;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingEntity toEntity(Booking domain);
    Booking toDomain(BookingEntity entity);
}
