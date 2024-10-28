package com.dogognon.sohliou.kone.security.oauth2ressourceserver;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link CustomUserDetailsService}.
 */
@Generated
public class CustomUserDetailsService__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static CustomUserDetailsService apply(RegisteredBean registeredBean,
      CustomUserDetailsService instance) {
    instance.userRepository = AutowiredFieldValueResolver.forRequiredField("userRepository").resolve(registeredBean);
    return instance;
  }
}
