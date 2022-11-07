package com.gajamy.rundryservice.admin.service.machine;

import com.gajamy.rundryservice.admin.dto.MachineDto;
import com.gajamy.rundryservice.admin.entity.Machine;
import com.gajamy.rundryservice.admin.param.MachineParam;
import com.gajamy.rundryservice.admin.repository.MachineRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MachineServiceImpl implements MachineService{

	private final MachineRepository machineRepository;

	@Override
	public List<MachineDto> list() {
		List<Machine> machineList = machineRepository.findAll();

		List<MachineDto> result = new ArrayList<>();
		for (Machine m : machineList) {
			result.add(MachineDto.builder()
					.machineModel(m.getMachineModel())
					.machineType(m.getMachineType())
					.size(m.getSize())
					.laundryType(m.getLaundryType())
					.state(m.getState())
					.laundryCourse(m.getLaundryCourse())
					.softener(m.getSoftener())
					.startDt(m.getStartDt())
					.endDt(m.getEndDt())
				.build());
		}

		return result;
	}

	@Override
	public MachineDto getMachine(MachineParam param) {
		String parseModel = param.getMachineModel().split(",")[0];
		Optional<Machine> optionalMachine = machineRepository.findById(parseModel);

		if (!optionalMachine.isPresent()) {
			return null;
		}
		Machine machine = optionalMachine.get();

		return MachineDto.builder()
			.machineModel(machine.getMachineModel())
			.machineType(machine.getMachineType())
			.size(machine.getSize())
			.laundryType(machine.getLaundryType())
			.state(machine.getState())
			.laundryCourse(machine.getLaundryCourse())
			.softener(machine.getSoftener())
			.startDt(machine.getStartDt())
			.endDt(machine.getEndDt())
			.build();
	}

	@Override
	public boolean set(MachineDto machineDto) {
		String parseModel = machineDto.getMachineModel().split(",")[0];

		Optional<Machine> optionalMachine = machineRepository.findById(parseModel);

		Machine machine = optionalMachine.get();

		if (machine == null) {
			return false;
		}

		machine.setMachineModel(machineDto.getMachineModel());
		machine.setMachineType(machineDto.getMachineType());
		machine.setSize(machineDto.getSize());
		machine.setLaundryType(machineDto.getLaundryType());

		machineRepository.save(machine);
		return true;
	}

	@Override
	public void add(MachineParam param) {
		Machine machine = new Machine();

		machine.setMachineModel(param.getMachineModel());
		machine.setMachineType(param.getMachineType());
		machine.setSize(param.getSize());
		machine.setLaundryType(param.getLaundryType());

		machineRepository.save(machine);
	}
}
