package com.dogognon.sohliou.kone.NouvelleProcedure.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



public interface PosteRepository extends JpaRepository<Poste, Long>{
	
	//gerer les doublons par libelle
	Boolean existsByLibelle(String libelle);
	Optional<Poste> findByLibelle(String libelle);
	List<Poste> findByServices(Services serv);
	
	
	
}