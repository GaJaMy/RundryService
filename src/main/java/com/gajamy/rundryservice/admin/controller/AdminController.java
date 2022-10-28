package com.gajamy.rundryservice.admin.controller;

import com.gajamy.rundryservice.admin.dto.MachineDto;
import com.gajamy.rundryservice.admin.service.machine.MachineService;
import com.gajamy.rundryservice.common.ListResponse;
import com.gajamy.rundryservice.common.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminController {
	private final MachineService machineService;

	@PostMapping("/admin/machine/list")
	public ListResponse<MachineDto> getMachineList() {
		return null;
	}

	@PostMapping("/admin/machine/{modelName}")
	public SingleResponse<MachineDto> getMachineList(@PathVariable String modelName) {
		return machineService.getMachineInfo(modelName);
	}
}
