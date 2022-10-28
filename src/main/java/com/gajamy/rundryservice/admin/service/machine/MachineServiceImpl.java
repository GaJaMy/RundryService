package com.gajamy.rundryservice.admin.service.machine;

import com.gajamy.rundryservice.admin.dto.MachineDto;
import com.gajamy.rundryservice.admin.entity.Machine;
import com.gajamy.rundryservice.admin.repository.MachineRepository;
import com.gajamy.rundryservice.common.ListResponse;
import com.gajamy.rundryservice.common.SingleResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MachineServiceImpl implements MachineService{

	private final MachineRepository machineRepository;

	@Override
	public <T> SingleResponse<T> getMachineInfo(String modelName) {
		//repository 조회
		//실패 종류 - 해당하는 모델이 없다.
		Optional<Machine> optionalMachine = machineRepository.findById(modelName);

		SingleResponse singleResponse = new SingleResponse();
		if (!optionalMachine.isPresent()) {
			singleResponse.setResponseResult(false, 101,
				"Not Found Machine");
			singleResponse.MakeResponse(false, 101,
				"Not Found Machine",null);
			return singleResponse;
		}

		Machine machine = optionalMachine.get();

		MachineDto machineDto = MachineDto.builder()
			.machineModel(machine.getMachineModel())
			.machineType(machine.getMachineType())
			.size(machine.getSize())
			.laundryType(machine.getLaundryType())
			.options(machine.getOptions())
			.state(machine.getState())
			.startDt(machine.getStartDt())
			.endDt(machine.getEndDt())
			.build();

		singleResponse.MakeResponse(true,100,"",machineDto);
		return singleResponse;
	}

	@Override
	public <T> ListResponse<T> getMachineList() {
		return null;
	}
}
