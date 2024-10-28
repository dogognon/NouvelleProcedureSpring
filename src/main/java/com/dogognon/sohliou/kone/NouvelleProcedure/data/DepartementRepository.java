package com.dogognon.sohliou.kone.NouvelleProcedure.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



public interface DepartementRepository extends JpaRepository<Departement, Long>{
	
	//gerer les doublons par libelle
	Boolean existsByLibelle(String libelle);
	Optional<Departement> findByLibelle(String libelle);
	
	
	
}