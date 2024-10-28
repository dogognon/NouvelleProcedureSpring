package com.dogognon.sohliou.kone.security.oauth2login;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link CustomOAuth2UserService}.
 */
@Generated
public class CustomOAuth2UserService__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static CustomOAuth2UserService apply(RegisteredBean registeredBean,
      CustomOAuth2UserService instance) {
    AutowiredFieldValueResolver.forRequiredField("userRepository").resolveAndSet(registeredBean, instance);
    instance.roleRepository = AutowiredFieldValueResolver.forRequiredField("roleRepository").resolve(registeredBean);
    return instance;
  }
}
