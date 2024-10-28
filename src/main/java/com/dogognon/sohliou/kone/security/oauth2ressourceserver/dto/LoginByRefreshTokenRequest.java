package com.dogognon.sohliou.kone.security.oauth2ressourceserver.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginByRefreshTokenRequest {
		@NotBlank
		private String refreshToken;
	}

