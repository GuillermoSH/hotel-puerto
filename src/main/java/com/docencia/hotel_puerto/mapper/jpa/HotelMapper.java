package com.docencia.hotel_puerto.mapper.jpa;

import org.mapstruct.Mapper;
import com.docencia.hotel_puerto.domain.model.Hotel;
import com.docencia.hotel_puerto.persistence.jpa.entity.HotelEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelMapper {
    HotelEntity toEntity(Hotel domain);
    Hotel toDomain(HotelEntity entity);
    List<HotelEntity> toEntities(List<Hotel> domains);
    List<Hotel> toDomains(List<HotelEntity> entities);
}
