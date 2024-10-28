package com.dogognon.sohliou.kone.security.oauth2ressourceserver.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdatePasswordRequest {
	
	@NotBlank
    @Size(min = 2, max = 100)
    private String newPassword;
	

}
