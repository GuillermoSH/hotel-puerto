package com.docencia.hotel_puerto.mapper.jpa;

import org.mapstruct.Mapper;
import com.docencia.hotel_puerto.domain.model.Room;
import com.docencia.hotel_puerto.persistence.jpa.entity.RoomEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomEntity toEntity(Room domain);
    Room toDomain(RoomEntity entity);
    List<RoomEntity> toEntities(List<Room> domains);
    List<Room> toDomains(List<RoomEntity> entities);
}
