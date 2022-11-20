package com.gajamy.rundryservice.reservation.service;

import com.gajamy.rundryservice.admin.entity.Machine;
import com.gajamy.rundryservice.admin.repository.MachineRepository;
import com.gajamy.rundryservice.reservation.dto.ReservationDto;
import com.gajamy.rundryservice.reservation.entity.Reservation;
import com.gajamy.rundryservice.reservation.repository.ReservationRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

	private final ReservationRepository reservationRepository;
	private final MachineRepository machineRepository;
	@Override
	public List<ReservationDto> getReservation(String userId,LocalDateTime date) {
		List<ReservationDto> reservationDtos = new ArrayList<>();

		if ( userId == null||userId.isEmpty() || date == null) {
			return reservationDtos;
		}

		LocalDateTime startDt = date;
		LocalDateTime endDt = date.plusHours(23);

		List<Reservation> reservations =
			reservationRepository.findByUsageStartDtBetween(startDt,endDt);

		for (int i = 0; i < 24; i++) {
			ReservationDto item = ReservationDto.builder()
													.usageStartDt(date.plusHours(i))
													.usageEndDt(date.plusHours(i+1))
													.isAbleReservation(true)
													.build();

			if (LocalDateTime.now().isAfter(item.getUsageEndDt())) {
				item.setAbleReservation(false);
			} else {
				for (Reservation reservation : reservations) {
					if (reservation.getUsageStartDt().equals(item.getUsageStartDt())) {
						item.setReservationNum(reservation.getReservationNum());
						item.setMachineModel(reservation.getMachineModel());
						item.setLaundryType(reservation.getLaundryType());
						item.setUser(reservation.getUserId());
						item.setAbleReservation(false);
						break;
					}
				}
			}

			reservationDtos.add(item);
		}

		return reservationDtos;
	}

	@Override
	public boolean reservation(String userId,String machineModel ,LocalDateTime date) {
		Optional<Machine> optionalMachine = machineRepository.findById(machineModel);
		if (!optionalMachine.isPresent()) {
			return false;
		}

		Machine machine = optionalMachine.get();

		Reservation reservation = Reservation.builder()
			.laundryType(machine.getLaundryType())
			.machineModel(machineModel)
			.usageStartDt(date)
			.usageEndDt(date.plusHours(1))
			.userId(userId)
			.build();

		reservationRepository.save(reservation);

		return true;
	}
}
