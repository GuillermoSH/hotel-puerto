package com.docencia.hotel_puerto.mapper.nosql;

import org.mapstruct.Mapper;
import com.docencia.hotel_puerto.domain.model.GuestPreferences;
import com.docencia.hotel_puerto.persistence.nosql.document.GuestPreferencesDocument;

@Mapper(componentModel = "spring")
public interface GuestPreferencesMapper {
    GuestPreferencesDocument toDocument(GuestPreferences domain);
    GuestPreferences toDomain(GuestPreferencesDocument doc);
}
