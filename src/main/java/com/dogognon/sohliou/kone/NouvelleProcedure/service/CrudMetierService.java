package com.dogognon.sohliou.kone.NouvelleProcedure.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.dogognon.sohliou.kone.NouvelleProcedure.data.Departement;
import com.dogognon.sohliou.kone.NouvelleProcedure.data.Poste;
import com.dogognon.sohliou.kone.NouvelleProcedure.data.Procedure;
import com.dogognon.sohliou.kone.NouvelleProcedure.data.Services;
import com.dogognon.sohliou.kone.NouvelleProcedure.data.payload.DepartementRequest;
import com.dogognon.sohliou.kone.NouvelleProcedure.data.payload.PosteRequest;
import com.dogognon.sohliou.kone.NouvelleProcedure.data.payload.ServiceRequest;
import com.dogognon.sohliou.kone.security.data.Role;
import com.dogognon.sohliou.kone.security.data.User;
import com.dogognon.sohliou.kone.security.oauth2ressourceserver.dto.RegisterRequest;




public interface CrudMetierService {
	
	
	/**
	 * 	 - Département
	 */
	ResponseEntity<String> createDepartement(DepartementRequest c);
	ResponseEntity<String> updateDepartement(Long id,DepartementRequest c);
	ResponseEntity<String> deleteDepartement(Long id);
	ResponseEntity<Departement> findDepartementById(Long id);
	ResponseEntity<List<Departement>> getAllDepartement();
	
	/**
	 * 	 - Service
	 */
	ResponseEntity<String> createService(ServiceRequest c);
	ResponseEntity<String> updateService(Long id,ServiceRequest c);
	ResponseEntity<String> deleteService(Long id);
	ResponseEntity<Services> findServiceById(Long id);
	ResponseEntity<List<Services>> getAllService();
	
	
	/**
	 * 	 - Poste
	 */
	ResponseEntity<String> createPoste(PosteRequest c);
	ResponseEntity<String> updatePoste(Long id,PosteRequest c);
	ResponseEntity<String> deletePoste(Long id);
	ResponseEntity<Poste> findPosteById(Long id);
	ResponseEntity<List<Poste>> getAllPoste();
	
	/**
	 * 	 - Procédure
	 * @throws Exception 
	 */
	
	ResponseEntity<String> createProcedure(String nom,String commentaire,MultipartFile file,String departement_list_id,String service_list_id,String poste_list_id) throws Exception;
	ResponseEntity<String> updateProcedure(UUID id,String nom,String commentaire,MultipartFile file,String departement_list_id) throws Exception;
	ResponseEntity<String> deleteProcedure(UUID id);
	ResponseEntity<Procedure> findProcedureById(UUID id);
	ResponseEntity<List<Procedure>> getAllProcedure();
	ResponseEntity<List<Procedure>> findProcedureByDepartementLibelle(String libelle);
	ResponseEntity<List<Procedure>> findProcedureByServiceLibelle(String libelle);
	ResponseEntity<List<Procedure>> findProcedureByPosteLibelle(String libelle);
	
	
	/**
	 * 	 - Utilisateur
	 */
	ResponseEntity<String> createUtilisateur(RegisterRequest c);
	ResponseEntity<String> updateUtilisateur(String id,RegisterRequest c);
	ResponseEntity<String> deleteUtilisateur(String id);
	ResponseEntity<User> findUtilisateurById(String id);
	ResponseEntity<List<User>> getAllUtilisateur();
	ResponseEntity<List<Role>> getAllRole();
	
	
	
	
	
	
	
	

}
