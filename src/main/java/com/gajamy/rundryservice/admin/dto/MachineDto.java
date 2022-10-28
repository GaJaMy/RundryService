package com.gajamy.rundryservice.admin.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class MachineDto {
	private String machineModel;
	private String machineType;
	private int size;
	private String laundryType;
	private String options;
	private String state;
	private LocalDateTime startDt;
	private LocalDateTime endDt;


}
