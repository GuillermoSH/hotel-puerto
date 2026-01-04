package com.docencia.hotel_puerto.mapper.jpa;

import org.mapstruct.Mapper;
import com.docencia.hotel_puerto.domain.model.Guest;
import com.docencia.hotel_puerto.persistence.jpa.entity.GuestEntity;

@Mapper(componentModel = "spring")
public interface GuestMapper {
    GuestEntity toEntity(Guest domain);
    Guest toDomain(GuestEntity entity);
}
