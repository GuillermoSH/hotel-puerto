package com.docencia.hotel_puerto.mapper.jpa;

import com.docencia.hotel_puerto.domain.model.Guest;
import com.docencia.hotel_puerto.persistence.jpa.entity.GuestEntity;
import com.docencia.hotel_puerto.persistence.nosql.document.GuestPreferencesDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GuestMapper {
    GuestEntity toEntity(Guest domain);

    Guest toDomain(GuestEntity entity);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "guestPreferences", source = "document")
    Guest toDomain(GuestEntity entity, GuestPreferencesDocument document);

    List<GuestEntity> toEntities(List<Guest> domains);

    List<Guest> toDomains(List<GuestEntity> entities);
}
