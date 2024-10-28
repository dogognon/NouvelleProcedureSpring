package com.dogognon.sohliou.kone.NouvelleProcedure.data.payload;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PosteRequest {
	

	@NotBlank
	private String libelle;
	
	
	@NotBlank
	private String service;

	
}