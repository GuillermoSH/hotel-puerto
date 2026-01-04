package com.docencia.hotel_puerto.service.impl;

import com.docencia.hotel_puerto.domain.model.Guest;
import com.docencia.hotel_puerto.mapper.jpa.GuestMapper;
import com.docencia.hotel_puerto.mapper.nosql.GuestPreferencesMapper;
import com.docencia.hotel_puerto.persistence.jpa.entity.GuestEntity;
import com.docencia.hotel_puerto.persistence.nosql.document.GuestPreferencesDocument;
import com.docencia.hotel_puerto.persistence.repository.jpa.GuestJpaRepository;
import com.docencia.hotel_puerto.persistence.repository.nosql.GuestPreferencesRepository;
import com.docencia.hotel_puerto.service.api.GuestService;

import java.util.List;
import java.util.stream.Collectors;

public class GuestServiceImpl implements GuestService {
    private final GuestJpaRepository guestRepository;
    private final GuestMapper guestMapper;
    private final GuestPreferencesRepository guestPreferencesRepository;
    private final GuestPreferencesMapper guestPreferencesMapper;

    public GuestServiceImpl(GuestJpaRepository guestRepository, GuestMapper guestMapper, GuestPreferencesRepository guestPreferencesRepository, GuestPreferencesMapper guestPreferencesMapper) {
        this.guestRepository = guestRepository;
        this.guestMapper = guestMapper;
        this.guestPreferencesRepository = guestPreferencesRepository;
        this.guestPreferencesMapper = guestPreferencesMapper;
    }

    @Override
    public boolean exists(Long id) {
        return guestRepository.existsById(id);
    }

    @Override
    public Guest findById(Long id) {
        GuestEntity guestEntity = guestRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Guest by id '" + id + "' not found"));
        GuestPreferencesDocument guestPreferencesDocument = guestPreferencesRepository.findByGuestId(id).orElse(null);

        return guestMapper.toDomain(guestEntity, guestPreferencesDocument);
    }

    @Override
    public List<Guest> findAll() {
        List<GuestEntity> guestEntities = guestRepository.findAll();
        return guestEntities.stream().map(guestEntity -> {
            GuestPreferencesDocument document = guestPreferencesRepository.findByGuestId(guestEntity.getId()).orElse(null);
            return guestMapper.toDomain(guestEntity, document);
        }).collect(Collectors.toList());
    }

    @Override
    public Guest save(Guest guest) {
        GuestEntity entityToSave = guestMapper.toEntity(guest);
        GuestEntity savedEntity = guestRepository.save(entityToSave);

        if (guest.getGuestPreferences() != null) {
            GuestPreferencesDocument document = guestPreferencesMapper.toDocument(guest.getGuestPreferences());
            document.setGuestId(savedEntity.getId());
            guestPreferencesRepository.save(document);
        }

        GuestPreferencesDocument savedDocument = guestPreferencesRepository.findByGuestId(savedEntity.getId()).orElse(null);

        return guestMapper.toDomain(savedEntity, savedDocument);
    }

    @Override
    public boolean delete(Long id) {
        guestRepository.deleteById(id);
        guestPreferencesRepository.deleteByGuestId(id);
        return !exists(id);
    }
}
