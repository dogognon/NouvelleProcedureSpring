package com.dogognon.sohliou.kone.security;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link SecurityServiceImp}.
 */
@Generated
public class SecurityServiceImp__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static SecurityServiceImp apply(RegisteredBean registeredBean,
      SecurityServiceImp instance) {
    instance.userRepository = AutowiredFieldValueResolver.forRequiredField("userRepository").resolve(registeredBean);
    instance.posteRepository = AutowiredFieldValueResolver.forRequiredField("posteRepository").resolve(registeredBean);
    instance.roleRepository = AutowiredFieldValueResolver.forRequiredField("roleRepository").resolve(registeredBean);
    instance.passwordEncoder = AutowiredFieldValueResolver.forRequiredField("passwordEncoder").resolve(registeredBean);
    instance.authenticationManager = AutowiredFieldValueResolver.forRequiredField("authenticationManager").resolve(registeredBean);
    instance.tokenProvider = AutowiredFieldValueResolver.forRequiredField("tokenProvider").resolve(registeredBean);
    AutowiredFieldValueResolver.forRequiredField("jwtDecoder").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
