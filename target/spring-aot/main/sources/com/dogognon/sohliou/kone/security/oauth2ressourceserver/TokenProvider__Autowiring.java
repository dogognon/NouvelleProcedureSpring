package com.dogognon.sohliou.kone.security.oauth2ressourceserver;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link TokenProvider}.
 */
@Generated
public class TokenProvider__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static TokenProvider apply(RegisteredBean registeredBean, TokenProvider instance) {
    instance.authenticationManager = AutowiredFieldValueResolver.forRequiredField("authenticationManager").resolve(registeredBean);
    AutowiredFieldValueResolver.forRequiredField("customUserDetailsService").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("rsakeysConfig").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
