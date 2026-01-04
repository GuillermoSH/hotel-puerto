package com.docencia.hotel_puerto.persistence.repository.nosql;

import com.docencia.hotel_puerto.persistence.nosql.document.GuestPreferencesDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GuestPreferencesRepository extends MongoRepository<GuestPreferencesDocument, String> {
    Optional<GuestPreferencesDocument> findByGuestId(Long guestId);
    boolean deleteByGuestId(Long guestId);
}
