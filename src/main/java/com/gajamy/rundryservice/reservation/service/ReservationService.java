package com.gajamy.rundryservice.reservation.service;

import com.gajamy.rundryservice.reservation.dto.ReservationDto;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {
	List<ReservationDto> getReservation(String userId,LocalDateTime date);
	boolean reservation(String userId,String machineModel,LocalDateTime date);
}
