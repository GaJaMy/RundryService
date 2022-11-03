package com.gajamy.rundryservice.admin.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Entity
@Getter
@Setter
public class Machine {
	@Id
	private String machineModel;
	private String machineType;
	private String size;
	private String laundryType;
	private String state;
	private String laundryCourse;
	private String softener;
	private LocalDateTime startDt;
	private LocalDateTime endDt;
}
