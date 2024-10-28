package com.dogognon.sohliou.kone.security;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

import com.dogognon.sohliou.kone.security.data.Role;
import com.dogognon.sohliou.kone.NouvelleProcedure.data.Poste;
import com.dogognon.sohliou.kone.NouvelleProcedure.data.PosteRepository;
//import com.dogognon.sohliou.kone.security.data.AuthentifProvider;
import com.dogognon.sohliou.kone.security.data.RoleName;
import com.dogognon.sohliou.kone.security.data.RoleRepository;
import com.dogognon.sohliou.kone.security.data.User;
import com.dogognon.sohliou.kone.security.data.UserRepository;
import com.dogognon.sohliou.kone.security.exception.MyBadCredentialsException;
import com.dogognon.sohliou.kone.security.exception.MyBadJWTException;
import com.dogognon.sohliou.kone.security.exception.ResourceNotFoundException;
import com.dogognon.sohliou.kone.security.exception.UsernameNotFoundException;
import com.dogognon.sohliou.kone.security.oauth2ressourceserver.TokenProvider;
import com.dogognon.sohliou.kone.security.oauth2ressourceserver.dto.LoginByPhoneOrEmailandPasswordRequest;
import com.dogognon.sohliou.kone.security.oauth2ressourceserver.dto.LoginByRefreshTokenRequest;
import com.dogognon.sohliou.kone.security.oauth2ressourceserver.dto.RegisterRequest;
import com.dogognon.sohliou.kone.security.oauth2ressourceserver.dto.TextResponse;
import com.dogognon.sohliou.kone.security.oauth2ressourceserver.dto.UpdatePasswordRequest;




/***
 * 
 * @author m17405
 * 
 * @apiNote revoir le timeout du refresh token et du access token qui sont de 1 minute et 5 minutes et faire un default role for new user.
 *
 */
@Service
public class SecurityServiceImp implements SecurityService {
	

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PosteRepository posteRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	TokenProvider tokenProvider;
	
	@Autowired
	private JwtDecoder jwtDecoder;
	
	
	
	@Override
	public ResponseEntity<String> registerUser(RegisterRequest registerRequest) {
		//Verification si l'utilisateur existe
				if(userRepository.existsByPhone(registerRequest.getTelephone())) {
					return  new ResponseEntity<String>("Téléphone déjà utilisée !",HttpStatus.CONFLICT);
				}

				if(userRepository.existsByEmail(registerRequest.getEmail())) {
					return new ResponseEntity<String>("Adresse e-mail déjà utilisée !",HttpStatus.CONFLICT);
				}

				// Création du compte de l'utilisateur
				User user = new User();
				if(registerRequest.getTelephone() ==null) {
					//si le telehpone est null on genere une chaine de caractere avec le prefixe -
					String text = "-"+generateString(9);
					//tant qu'il existe on réessaye sinon on injecte 
					while(userRepository.existsByPhone(text)) {
						text = "-"+generateString(9);
					}
					user.setPhone(text);
				} else {
					user.setPhone(registerRequest.getTelephone());
				}
				if(registerRequest.getNomprenoms()!=null) {
					user.setNomprenoms(registerRequest.getNomprenoms());
				}
				if(registerRequest.getEmail().isEmpty()) {
					//generer une email avec prefixe -
					String text = "-"+generateString(9)+"@del.null.app" ;
					//tant qu'il existe on réessaye sinon on injecte
					//tant qu'il existe on réessaye sinon on injecte 
					while(userRepository.existsByEmail(text)) {
						text = "-"+generateString(9)+"@del.null.app";
					}
					user.setEmail(text);
				} else {
					user.setEmail(registerRequest.getEmail());
				}
				//user.setProvider(AuthentifProvider.app);
				user.setPasswords(passwordEncoder.encode(registerRequest.getPassword()));
				Poste poste = posteRepository.findByLibelle(registerRequest.getPoste()).orElseThrow(() -> new ResourceNotFoundException("Role", "id", registerRequest.getPoste()));
				user.setPostes(poste);
		
				//refresh all role 
				 for (RoleName role : RoleName.values()) {
					 Optional<Role> r = roleRepository.findByRoleName(role);
					 if(r.isEmpty()) {
						 roleRepository.save(Role.of(role));
					 }
				}
				// recuperation ou creation du role par default
				Role userRole = null ;
				Optional<Role> r = roleRepository.findByRoleName(RoleName.Utilisateur);
				if(r.isEmpty()) {
					userRole = roleRepository.save(Role.of(RoleName.Utilisateur));
				}else {
					userRole = r.get();
				}
	
				user.setRoles(Collections.singleton(userRole));
				User savedUser = userRepository.save(user);
				
				return ResponseEntity.ok("L'utilisateur "+ savedUser.getNomprenoms()+" au numéro : "+savedUser.getPhone()+" ,adresse mail : "+savedUser.getEmail()+" a été créé avec succès.");

	}

