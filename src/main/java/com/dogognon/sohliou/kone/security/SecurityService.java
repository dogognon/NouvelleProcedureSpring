package com.dogognon.sohliou.kone.security;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.dogognon.sohliou.kone.security.oauth2ressourceserver.dto.LoginByPhoneOrEmailandPasswordRequest;
import com.dogognon.sohliou.kone.security.oauth2ressourceserver.dto.LoginByRefreshTokenRequest;
import com.dogognon.sohliou.kone.security.oauth2ressourceserver.dto.RegisterRequest;
import com.dogognon.sohliou.kone.security.oauth2ressourceserver.dto.TextResponse;
import com.dogognon.sohliou.kone.security.oauth2ressourceserver.dto.UpdatePasswordRequest;



/**
 * 
 * @author m17405
 * @apiNote Cette interface gerer les services de securité
 * 
 *
 */
public interface SecurityService {
	
	ResponseEntity<String> registerUser(RegisterRequest registerRequest);
	
	ResponseEntity<Map<String, String>> loginUserByUsernameAndPassword(LoginByPhoneOrEmailandPasswordRequest loginByPhoneOrEmailandPasswordRequest);
	ResponseEntity<Map<String, String>> loginUserByRefreshToken(LoginByRefreshTokenRequest loginByRefreshTokenRequest);
	
	ResponseEntity<TextResponse> updatePassword(String login,UpdatePasswordRequest updatePasswordRequest);
	ResponseEntity<String> updateUserRolename(String userid, String rolename);
	
	ResponseEntity<Map<String,String>> getUserEmailAndPasswordByLogin(String login);
	/*ResponseEntity<TextResponse> getOtpByLogin(String login,Boolean emailOrPhone);
	ResponseEntity<TextResponse> verifyOTPCode(String login,String codeotp);
	ResponseEntity<TextResponse> changePasswordWithOTPCode(String login, String codeotp,String newPassword);*/
	

}
