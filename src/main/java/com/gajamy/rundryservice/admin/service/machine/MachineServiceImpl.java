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
}
