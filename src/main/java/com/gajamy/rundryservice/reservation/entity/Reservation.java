package com.gajamy.rundryservice.reservation.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Entity
@Getter
@Builder
@AllArgsConstructor
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reservationNum;

	private String userId;
	private LocalDateTime usageStartDt;
	private LocalDateTime usageEndDt;
	private String machineModel;
	private String laundryType;
}
