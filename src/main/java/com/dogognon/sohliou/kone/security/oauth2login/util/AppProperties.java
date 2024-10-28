package com.dogognon.sohliou.kone.security.oauth2login.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {
	
	 @Getter
    private final OAuth2 oauth2 = new OAuth2();

    public static final class OAuth2 {
    	 @Getter
        private List<String> authorizedRedirectUris = new ArrayList<>();


        public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
            this.authorizedRedirectUris = authorizedRedirectUris;
            return this;
        }
    }


}
