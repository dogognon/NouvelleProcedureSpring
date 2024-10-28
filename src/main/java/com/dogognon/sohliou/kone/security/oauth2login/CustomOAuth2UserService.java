package com.dogognon.sohliou.kone.security.oauth2login;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

//import com.dogognon.sohliou.kone.security.data.AuthentifProvider;
import com.dogognon.sohliou.kone.security.data.Role;
import com.dogognon.sohliou.kone.security.data.RoleName;
import com.dogognon.sohliou.kone.security.data.RoleRepository;
import com.dogognon.sohliou.kone.security.data.User;
import com.dogognon.sohliou.kone.security.data.UserRepository;
import com.dogognon.sohliou.kone.security.exception.OAuth2AuthenticationProcessingException;
import com.dogognon.sohliou.kone.security.oauth2login.dto.OAuth2UserInfo;
import com.dogognon.sohliou.kone.security.oauth2login.dto.OAuth2UserInfoFactory;
import com.dogognon.sohliou.kone.security.oauth2ressourceserver.UserPrincipal;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    
   

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if(!StringUtils.hasLength(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        Optional<User> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        User user;
        if(userOptional.isPresent()) {
            user = userOptional.get();
            /*if(!user.getProvider().equals(AuthentifProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                        user.getProvider() + " account. Please use your " + user.getProvider() +
                        " account to login.");
            }*/
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        User user = new User();

        //user.setProvider(AuthentifProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        //user.setProviderId(oAuth2UserInfo.getId());
        user.setNomprenoms(oAuth2UserInfo.getName());
        user.setEmail(oAuth2UserInfo.getEmail());
        //user.setPhotoUser(oAuth2UserInfo.getImageUrl());
       
        
     // recuperation ou creation du role par default
		Role userRole = null ;
		Optional<Role> r = roleRepository.findByRoleName(RoleName.Utilisateur);
		if(r.isEmpty()) {
			userRole = roleRepository.save(Role.of(RoleName.Utilisateur));
		}else {
			userRole = r.get();
		}
        user.setRoles(Collections.singleton(userRole));
        return userRepository.save(user);
    }

    private User updateExistingUser(User user, OAuth2UserInfo oAuth2UserInfo) {
    	//la mise a jour n'est pas forcement necessaire
    	user.setNomprenoms(oAuth2UserInfo.getName());
    	//user.setPhotoUser(oAuth2UserInfo.getImageUrl());
        return userRepository.save(user);
    }

}
