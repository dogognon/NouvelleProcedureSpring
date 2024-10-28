package com.dogognon.sohliou.kone.security.config;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.*;
import static org.springframework.security.config.Customizer.*;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.dogognon.sohliou.kone.security.oauth2login.CustomOAuth2UserService;
import com.dogognon.sohliou.kone.security.oauth2login.HttpCookieOAuth2AuthorizationRequestRepository;
import com.dogognon.sohliou.kone.security.oauth2login.OAuth2AuthenticationFailureHandler;
import com.dogognon.sohliou.kone.security.oauth2login.OAuth2AuthenticationSuccessHandler;
import com.dogognon.sohliou.kone.security.oauth2ressourceserver.CustomUserDetailsService;  


@Configuration
@CrossOrigin
@EnableWebSecurity
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true
        //prePostEnabled = true - default is already true
)

public class SecurityConfig {
	
	@Autowired
    @Qualifier("customAuthenticationEntryPoint")
    AuthenticationEntryPoint authEntryPoint;
	
	@Autowired
    private CustomUserDetailsService customUserDetailsService;
	
	 @Autowired
	private CustomOAuth2UserService customOAuth2UserService;

	
	@Autowired
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Autowired
    private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
	
    private final long MAX_AGE_SECS = 3600;

    @Value("${app.cors.allowed-origins}")
    private String[] allowedOrigins;

    @Bean
    HttpFirewall configureFirewall() {
        StrictHttpFirewall strictHttpFirewall = new StrictHttpFirewall();
        strictHttpFirewall.setAllowedHttpMethods(Arrays.asList("GET", "POST", "DELETE", "PUT","OPTIONS")); // Allow only HTTP GET, POST, DELETE and OPTIONS methods
        return strictHttpFirewall;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
    
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    
    @Bean
    AuthenticationManager authenticationManager(UserDetailsService userDetailsService){
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(customUserDetailsService);
        return new ProviderManager(authProvider);
    }

    @Bean
    HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }
    
    @Bean
	CorsConfigurationSource corsConfigurationSource() {
    	//construct a stringbuilder object from given string array
	        StringBuilder stringBuilder = new StringBuilder();
	        for (int i = 0; i <allowedOrigins.length; i++) {
	             stringBuilder.append(allowedOrigins[i]+ ",");
	        }
	       
	        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
	        System.out.println("CORS"+stringBuilder.toString());
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList(stringBuilder.toString()));
		configuration.setAllowedMethods(Arrays.asList("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("Content-Type", "X-Requested-With", "accept", "Origin","authorization", "Access-Control-Request-Method",
                "Access-Control-Request-Headers"));
		configuration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials","Access-Control-Allow-Headers"));
		configuration.setAllowCredentials(false);
		configuration.setMaxAge(MAX_AGE_SECS);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
    
    

    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
        		.cors(withDefaults())
        		//.cors(cors->cors.disable())
                .csrf(csrf -> csrf.ignoringRequestMatchers(toH2Console()))
                .csrf(csrf -> csrf.disable())
                .httpBasic(http -> http.disable())
                .headers(header -> header
                		//.frameOptions(withDefaults())
                		// for H2-console
                		.frameOptions(frameOptions -> frameOptions
                	            .sameOrigin() //or disable
                	        )
                		)
                .formLogin(formlogin -> formlogin.disable())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                /**
                 * toutes les routes sont activées sauf api qui est le coeur du reste api
                 * 
                 * anyrequest est a eviter
                 */
            	//.requestMatchers("/auth/**", "/oauth2/**", "/login/**", "/error/**").permitAll()//.requestMatchers(toH2Console()).permitAll()
                .authorizeHttpRequests(auth -> auth.requestMatchers("/**").permitAll()
                									.requestMatchers("/api/**").authenticated())
                									
                
                
              //.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                
                //.requestMatchers("/**").permitAll())
                									//.requestMatchers().permitAll().
                									
                							
                									//.anyRequest().authenticated())
                //.authorizeHttpRequests(auth -> auth.requestMatchers("/api/**","/auth/**", "/oauth2/**", "/login/**", "/error/**").permitAll().anyRequest().authenticated())

                .oauth2ResourceServer((oauth2) -> oauth2.jwt(withDefaults()))
                .exceptionHandling(handling -> handling.authenticationEntryPoint(authEntryPoint))
                .oauth2Login(login -> login
                        .authorizationEndpoint(
                        		authorizationEndpoint ->
                                authorizationEndpoint
                                    .baseUri("/oauth2/authorize")
                                    .authorizationRequestRepository(cookieAuthorizationRequestRepository())
                          )
                        .redirectionEndpoint(
                        		redirectionEndpoint ->
                        		redirectionEndpoint
                                    .baseUri("/login/oauth2/code/**")
                        		)
                        .userInfoEndpoint(
                        		userInfoEndpoint ->
                        		userInfoEndpoint
                                .userService(customOAuth2UserService)
                        )
                        .successHandler(oAuth2AuthenticationSuccessHandler)
                        .failureHandler(oAuth2AuthenticationFailureHandler)
                ).build();
        
        
        
        
      
   
   
    
   
   
   
       
//    .oauth2Login()
//        .authorizationEndpoint()
//            .baseUri("/oauth2/authorize")
//            .authorizationRequestRepository(cookieAuthorizationRequestRepository())
//            .and()
//        .redirectionEndpoint()
//        .baseUri("/login/oauth2/code/*")
//            .and()
//        .userInfoEndpoint()
//            .userService(customOAuth2UserService)
//            .and()
//        .successHandler(oAuth2AuthenticationSuccessHandler)
//        .failureHandler(oAuth2AuthenticationFailureHandler);

    }
    
    
    
  
}
