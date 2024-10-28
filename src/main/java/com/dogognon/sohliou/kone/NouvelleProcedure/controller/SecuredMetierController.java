package com.dogognon.sohliou.kone.NouvelleProcedure.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dogognon.sohliou.kone.NouvelleProcedure.data.Departement;
import com.dogognon.sohliou.kone.NouvelleProcedure.data.Poste;
import com.dogognon.sohliou.kone.NouvelleProcedure.data.Procedure;
import com.dogognon.sohliou.kone.NouvelleProcedure.data.Services;
import com.dogognon.sohliou.kone.NouvelleProcedure.data.payload.DepartementRequest;
import com.dogognon.sohliou.kone.NouvelleProcedure.data.payload.PosteRequest;
import com.dogognon.sohliou.kone.NouvelleProcedure.data.payload.ServiceRequest;
import com.dogognon.sohliou.kone.NouvelleProcedure.service.CrudMetierService;
import com.dogognon.sohliou.kone.security.oauth2ressourceserver.dto.RegisterRequest;
import com.dogognon.sohliou.kone.security.SecurityService;
import com.dogognon.sohliou.kone.security.data.Role;
import com.dogognon.sohliou.kone.security.data.User;

@RestController
@RequestMapping("/api/secured/")
public class SecuredMetierController {
	
	
	@Autowired
	private CrudMetierService crud;

	@Autowired
	private SecurityService service;
	


	@PostMapping("departement")
	@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	ResponseEntity<String> createDepartement(Authentication authentication,@RequestBody DepartementRequest a){
		System.out.println(authentication.getName());
		return crud.createDepartement(a);

	}
	@PutMapping("departement/{iddepartement}")
	@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	ResponseEntity<String> updateDepartement(@PathVariable(value = "iddepartement") Long id,@RequestBody DepartementRequest d){
		return crud.updateDepartement(id,d);
	}
	
	@GetMapping("departements")
	@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	public ResponseEntity<List<Departement>> findAllDepartements() {
		return crud.getAllDepartement();
	}

	
	
	@GetMapping("departement_by_id/{iddepartement}")
	@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	public ResponseEntity<Departement> findDepartementById(@PathVariable(value = "iddepartement") Long id) {
		return crud.findDepartementById(id);
	}

	
	@DeleteMapping("departement/{iddepartement}")
	@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	ResponseEntity<String> deleteDepartement(@PathVariable(value = "iddepartement") Long id){
		return crud.deleteDepartement(id);
	}
	@PostMapping("service")
	@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	ResponseEntity<String> createService(Authentication authentication,@RequestBody ServiceRequest a){
		System.out.println(authentication.getName());
		return crud.createService(a);

	}
	@PutMapping("service/{idservice}")
	@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	ResponseEntity<String> updateService(@PathVariable(value = "idservice") Long id,@RequestBody ServiceRequest d){
		return crud.updateService(id,d);
	}
	
	@GetMapping("services")
	@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	public ResponseEntity<List<Services>> findAllServices() {
		return crud.getAllService();
	}

	
	
	@GetMapping("service_by_id/{idservice}")
	@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	public ResponseEntity<Services> findServiceById(@PathVariable(value = "idservice") Long id) {
		return crud.findServiceById(id);
	}

	
	@DeleteMapping("service/{idservice}")
	@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	ResponseEntity<String> deleteService(@PathVariable(value = "idservice") Long id){
		return crud.deleteService(id);
	}
	@PostMapping("poste")
	@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	ResponseEntity<String> createPoste(Authentication authentication,@RequestBody PosteRequest a){
		System.out.println(authentication.getName());
		return crud.createPoste(a);

	}
	@PutMapping("poste/{idposte}")
	@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	ResponseEntity<String> updatePoste(@PathVariable(value = "idposte") Long id,@RequestBody PosteRequest d){
		return crud.updatePoste(id,d);
	}
	
	@GetMapping("postes")
	@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	public ResponseEntity<List<Poste>> findAllPostes() {
		return crud.getAllPoste();
	}

	
	
	@GetMapping("poste_by_id/{idposte}")
	@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	public ResponseEntity<Poste> findPosteById(@PathVariable(value = "idposte") Long id) {
		return crud.findPosteById(id);
	}

	
	@DeleteMapping("poste/{idposte}")
	@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	ResponseEntity<String> deletePoste(@PathVariable(value = "idposte") Long id){
		return crud.deletePoste(id);
	}
	
