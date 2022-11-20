package com.gajamy.rundryservice.reservation.repository;

import com.gajamy.rundryservice.reservation.entity.Reservation;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
	List<Reservation> findByUsageStartDtBetween(LocalDateTime usageStartDt ,LocalDateTime usageEndDt);
}
