package com.dogognon.sohliou.kone.NouvelleProcedure.data;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;



public interface ProcedureRepository extends JpaRepository<Procedure, UUID>{
	
	 List<Procedure> findByDepartementsId(Long departementId);
	 List<Procedure> findByPostesId(Long posteId);
	 List<Procedure> findByServicesId(Long serviceId);
	
}