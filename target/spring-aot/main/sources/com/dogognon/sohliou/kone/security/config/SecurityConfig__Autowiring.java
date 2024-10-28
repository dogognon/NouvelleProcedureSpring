package com.dogognon.sohliou.kone.security.config;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link SecurityConfig}.
 */
@Generated
public class SecurityConfig__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static SecurityConfig apply(RegisteredBean registeredBean, SecurityConfig instance) {
    instance.authEntryPoint = AutowiredFieldValueResolver.forRequiredField("authEntryPoint").resolve(registeredBean);
    AutowiredFieldValueResolver.forRequiredField("customUserDetailsService").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("customOAuth2UserService").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("oAuth2AuthenticationSuccessHandler").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("oAuth2AuthenticationFailureHandler").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("allowedOrigins").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
