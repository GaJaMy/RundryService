package com.gajamy.rundryservice.reservation.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
	private Long reservationNum;
	private String user;
	private LocalDateTime usageStartDt;
	private LocalDateTime usageEndDt;
	private String machineModel;
	private String laundryType;

	private boolean isAbleReservation;
}
