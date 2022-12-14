package com.gajamy.rundryservice.admin.service.machine;

import com.gajamy.rundryservice.admin.dto.MachineDto;
import com.gajamy.rundryservice.admin.param.MachineParam;
import java.util.List;

public interface MachineService {
	List<MachineDto> list();

	MachineDto getMachine(MachineParam param);
	MachineDto getMachineName(String machineModel);

	boolean set(MachineDto machineDto);

	void add(MachineParam param);
}
