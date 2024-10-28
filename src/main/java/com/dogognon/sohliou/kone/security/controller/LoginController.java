package com.dogognon.sohliou.kone.security.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dogognon.sohliou.kone.security.SecurityService;
import com.dogognon.sohliou.kone.security.oauth2ressourceserver.dto.LoginByPhoneOrEmailandPasswordRequest;
import com.dogognon.sohliou.kone.security.oauth2ressourceserver.dto.LoginByRefreshTokenRequest;
import com.dogognon.sohliou.kone.security.oauth2ressourceserver.dto.RegisterRequest;
//import com.dogognon.sohliou.kone.security.oauth2ressourceserver.dto.RegisterRequest;
import com.dogognon.sohliou.kone.security.oauth2ressourceserver.dto.TextResponse;
import com.dogognon.sohliou.kone.security.oauth2ressourceserver.dto.UpdatePasswordRequest;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/auth/")
public class LoginController {

	@Autowired
	private SecurityService service;

	@PostMapping("/register")
	@PreAuthorize("hasAuthority('SCOPE_Administrateur')")//@Valid 
	ResponseEntity<String> registerUser(@RequestBody RegisterRequest registerRequest){
		System.err.println(registerRequest.toString());
		return service.registerUser(registerRequest);

	}
	@PostMapping("/tokenbyloginandpassword")
	ResponseEntity<Map<String, String>> loginUser(@Valid @RequestBody LoginByPhoneOrEmailandPasswordRequest loginByPhoneOrEmailandPasswordRequest){
		return service.loginUserByUsernameAndPassword(loginByPhoneOrEmailandPasswordRequest);

	}

	@PostMapping("/tokenbyrefreshtoken")
	ResponseEntity<Map<String, String>> loginUser(@Valid @RequestBody LoginByRefreshTokenRequest byRefreshTokenRequest){
		return service.loginUserByRefreshToken(byRefreshTokenRequest);

	}

	@PostMapping("/updatepassword")
	@PreAuthorize("hasAuthority('SCOPE_Utilisateur')")
	ResponseEntity<TextResponse> updatePassword(Authentication authentication,@Valid @RequestBody UpdatePasswordRequest updatePasswordRequest){
		String loginuser = authentication.getName();
		return service.updatePassword(loginuser,updatePasswordRequest);

	}


	@GetMapping("/getemailandphone/{loginuser}")
	ResponseEntity<Map<String, String>> getUserEmailAndPasswordByLogin(@PathVariable(value = "loginuser") String loginuser){
		return service.getUserEmailAndPasswordByLogin(loginuser);
	}
	
	@GetMapping("/update-privilege/{iduser}/{rolename}")
	@PreAuthorize("hasAuthority('SCOPE_Administrateur')")
	ResponseEntity<String> updateUserRolename(@PathVariable(value = "iduser") String userid,@PathVariable(value = "rolename") String namerole){
		return service.updateUserRolename(userid, namerole);
	}


	//True email - False phone
	/**@GetMapping("/sendotpbyemail/{emailuser}")
	ResponseEntity<TextResponse> sendotpByemail(@PathVariable(value = "emailuser") String emailuser){
		return service.getOtpByLogin(emailuser,true);
	}

	
	//True email - False phone
	@GetMapping("/sendotpbyphone/{phoneuser}")
	ResponseEntity<TextResponse> sendotpByphone(@PathVariable(value = "phoneuser") String phoneuser){
		return service.getOtpByLogin(phoneuser,false);
	}
	
	@GetMapping("/verifyotp/{login}/{codeotp}")
	ResponseEntity<TextResponse> verifyotp(@PathVariable(value = "login") String login,@PathVariable(value = "codeotp") String codeotp){
		return service.verifyOTPCode(login,codeotp);
	}
	
	@GetMapping("/changePasswordWithOTPCode/{login}/{codeotp}/{password}")
	ResponseEntity<TextResponse> changePasswordWithOTPCode(@PathVariable(value = "login") String login,@PathVariable(value = "codeotp") String codeotp,@PathVariable(value = "password") String password){
		return service.changePasswordWithOTPCode(login,codeotp,password);
	}
*/
	
	
}
