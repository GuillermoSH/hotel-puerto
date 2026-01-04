package com.docencia.hotel_puerto.persistence.repository.jpa;

import com.docencia.hotel_puerto.persistence.jpa.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingJpaRepository extends JpaRepository<BookingEntity, Long> {
    @Query("SELECT b FROM BookingEntity b WHERE b.room.id = :roomId AND (b.checkIn <= :endDate AND b.checkOut >= :startDate)")
    List<BookingEntity> findByRoomEntityAndDateRange(@Param("roomId") String roomId,
                                               @Param("startDate") String startDate,
                                               @Param("endDate") String endDate);
}
