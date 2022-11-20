package com.gajamy.rundryservice.reservation.controller;

import com.gajamy.rundryservice.admin.dto.MachineDto;
import com.gajamy.rundryservice.admin.service.machine.MachineService;
import com.gajamy.rundryservice.reservation.dto.ReservationDto;
import com.gajamy.rundryservice.reservation.service.ReservationService;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class ReservationController {
	private final MachineService machineService;
	private final ReservationService reservationService;

	@GetMapping("/washing/list")
	public String washingList(Model model) {
		List<MachineDto> machineDtoList = machineService.list();
		model.addAttribute("list",machineDtoList);
		return "washing/list";
	}

	@GetMapping("/washing/reservation")
	public String reservation(Model model,@RequestParam String machineModel) {
		model.addAttribute("machineModel",machineModel);
		model.addAttribute("curDate",LocalDate.now());
		return "washing/reservation";
	}

	@GetMapping("/washing/reservation/do")
	public String reservationSubmit(Model model, Principal principal,
		@RequestParam String machineModel,
		@RequestParam String date) {
		LocalDateTime startDate = LocalDateTime.parse(date);
		boolean result = reservationService.reservation(principal.getName(),machineModel,startDate);
		model.addAttribute("result",result);
		return "washing/reservation_complete";
	}
	@GetMapping("/washing/reservation/search")
	public String inquireReservation(Model model,Principal principal
		,@RequestParam String machineModel
		,@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date) {


		LocalDateTime startDay = date.atStartOfDay();
		if (LocalDateTime.now().isAfter(startDay.plusDays(1))) {
			model.addAttribute("message","조회 일자가 잘못 되엇습니다.");
			return "error/error";
		}

		List<ReservationDto> reservationDtoList =
			reservationService.getReservation(principal.getName(), startDay);

		model.addAttribute("machineModel",machineModel);
		model.addAttribute("list",reservationDtoList);
		model.addAttribute("curDate",date);
		return "washing/reservation";
	}
}
