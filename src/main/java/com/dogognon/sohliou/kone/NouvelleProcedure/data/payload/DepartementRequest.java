package com.dogognon.sohliou.kone.NouvelleProcedure.data.payload;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DepartementRequest {
	

	@NotBlank
	private String libelle;
	
	
	//@NotBlank
	//private long departement_parent_id;

	
}
