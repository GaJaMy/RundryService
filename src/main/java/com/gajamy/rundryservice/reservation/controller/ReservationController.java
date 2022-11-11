package com.gajamy.rundryservice.reservation.controller;

import com.gajamy.rundryservice.admin.dto.MachineDto;
import com.gajamy.rundryservice.admin.service.machine.MachineService;
import com.gajamy.rundryservice.reservation.service.ReservationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ReservationController {
	private final MachineService machineService;

	@GetMapping("/washing/list")
	public String washingList(Model model) {
		List<MachineDto> machineDtoList = machineService.list();
		model.addAttribute("list",machineDtoList);
		return "washing/list";
	}
}
