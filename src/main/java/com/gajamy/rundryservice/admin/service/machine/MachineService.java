package com.gajamy.rundryservice.admin.service.machine;

import com.gajamy.rundryservice.common.ListResponse;
import com.gajamy.rundryservice.common.SingleResponse;

public interface MachineService {
	<T> SingleResponse<T> getMachineInfo(String modelName);
	<T> ListResponse<T> getMachineList();
}
