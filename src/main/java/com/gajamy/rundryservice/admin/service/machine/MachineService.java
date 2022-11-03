package com.gajamy.rundryservice.admin.service.machine;

import com.gajamy.rundryservice.admin.dto.MachineDto;
import com.gajamy.rundryservice.admin.param.MachineParam;
import java.util.List;

public interface MachineService {
	List<MachineDto> list();

	MachineDto getMachine(MachineParam param);

	void set(MachineParam param);

	void add(MachineParam param);
}