	@PostMapping("utilisateur")
	@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	ResponseEntity<String> createUtilisateur(Authentication authentication,@RequestBody RegisterRequest registerRequest){
		System.out.println(authentication.getName());
		System.err.println(registerRequest.toString());
		return service.registerUser(registerRequest);

	}
	@PutMapping("utilisateur/{idutilisateur}")
	@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	ResponseEntity<String> updateUtilisateur(@PathVariable(value = "idutilisateur") String id,@RequestBody RegisterRequest d){
		return crud.updateUtilisateur(id,d);
	}
	
	@GetMapping("utilisateurs")
	@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	public ResponseEntity<List<User>> findAllUtilisateurs() {
		return crud.getAllUtilisateur();
	}

	
	
	@GetMapping("utilisateur_by_id/{idutilisateur}")
	@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	public ResponseEntity<User> findUtilisateurById(@PathVariable(value = "idutilisateur") String id) {
		return crud.findUtilisateurById(id);
	}

	
	@DeleteMapping("utilisateur/{idutilisateur}")
	@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	ResponseEntity<String> deleteUtilisateur(@PathVariable(value = "idutilisateur") String id){
		return crud.deleteUtilisateur(id);
	}

	//
	//ResponseEntity<String> createProcedure(Authentication authentication,@RequestBody ProcedureRequest a) throws Exception{
	@PostMapping("procedure")
	@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	ResponseEntity<String> createProcedure(@RequestParam("nom") String nom,@RequestParam("commentaire") String commentaire,@RequestParam("document") MultipartFile file,@RequestParam("list_departement_id") String departement_list_id,@RequestParam("list_service_id") String service_list_id,@RequestParam("list_poste_id") String poste_list_id) throws Exception{
		return crud.createProcedure(nom,commentaire,file,departement_list_id, service_list_id, poste_list_id);
	}
	
	
	
	//ObjectReader reader = new ObjectMapper().readerFor(new TypeReference<List<CategoryRequestMobil>>() {
	//});
	//reading the certificates property into a list
	//List<CategoryRequestMobil> data = reader.readValue(root.at("/mycategorylist")); 
	
	
	
	@PutMapping("procedure/{idprocedure}")
	@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	ResponseEntity<String> updateProcedure(@PathVariable(value = "idprocedure") UUID id,@RequestParam("nom") String nom,@RequestParam("commentaire") String commentaire,@RequestParam("document") MultipartFile file,@RequestParam("list_departement_id") String departement_list_id) throws Exception{
		return crud.updateProcedure(id,nom,commentaire,file,departement_list_id);
	}
	
	@GetMapping("procedure_by_id/{idprocedure}")
	public ResponseEntity<Procedure> findProcedureById(@PathVariable(value = "idprocedure") UUID id) {
		return crud.findProcedureById(id);
	}
	
	@GetMapping("procedure_by_departement_libelle/{libelle_departement}")
	//@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	public ResponseEntity<List<Procedure>> findProcedureByDepartementLibelle(@PathVariable(value = "libelle_departement") String libelle) {
		return crud.findProcedureByDepartementLibelle(libelle);
	}
	@GetMapping("procedure_by_poste_libelle/{libelle_poste}")
	//@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	public ResponseEntity<List<Procedure>> findProcedureByPosteLibelle(@PathVariable(value = "libelle_poste") String libelle) {
		return crud.findProcedureByPosteLibelle(libelle);
	}

	@GetMapping("procedure_by_service_libelle/{libelle_service}")
	//@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	public ResponseEntity<List<Procedure>> findProcedureByServiceLibelle(@PathVariable(value = "libelle_service") String libelle) {
		return crud.findProcedureByServiceLibelle(libelle);
	}
	
	@GetMapping("procedures")
	//@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	public ResponseEntity<List<Procedure>> getAllProcedure() {
		return crud.getAllProcedure();
	}

	
	
	@DeleteMapping("procedure/{idprocedure}")
	@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	ResponseEntity<String> deleteProcedure(@PathVariable(value = "idprocedure") UUID id){
		return crud.deleteProcedure(id);
	}

	
	@GetMapping("roles")
	public ResponseEntity<List<Role>> findAllRoles() {
		return crud.getAllRole();
	}

}
