package com.dogognon.sohliou.kone.NouvelleProcedure.data;


import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.dogognon.sohliou.kone.file.data.File;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="procedures")
public class Procedure {
	

	  @Id
	    @GeneratedValue(strategy = GenerationType.UUID)
	    @UuidGenerator(style = UuidGenerator.Style.TIME)
	    private UUID id;
	
	
	
	@OneToOne
	@JoinColumn(nullable = false,updatable=false)
	private File binaire_id;
	
	@NonNull
	@Column(nullable = true,updatable=true,length = 50)
	private String nom_procedure; 
	
	@NonNull
	@Column(nullable = true,updatable=true,length = 255)
	private String commentaire;
	
	@JsonIgnore
	 @ManyToMany(fetch = FetchType.LAZY)
	  @JoinTable(name = "procedure_departement",
	            joinColumns = @JoinColumn(name = "procedure_id"),
	            inverseJoinColumns = @JoinColumn(name = "departement_id"))
	    private Set<Departement> departements = new HashSet<>();
	
	@Transient
	private String departement_id;
	 
	@JsonIgnore
	 @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(name = "procedure_service",
	            joinColumns = @JoinColumn(name = "procedure_id"),
	            inverseJoinColumns = @JoinColumn(name = "service_id"))
	    private Set<Services> services = new HashSet<>();
	
	@Transient
	private String service_id;
	
	@JsonIgnore
	 @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(name = "procedure_poste",
	            joinColumns = @JoinColumn(name = "procedure_id"),
	            inverseJoinColumns = @JoinColumn(name = "poste_id"))
	    private Set<Poste> postes = new HashSet<>();
	
	@Transient
	private String poste_id;

	@CreatedDate
    private Instant createdAt;
	
	
	@LastModifiedDate
    private Instant modifiedAt;
}
