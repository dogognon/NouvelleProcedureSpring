package com.dogognon.sohliou.kone.security.oauth2ressourceserver.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterRequest {
	
	@NotBlank
    @Size(min = 2, max = 100)
    private String nomprenoms;

	//@NotBlank
    //@Pattern(regexp="(^$|[0-9]{10})")
    private String telephone;


	
	@NotBlank
    @Size(min = 2, max = 100)
    private String poste;
	
	@NotBlank
    @Size(max = 150)
    @Email
    private String email;


	@NotBlank
    @Size(min = 2, max = 100)
    private String password = "a";

}
