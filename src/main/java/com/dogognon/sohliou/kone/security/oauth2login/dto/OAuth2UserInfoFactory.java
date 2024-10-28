package com.dogognon.sohliou.kone.security.oauth2login.dto;

import java.util.Map;

import com.dogognon.sohliou.kone.security.data.AuthentifProvider;
import com.dogognon.sohliou.kone.security.exception.OAuth2AuthenticationProcessingException;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthentifProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthentifProvider.facebook.toString())) {
            return new FacebookOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
