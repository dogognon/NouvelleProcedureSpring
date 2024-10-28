package com.dogognon.sohliou.kone.security.oauth2login;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link OAuth2AuthenticationFailureHandler}.
 */
@Generated
public class OAuth2AuthenticationFailureHandler__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static OAuth2AuthenticationFailureHandler apply(RegisteredBean registeredBean,
      OAuth2AuthenticationFailureHandler instance) {
    instance.httpCookieOAuth2AuthorizationRequestRepository = AutowiredFieldValueResolver.forRequiredField("httpCookieOAuth2AuthorizationRequestRepository").resolve(registeredBean);
    return instance;
  }
}
