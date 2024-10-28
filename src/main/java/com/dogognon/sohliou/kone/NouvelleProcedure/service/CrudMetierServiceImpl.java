package com.dogognon.sohliou.kone.NouvelleProcedure.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dogognon.sohliou.kone.NouvelleProcedure.data.Departement;
import com.dogognon.sohliou.kone.NouvelleProcedure.data.DepartementRepository;
import com.dogognon.sohliou.kone.NouvelleProcedure.data.Poste;
import com.dogognon.sohliou.kone.NouvelleProcedure.data.PosteRepository;
import com.dogognon.sohliou.kone.NouvelleProcedure.data.Procedure;
import com.dogognon.sohliou.kone.NouvelleProcedure.data.ProcedureRepository;
import com.dogognon.sohliou.kone.NouvelleProcedure.data.ServiceRepository;
import com.dogognon.sohliou.kone.NouvelleProcedure.data.Services;
import com.dogognon.sohliou.kone.NouvelleProcedure.data.payload.DepartementRequest;
import com.dogognon.sohliou.kone.NouvelleProcedure.data.payload.PosteRequest;
import com.dogognon.sohliou.kone.NouvelleProcedure.data.payload.ServiceRequest;
import com.dogognon.sohliou.kone.file.FileStorageService;
import com.dogognon.sohliou.kone.file.data.File;
import com.dogognon.sohliou.kone.file.data.FileRepository;
import com.dogognon.sohliou.kone.security.data.Role;
import com.dogognon.sohliou.kone.security.data.RoleRepository;
import com.dogognon.sohliou.kone.security.data.User;
import com.dogognon.sohliou.kone.security.data.UserRepository;
import com.dogognon.sohliou.kone.security.exception.ResourceNotFoundException;
import com.dogognon.sohliou.kone.security.oauth2ressourceserver.dto.RegisterRequest;
//import com.nimbusds.oauth2.sdk.util.ListUtils;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CrudMetierServiceImpl implements CrudMetierService {

	@Autowired
	DepartementRepository departementRepository;

	@Autowired
	ProcedureRepository procedureRepository;

	@Autowired
	PosteRepository posteRepository;

	@Autowired
	ServiceRepository serviceRepository;

	@Autowired
	FileRepository fileRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;

	
	@Autowired
	private FileStorageService fileStorageService;






	@Override
	public ResponseEntity<String> createDepartement(DepartementRequest c) {
		if(departementRepository.existsByLibelle(c.getLibelle())) {
			return ResponseEntity.ok("Le département "+c.getLibelle()+" existe déjà dans la base.");
		}else {
			Departement newd = new Departement();
			newd.setLibelle(c.getLibelle());
		
			final Departement departementSaved = departementRepository.save(newd);

		//	if(departementSaved != null) {
				return ResponseEntity.status(HttpStatus.OK).body("Le département "+departementSaved.getLibelle()+" a bien été ajouté à la base.");
		//	}else {
		//		return ResponseEntity.status(HttpStatus.RESET_CONTENT).body("Une erreur a été detécté.Veuillez réessayer svp ...");
		//	}
		}

	}


	@Override
	public ResponseEntity<String> updateDepartement(Long id, DepartementRequest c) {
		Departement newd = departementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Le département","id",id));
		if (c.getLibelle()!=null) {
			newd.setLibelle(c.getLibelle());
		}

		final Departement departementSaved = departementRepository.save(newd);

		//if(departementSaved != null) {
			return ResponseEntity.status(HttpStatus.OK).body("Le département "+departementSaved.getLibelle()+" a bien été modifié.");
		//}else {
		//	return ResponseEntity.status(HttpStatus.RESET_CONTENT).body("Une erreur a été detécté.Veuillez réessayer svp ...");
		//}


	}


	@Override
	public ResponseEntity<String> deleteDepartement(Long id) {
		Departement d = departementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Le département","id", id));
		if(!d.getService().isEmpty()) {
			//on supprime d'abord tous ses tickets
			d.getService().forEach(s->{
				s.getPoste().forEach(p->{
					posteRepository.delete(p);
				});
				serviceRepository.delete(s);
			});
		}
		//gerer les procedures avant de supprimer 
		departementRepository.delete(d);
		return ResponseEntity.status(HttpStatus.OK).body("Le département : "+d.getLibelle()+" a bien été supprimé");
	}


	@Override
	public ResponseEntity<Departement> findDepartementById(Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(departementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Le département ", "id", id)));
	}


	@Override
	public ResponseEntity<List<Departement>> getAllDepartement() {
		List<Departement> departement_list = departementRepository.findAll();
		departement_list.forEach(dep->{
		List<Services> service_list = serviceRepository.findByDepartement(dep);
			service_list.forEach(serv->{
				List<Poste> poste_list = posteRepository.findByServices(serv);
				serv.setPoste(poste_list);
				});
			dep.setService(service_list);
		});
		return ResponseEntity.status(HttpStatus.OK).body(departement_list);	
	}


	@Override
	public ResponseEntity<String> createProcedure(String nom,String commentaire, MultipartFile file,
			String departement_list_id, String service_list_id, String poste_list_id) throws Exception {
		Procedure newd = new Procedure();
		newd.setNom_procedure(nom);
		newd.setCommentaire(commentaire);
		//True : save to database false : save on filedir
		File fileSaved = fileStorageService.Televerser(file,false);
		if(!fileSaved.getId().isEmpty()) {
			newd.setBinaire_id(fileSaved);
		}else {
			return ResponseEntity.status(HttpStatus.RESET_CONTENT).body("Le fichier est manquant.Veuillez selectionner le fichier.");
		}		
		String[] valDepartement = departement_list_id.split(",");
		System.err.println(valDepartement.length);
		if(valDepartement.length>0){
			Set<Departement> departement_list = new HashSet<Departement>();
			for (String libelle : valDepartement) {
				// recherhcer le departement par le libelle
				Optional<Departement> newa = departementRepository.findByLibelle(libelle);
				if(newa.isPresent()) {
					departement_list.add(newa.get());
				}
				
			}
			newd.setDepartements(departement_list);
		}
			
			String[] valService = service_list_id.split(",");
			System.err.println(valService.length);
			if(valService.length>0){
				Set<Services> services_list = new HashSet<Services>();
				for (String libelle : valService) {
					// recherhcer le departement par le libelle
					Optional<Services> newa = serviceRepository.findByLibelle(libelle);
					if(newa.isPresent()) {
						services_list.add(newa.get());
					}
					
				}

				newd.setServices(services_list);
			}

				String[] valPoste = poste_list_id.split(",");
				System.err.println(valPoste.length);
				if(valPoste.length>0){
					Set<Poste> poste_list = new HashSet<Poste>();
					for (String libelle : valPoste) {
						// recherhcer le departement par le libelle
						Optional<Poste> newa = posteRepository.findByLibelle(libelle);
						if(newa.isPresent()) {
							poste_list.add(newa.get());
						}
					}

					newd.setPostes(poste_list);
				}
				if(valDepartement.length == 0 && valService.length == 0 && valPoste.length==0) {
					return ResponseEntity.status(HttpStatus.RESET_CONTENT).body("Veuillez selectionner un departement ou un service ou un poste.");
				}

				final Procedure procedureSaved = procedureRepository.save(newd);

				//if(procedureSaved != null) {
					return ResponseEntity.status(HttpStatus.OK).body("La procédure "+procedureSaved.getBinaire_id().getFileName()+" a bien été ajoutée a la base.");
				//}else {
			//		return ResponseEntity.status(HttpStatus.RESET_CONTENT).body("Une erreur a été detécté.Veuillez réessayer svp ...");
			//	}
			
		}

			@Override
			public ResponseEntity<String> updateProcedure(UUID id,String nom, String commentaire,MultipartFile file,String departement_list_libelle) throws Exception {

				/*System.out.println(commentaire+file.getOriginalFilename()+departement_list_id);
		String[] val = departement_list_id.split(",");
		List dep = new ArrayList<DepartementEnfantRequest>();
		for (String id : val) {
			crud.findDepartementByLi(id);
			dep.add(dep)
		}*/
				Procedure newd = procedureRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("La procédure  ", "id", id));
				if(nom!= null) {
					newd.setNom_procedure(nom);
				}
				if(commentaire!= null) {
					newd.setCommentaire(commentaire);
				}
				if(!file.isEmpty()) {
					//True : save to database false : save on filedir
					File fileName = fileStorageService.Televerser(file,false);
					newd.setBinaire_id(fileName);
				}
				String [] val = departement_list_libelle.split(",");
				if(!(val.length==0)){
					Set<Departement> departement_list = new HashSet<Departement>();
					for (String libelle : val) {
						// recherhcer le departement par le libelle
						Departement newa = departementRepository.findByLibelle(libelle).orElseThrow(() -> new ResourceNotFoundException("Le département ","id",libelle));
						departement_list.add(newa);
					}
					newd.setDepartements(departement_list);
				}

				final Procedure procedureSaved = procedureRepository.save(newd);

				//if(procedureSaved != null) {
					return ResponseEntity.status(HttpStatus.OK).body("La procédure "+procedureSaved.getBinaire_id().getFileName()+" a bien été ajoutée a la base.");
				//}else {
				//	return ResponseEntity.status(HttpStatus.RESET_CONTENT).body("Une erreur a été detécté.Veuillez réessayer svp ...");
				//}
			}


			@Override
			public ResponseEntity<String> deleteProcedure(UUID id) {
				Procedure p = procedureRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("La procédure  ","id", id));
				if(!(p.getBinaire_id().getSize()==0)) {
					File f = p.getBinaire_id();
					p.setDepartements(null);
					p.setBinaire_id(null);
					fileRepository.delete(f);
				}
				procedureRepository.delete(p);
				return ResponseEntity.status(HttpStatus.OK).body("La procédure : "+p.getBinaire_id().getFileName()+" a bien été supprimé");

			}


			@Override
			public ResponseEntity<Procedure> findProcedureById(UUID id) {
				return ResponseEntity.status(HttpStatus.OK).body(procedureRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("La procédure  ", "id", id)));
			}


			@Override
			public ResponseEntity<List<Procedure>> getAllProcedure() {
				List<Procedure> va = procedureRepository.findAll();
				/*va.forEach(p->{
					p.setDepartements(null);
					p.setServices(null);
					p.setPostes(null);
				});*/
				return ResponseEntity.status(HttpStatus.OK).body(va);
			}


			@Override
			public ResponseEntity<List<Procedure>> findProcedureByDepartementLibelle(String libelle) {
				Departement newd = departementRepository.findByLibelle(libelle).orElseThrow(() -> new ResourceNotFoundException("Le département","id",libelle));
				List<Procedure> tes = procedureRepository.findByDepartementsId(newd.getId());
				tes.forEach(ele->{
					ele.getBinaire_id().setFileDownloadUri(ServletUriComponentsBuilder.fromCurrentContextPath()
							.path("/api/file/download/")
							.path(ele.getBinaire_id().getId())
							.toUriString());
					ele.getBinaire_id().setSize(ele.getBinaire_id().getSize());
				});


				return ResponseEntity.status(HttpStatus.OK).body(tes);

			}


			@Override
			public ResponseEntity<String> createService(ServiceRequest c) {
				if(serviceRepository.existsByLibelle(c.getLibelle())) {
					return ResponseEntity.ok("Le service "+c.getLibelle()+" existe déjà dans la base.");
				}else {
					Services newd = new Services();
					newd.setLibelle(c.getLibelle());
					Departement dep = departementRepository.findByLibelle(c.getDepartement()).orElseThrow(() -> new ResourceNotFoundException("Le département ", "libelle",c.getDepartement()));
					newd.setDepartement(dep);
					final Services serviceSaved = serviceRepository.save(newd);

				//	if(serviceSaved != null) {
						return ResponseEntity.status(HttpStatus.OK).body("Le service "+serviceSaved.getLibelle()+" a bien été ajouté à la base.");
				//	}else {
				//		return ResponseEntity.status(HttpStatus.RESET_CONTENT).body("Une erreur a été detécté.Veuillez réessayer svp ...");
				//	}
				}
			}


			@Override
			public ResponseEntity<String> updateService(Long id, ServiceRequest c) {
				// TODO Auto-generated method stub
				return null;
			}


			@Override
			public ResponseEntity<String> deleteService(Long id) {
				Services s = serviceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Le service","id", id));
				if(!s.getPoste().isEmpty()) {
					s.getPoste().forEach(p->{
						posteRepository.delete(p);
					});

				}
				serviceRepository.delete(s);
				return ResponseEntity.status(HttpStatus.OK).body("Le département : "+s.getLibelle()+" a bien été supprimé");

			}


			@Override
			public ResponseEntity<Services> findServiceById(Long id) {
				return ResponseEntity.status(HttpStatus.OK).body(serviceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Le service  ", "id", id)));
			}


			@Override
			public ResponseEntity<List<Services>> getAllService() {
				List<Services> service_list = serviceRepository.findAll();
				service_list.forEach(serv->{
				serv.setDepartement_id(serv.getDepartement().getLibelle());
				List<Poste> poste_list = posteRepository.findByServices(serv);
				serv.setPoste(poste_list);
				});
				return ResponseEntity.status(HttpStatus.OK).body(service_list);	
			}


			@Override
			public ResponseEntity<String> createPoste(PosteRequest c) {
				if(posteRepository.existsByLibelle(c.getLibelle())) {
					return ResponseEntity.ok("Le poste "+c.getLibelle()+" existe déjà dans la base.");
				}else {
					Poste newd = new Poste();
					newd.setLibelle(c.getLibelle());
					Services serv = serviceRepository.findByLibelle(c.getService()).orElseThrow(() -> new ResourceNotFoundException("Le service ", "libelle",c.getService()));
					newd.setServices(serv);
					final Poste serviceSaved = posteRepository.save(newd);
					//if(serviceSaved != null) {
						return ResponseEntity.status(HttpStatus.OK).body("Le poste "+serviceSaved.getLibelle()+" a bien été ajouté à la base.");
				//	}else {
				//		return ResponseEntity.status(HttpStatus.RESET_CONTENT).body("Une erreur a été detécté.Veuillez réessayer svp ...");
				//	}
				}
			}


			@Override
			public ResponseEntity<String> updatePoste(Long id, PosteRequest c) {
				// TODO Auto-generated method stub
				return null;
			}


			@Override
			public ResponseEntity<String> deletePoste(Long id) {
				Poste p = posteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Le service","id", id));
				/*if(!p.getUser().isEmpty()) {
					p.getUser().forEach(u->{
						userRepository.delete(u);
					});

				}*/
				posteRepository.delete(p);
				return ResponseEntity.status(HttpStatus.OK).body("Le poste : "+p.getLibelle()+" a bien été supprimé");

			}


			@Override
			public ResponseEntity<Poste> findPosteById(Long id) {
				return ResponseEntity.status(HttpStatus.OK).body(posteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Le poste  ", "id", id)));
			}


			@Override
			public ResponseEntity<List<Poste>> getAllPoste() {
				List<Poste> poste_list = posteRepository.findAll();
				poste_list.forEach(pos->{
					pos.setService_id(pos.getServices().getLibelle());
				});
				return ResponseEntity.status(HttpStatus.OK).body(poste_list);	
			
			}


			@Override
			public ResponseEntity<List<Procedure>> findProcedureByServiceLibelle(String libelle) {
				Services newd = serviceRepository.findByLibelle(libelle).orElseThrow(() -> new ResourceNotFoundException("Le département","id",libelle));
				List<Procedure> tes = procedureRepository.findByServicesId(newd.getId());
				tes.forEach(ele->{
					ele.getBinaire_id().setFileDownloadUri(ServletUriComponentsBuilder.fromCurrentContextPath()
							.path("/api/file/download/")
							.path(ele.getBinaire_id().getId())
							.toUriString());
					ele.getBinaire_id().setSize(ele.getBinaire_id().getSize());
				});


				return ResponseEntity.status(HttpStatus.OK).body(tes);
			}


			@Override
			public ResponseEntity<List<Procedure>> findProcedureByPosteLibelle(String libelle) {
				Poste newd = posteRepository.findByLibelle(libelle).orElseThrow(() -> new ResourceNotFoundException("Le département","id",libelle));
				List<Procedure> tes = procedureRepository.findByPostesId(newd.getId());
				tes.forEach(ele->{
					ele.getBinaire_id().setFileDownloadUri(ServletUriComponentsBuilder.fromCurrentContextPath()
							.path("/api/file/download/")
							.path(ele.getBinaire_id().getId())
							.toUriString());
					ele.getBinaire_id().setSize(ele.getBinaire_id().getSize());
				});


				return ResponseEntity.status(HttpStatus.OK).body(tes);
			}


			@Override
			public ResponseEntity<String> createUtilisateur(RegisterRequest c) {
				// TODO Auto-generated method stub
				return null;
			}


			@Override
			public ResponseEntity<String> updateUtilisateur(String id, RegisterRequest c) {
				// TODO Auto-generated method stub
				return null;
			}


			@Override
			public ResponseEntity<String> deleteUtilisateur(String id) {
				User p = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("L'utilisateur","id", id));
				userRepository.delete(p);
				return ResponseEntity.status(HttpStatus.OK).body("L'utilisateur : "+p.getNomprenoms()+" a bien été supprimé");
			}


			@Override
			public ResponseEntity<User> findUtilisateurById(String id) {
				return ResponseEntity.status(HttpStatus.OK).body(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("L'utilisateur  ", "id", id)));
			}


			@Override
			public ResponseEntity<List<User>> getAllUtilisateur() {
				List<User> user_list = userRepository.findAll();
				user_list.forEach(user->{
					System.out.println(user.getCreatedAt());
					user.setPoste_id(user.getPostes().getLibelle());
				});
				return ResponseEntity.status(HttpStatus.OK).body(user_list);	
			}


			@Override
			public ResponseEntity<List<Role>> getAllRole() {
				return ResponseEntity.status(HttpStatus.OK).body(roleRepository.findAll());	
			}


		}