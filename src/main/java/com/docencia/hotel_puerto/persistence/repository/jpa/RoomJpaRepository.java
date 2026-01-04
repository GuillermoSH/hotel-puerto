package com.docencia.hotel_puerto.persistence.repository.jpa;

import com.docencia.hotel_puerto.persistence.jpa.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomJpaRepository extends JpaRepository<RoomEntity, Long> {
}
