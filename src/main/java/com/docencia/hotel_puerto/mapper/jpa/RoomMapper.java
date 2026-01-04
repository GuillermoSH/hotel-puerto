package com.docencia.hotel_puerto.mapper.jpa;

import org.mapstruct.Mapper;
import com.docencia.hotel_puerto.domain.model.Room;
import com.docencia.hotel_puerto.persistence.jpa.entity.RoomEntity;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomEntity toEntity(Room domain);
    Room toDomain(RoomEntity entity);
}
