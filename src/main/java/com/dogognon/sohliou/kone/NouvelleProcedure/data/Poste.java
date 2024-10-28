package com.dogognon.sohliou.kone.NouvelleProcedure.data;


import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
@Table(name="poste")
public class Poste {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	
	@Column(unique=true, nullable = false,updatable=true,length = 100)
	private String libelle;
	
	
	
	 @JsonBackReference
	@ManyToOne
	@JoinColumn(name = "services")
	private Services services; // Relation avec le departement
	
	


	@Transient
	private String service_id;
	
	
	 
	@CreatedDate
    private Instant createdAt;
	
	
	@LastModifiedDate
    private Instant modifiedAt;
}
