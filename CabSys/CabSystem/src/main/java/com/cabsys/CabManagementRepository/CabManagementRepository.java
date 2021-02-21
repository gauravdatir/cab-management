package com.cabsys.CabManagementRepository;
import org.springframework.data.repository.CrudRepository;

import com.cabsys.cabManagement.CabSystem;

public interface CabManagementRepository extends CrudRepository<CabSystem, Integer> {

}