	 public static String generateString(int size) {
	        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	        StringBuilder uniqueString = new StringBuilder();
	        Random rand = new Random();
	        
	        while (uniqueString.length() < size) {
	            int index = rand.nextInt(characters.length());
	            char randomChar = characters.charAt(index);
	            // Vérifier si le caractère est déjà dans la chaîne générée
	            //if (uniqueString.indexOf(String.valueOf(randomChar)) == -1) {
	                uniqueString.append(randomChar);
	            //}
	        }
	        
	        return uniqueString.toString();
	    }

	@Override
	public ResponseEntity<Map<String, String>> loginUserByUsernameAndPassword(LoginByPhoneOrEmailandPasswordRequest loginRequest) {
		try {
   		return tokenProvider.loginAndCreateJwtToken(loginRequest.getLogin(),loginRequest.getPassword(),true);
   	} catch (BadCredentialsException e) {
		 	SecurityContextHolder.clearContext();
		 	throw new MyBadCredentialsException("Incorrect login ", e);
       }
	}
	
	@Override
	public ResponseEntity<Map<String, String>> loginUserByRefreshToken(LoginByRefreshTokenRequest loginRequest) {
        	  if(loginRequest.getRefreshToken()==null) {
                  return new ResponseEntity<>(Map.of("errorMessage","Refresh  Token is required"), HttpStatus.UNAUTHORIZED);
              }
              try {
            	  Jwt decodeJWT = jwtDecoder.decode(loginRequest.getRefreshToken());
    			  return tokenProvider.refreshJwtToken(decodeJWT);
              } catch (JwtException e) {
            	  SecurityContextHolder.clearContext();
            	  throw new MyBadJWTException("Invalid JWT",e);
                 
              }
			
         }
	

