package com.docencia.hotel_puerto.persistence.repository.jpa;

import com.docencia.hotel_puerto.persistence.jpa.entity.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestJpaRepository extends JpaRepository<GuestEntity, Long> {
}
