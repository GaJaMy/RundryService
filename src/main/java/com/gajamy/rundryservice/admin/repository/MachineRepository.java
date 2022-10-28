package com.gajamy.rundryservice.admin.repository;

import com.gajamy.rundryservice.admin.entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineRepository extends JpaRepository<Machine,String> {

}
