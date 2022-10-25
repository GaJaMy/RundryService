package com.gajamy.rundryservice.reservation.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Entity
public class Reservation {
	@Id
	private int reservationNum;
	private String user;
	private LocalDateTime usageStartDt;
	private LocalDateTime usageEndDt;
	private int maineNum;
	private String rundryType;
}