	@Override
	public ResponseEntity<TextResponse> updatePassword(String login,UpdatePasswordRequest updatePasswordRequest) {
		User user = userRepository.findByPhoneOrEmail(login, login).orElseThrow(() -> new UsernameNotFoundException(login, login));
		user.setPasswords(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));
	//	final User usersaved = userRepository.save(user);
	//	if(usersaved != null) {
			TextResponse response = new TextResponse();
			response.setResponse("Votre mot de passe a été modifié avec succès.");
			return ResponseEntity.status(HttpStatus.OK).body(response);
	//}else {
	//		TextResponse response = new TextResponse();
	//		response.setResponse("Une erreur a été detécté.Veuillez réessayer svp ...");
	//		return ResponseEntity.status(HttpStatus.RESET_CONTENT).body(response);
	//	}
	}
		
	
	@Override
	public ResponseEntity<String> updateUserRolename(String userid ,String rolename) {
		User user = userRepository.findById(userid).orElseThrow(() -> new ResourceNotFoundException("User", "id", userid));
		Set<Role> users_role = new HashSet<Role>();
		if (rolename.equals("Administrateur")) {
			//si 'utilisateur est ADMIN c'est qu'il a tous les droits
			Role roleadmin = roleRepository.findByRoleName(RoleName.Administrateur).orElseThrow(() -> new ResourceNotFoundException("Role", "id", rolename));
			users_role.add(roleadmin);
			
			Role roleuser = roleRepository.findByRoleName(RoleName.Utilisateur).orElseThrow(() -> new ResourceNotFoundException("Role", "id", rolename));
			users_role.add(roleuser);
		}
		
		if (rolename.equals("Utilisateur")) {
			//si 'utilisateur est ADMIN c'est qu'il a tous les droits
			Role roleuser = roleRepository.findByRoleName(RoleName.Utilisateur).orElseThrow(() -> new ResourceNotFoundException("Role", "id", rolename));
			users_role.add(roleuser);
		}
		
		user.setRoles(users_role);
		User userUpdated = userRepository.save(user);
		return ResponseEntity.ok("Vous avez mis à jour les droits au compte de : "+userUpdated.getNomprenoms());
	}
	

	@Override
	public ResponseEntity<Map<String,String>> getUserEmailAndPasswordByLogin(String login){
			User user = userRepository.findByPhoneOrEmail(login,login).orElseThrow(() -> new ResourceNotFoundException("User", "login", login));
			Map<String,String> emailAndPhone = Map.of("email",maskEmailAddress(user.getEmail()),"phone", maskPhoneNumber(user.getPhone()));
			return new ResponseEntity<>(emailAndPhone, HttpStatus.OK);
			
	}
	
	private String maskPhoneNumber(String inputPhoneNum){
	    return inputPhoneNum.replaceAll("[()\\s]+", "-")
	            .replaceAll("\\d(?=(?:\\D*\\d){2})", "*");
	}
	
	private String maskEmailAddress(final String email) {
	    final String mask = "*****";
	    final int at = email.indexOf("@");
	    if (at > 2) {
	        final int maskLen = Math.min(Math.max(at / 2, 2), 4);
	        final int start = (at - maskLen) / 2;
	        return email.substring(0, start) + mask.substring(0, maskLen) + email.substring(start + maskLen);
	    }
	    return email;
	}
	
	/**
	 * @apiNote True is email,false is SMS
	 */
	/*@Override
	public ResponseEntity<TextResponse> getOtpByLogin(String login,Boolean emailOrPhone){
			User user = userRepository.findByPhoneOrEmail(login,login).orElseThrow(() -> new ResourceNotFoundException("User", "login", login));
			//random 4 number
			int length = 4;
			String numbers = "0123456789";
			
			char[] otp = new char[length];

			for (int i = 0; i < length; i++)
			{
				otp[i] = numbers.charAt(new Random().nextInt(numbers.length()));
			}
			//user.setOtp(String. valueOf(otp));
			final User usersaved = userRepository.save(user);
			
			
			if(usersaved != null) {
				if(emailOrPhone) {
					//email 
					TextResponse response = new TextResponse();
					response.setResponse("Un code OTP vous a été envoyé à votre email.");
					return new ResponseEntity<>(response, HttpStatus.CREATED);
				}else {
					//envoi de sms
					//String message = "Salut voici votre code OTP est "+String. valueOf(otp)+".Pour changer de mot de passe.";
					//smsService.envoyerMessage(user.getPhone(), message);
					TextResponse response = new TextResponse();
					response.setResponse("Un code OTP vous a été envoyé par SMS.");
					return new ResponseEntity<>(response, HttpStatus.CREATED);
				}
			}else {
				TextResponse response = new TextResponse();
				response.setResponse("Une erreur a été detécté.Veuillez réessayer svp ...");
				return ResponseEntity.status(HttpStatus.RESET_CONTENT).body(response);
			}
	}


	@Override
	public ResponseEntity<TextResponse> verifyOTPCode(String login, String codeotp) {
		User user = userRepository.findByPhoneOrEmail(login,login).orElseThrow(() -> new ResourceNotFoundException("User", "login", login));
		if(user.getOtp().equals(codeotp)) {
			TextResponse response = new TextResponse();
			response.setResponse("Votre code OTP est correct.Vous pouvez mettre à jour votre mot de passe.");
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		}else {
			TextResponse response = new TextResponse();
			response.setResponse("Votre code OTP est incorrect.");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@Override
	public ResponseEntity<TextResponse> changePasswordWithOTPCode(String login, String codeotp,String newPassword) {
		User user = userRepository.findByPhoneOrEmail(login,login).orElseThrow(() -> new ResourceNotFoundException("User", "login", login));
		if(user.getOtp() ==codeotp) {
			user.setOtp("");
			user.setPassword(passwordEncoder.encode(newPassword));
			final User usersaved = userRepository.save(user);
			if(usersaved != null) {
				TextResponse response = new TextResponse();
				response.setResponse("Votre mot de passe a été modifié avec succès.");
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}else {
				TextResponse response = new TextResponse();
				response.setResponse("Une erreur a été detécté.Veuillez réessayer svp ...");
				return ResponseEntity.status(HttpStatus.RESET_CONTENT).body(response);
			}
		}else {
			TextResponse response = new TextResponse();
			response.setResponse("Votre code OTP est incorrect.");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		
	}*/
		


}
