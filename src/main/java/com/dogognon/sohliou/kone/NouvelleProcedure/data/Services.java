package com.dogognon.sohliou.kone.NouvelleProcedure.data;


import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import lombok.NonNull;


/*@JsonIdentityInfo(
generator = ObjectIdGenerators.PropertyGenerator.class,
property = "id")*/
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="services")
public class Services {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;


	//@NonNull
	@Column(unique=true, nullable = false,updatable=true,length = 100)
	private String libelle;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "departement")
	private Departement departement; // Relation avec le departement
	
	@Transient
	private String departement_id;

	
	@JsonManagedReference
	@OneToMany(mappedBy = "services")
	private List<Poste> poste; // Relation avec les poste


	@CreatedDate
	private Instant createdAt;


	@LastModifiedDate
	private Instant modifiedAt;
}
