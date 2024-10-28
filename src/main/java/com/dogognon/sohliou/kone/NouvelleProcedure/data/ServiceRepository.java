package com.dogognon.sohliou.kone.NouvelleProcedure.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



public interface ServiceRepository extends JpaRepository<Services, Long>{

	//gerer les doublons par libelle
	Boolean existsByLibelle(String libelle);
	Optional<Services> findByLibelle(String libelle);
	List<Services> findByDepartement(Departement dep);



}