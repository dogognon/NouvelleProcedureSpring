package com.dogognon.sohliou.kone.security.oauth2ressourceserver;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("customAuthenticationEntryPoint")
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	 private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) 
      throws IOException, ServletException {

//        RestError re = new RestError(HttpStatus.UNAUTHORIZED.toString(), "Authentication failed");
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        OutputStream responseStream = response.getOutputStream();
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValue(responseStream, re);
//        responseStream.flush();
    	
    	logger.error("Responding with unauthorized error. Message - {}", authException.getMessage());
    	response.sendError(HttpServletResponse.SC_UNAUTHORIZED,authException.getLocalizedMessage());
    }
}