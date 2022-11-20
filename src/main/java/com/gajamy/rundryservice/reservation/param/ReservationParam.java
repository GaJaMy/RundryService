package com.gajamy.rundryservice.reservation.param;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ReservationParam {
	String userId;
	LocalDateTime date;
}
