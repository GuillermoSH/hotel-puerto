package com.docencia.hotel_puerto.mapper.jpa;

import org.mapstruct.Mapper;
import com.docencia.hotel_puerto.domain.model.Hotel;
import com.docencia.hotel_puerto.persistence.jpa.entity.HotelEntity;

@Mapper(componentModel = "spring")
public interface HotelMapper {
    HotelEntity toEntity(Hotel domain);
    Hotel toDomain(HotelEntity entity);
}
