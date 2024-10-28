package com.dogognon.sohliou.kone.security.oauth2ressourceserver;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.dogognon.sohliou.kone.security.config.RsakeysConfig;


/**
 * 
 * @author m17405
 * @apiNote Cette classe permet de gerer le buid du jwt. il reste a bien configuer les claims et les timeouts ainsi que service name
 */
@Configuration
@Service
public class TokenProvider {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private RsakeysConfig rsakeysConfig;

	//define encoder
	@Bean
	JwtEncoder jwtEncoder(){
		JWK jwk= new RSAKey.Builder(rsakeysConfig.publicKey()).privateKey(rsakeysConfig.privateKey()).build();
		JWKSource<SecurityContext> jwkSource= new ImmutableJWKSet<>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwkSource);
	}

	//define decoder
	@Bean
	JwtDecoder jwtDecoder(){
		return NimbusJwtDecoder.withPublicKey(rsakeysConfig.publicKey()).build();
	}



	// Create token with user is successfully connected by password or refresh token
	public ResponseEntity<Map<String, String>> loginAndCreateJwtToken(String login,String password,Boolean withRefreshToken){
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
		// je mets l'id dans le subject car c'est un champs predefinis qui me facilite beaucoup de chose
        String subject=login;
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String scope =userPrincipal.getAuthorities().stream().map(aut -> aut.getAuthority()).collect(Collectors.joining(" "));
		SecurityContextHolder.getContext().setAuthentication(authentication);


		// Create access Token
		Map<String, String> idToken=new HashMap<>();
		Instant instant=Instant.now();
		JwtClaimsSet jwtClaimsSet=JwtClaimsSet.builder()
				.subject(subject)
				.issuedAt(instant)
				.expiresAt(instant.plus(1, ChronoUnit.DAYS))
				.issuer("security-service")
				.claim("scope",scope)
				//.claim("roles",userPrincipal.getAuthorities())
				.claim("phone",userPrincipal.getPhone())
				.claim("email",userPrincipal.getEmail())
				.claim("nomprenoms",userPrincipal.getNomprenoms())
				.build();
		String jwtAccessToken=jwtEncoder().encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
		idToken.put("accessToken",jwtAccessToken);
		// Create refresh Token
		if(withRefreshToken){
			JwtClaimsSet jwtClaimsSetRefresh=JwtClaimsSet.builder()
					.subject(subject)
					.issuedAt(instant)
					.expiresAt(instant.plus(730, ChronoUnit.DAYS))
					.issuer("security-service")
					.build();
			String jwtRefreshToken=jwtEncoder().encode(JwtEncoderParameters.from(jwtClaimsSetRefresh)).getTokenValue();
			idToken.put("refreshToken",jwtRefreshToken);
			
		}
		idToken.put("tokenType","Bearer");
		return ResponseEntity.ok(idToken);


	}  

	
	// Create token with user is successfully connected by password or refresh token
	public Map<String, String> createJwtToken(Authentication authentication){
		
			// je mets l'id dans le subject car c'est un champs predefinis qui me facilite beaucoup de chose
        String subject=authentication.getName();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String scope =userPrincipal.getAuthorities().stream().map(aut -> aut.getAuthority()).collect(Collectors.joining(" "));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Create access Token
		Map<String, String> idToken=new HashMap<>();
		Instant instant=Instant.now();
		JwtClaimsSet jwtClaimsSet=JwtClaimsSet.builder()
				.subject(subject)
				.issuedAt(instant)
				.expiresAt(instant.plus(1, ChronoUnit.DAYS))
				.issuer("security-service")
				.claim("scope",scope)
				//.claim("roles",userPrincipal.getAuthorities())
				.claim("phone",userPrincipal.getPhone())
				.claim("email",userPrincipal.getEmail())
				.claim("nomprenoms",userPrincipal.getNomprenoms())
				.build();
		String jwtAccessToken=jwtEncoder().encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
		idToken.put("accessToken",jwtAccessToken);
		// Create refresh Token
		
			JwtClaimsSet jwtClaimsSetRefresh=JwtClaimsSet.builder()
					.subject(subject)
					.issuedAt(instant)
					.expiresAt(instant.plus(730, ChronoUnit.DAYS))
					.issuer("security-service")
					.build();
			String jwtRefreshToken=jwtEncoder().encode(JwtEncoderParameters.from(jwtClaimsSetRefresh)).getTokenValue();
			idToken.put("refreshToken",jwtRefreshToken);
			idToken.put("tokenType","Bearer");

		return idToken;


	}  

	

	// Create token with user is successfully connected by password or refresh token
	public ResponseEntity<Map<String, String>> refreshJwtToken(Jwt decodeJWT){
		// je mets l'id dans le subject car c'est un champs predefinis qui me facilite beaucoup de chose
		String subject=decodeJWT.getSubject();
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(subject);
		UserPrincipal userPrincipal = (UserPrincipal) userDetails;
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		String scope=authorities.stream().map(auth->auth.getAuthority()).collect(Collectors.joining(" "));
		// Create access Token
		Map<String, String> idToken=new HashMap<>();
		Instant instant=Instant.now();
		JwtClaimsSet jwtClaimsSet=JwtClaimsSet.builder()
				.subject(subject)
				.issuedAt(instant)
				.expiresAt(instant.plus(1, ChronoUnit.DAYS))
				.issuer("security-service")
				.claim("scope",scope)
				.claim("phone",userPrincipal.getPhone())
				.claim("email",userPrincipal.getEmail())
				.claim("nomprenoms",userPrincipal.getNomprenoms())
				.build();
		String jwtAccessToken=jwtEncoder().encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
		idToken.put("accessToken",jwtAccessToken);

		// Create refresh Token
		JwtClaimsSet jwtClaimsSetRefresh=JwtClaimsSet.builder()
				.subject(subject)
				.issuedAt(instant)
				.expiresAt(instant.plus(730, ChronoUnit.DAYS))
				.issuer("security-service")
				.build();
		String jwtRefreshToken=jwtEncoder().encode(JwtEncoderParameters.from(jwtClaimsSetRefresh)).getTokenValue();
		idToken.put("refreshToken",jwtRefreshToken);
		idToken.put("tokenType","Bearer");
		return ResponseEntity.ok(idToken);
	}  

}    


