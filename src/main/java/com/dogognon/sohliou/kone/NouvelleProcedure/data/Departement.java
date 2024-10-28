package com.dogognon.sohliou.kone.NouvelleProcedure.data;


import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/*@JsonIdentityInfo(
	    generator = ObjectIdGenerators.PropertyGenerator.class,
	    property = "id")*/
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="departement")
public class Departement {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	
	@Column(unique=true, nullable = false,updatable=true,length = 100)
	private String libelle;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "departement")
	private List<Services> service; // Relation avec les departements enfants

	
	
	@CreatedDate
    private Instant createdAt;
	
	
	@LastModifiedDate
    private Instant modifiedAt;
}
