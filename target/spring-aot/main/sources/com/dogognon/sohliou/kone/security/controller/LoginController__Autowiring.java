package com.dogognon.sohliou.kone.security.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link LoginController}.
 */
@Generated
public class LoginController__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static LoginController apply(RegisteredBean registeredBean, LoginController instance) {
    AutowiredFieldValueResolver.forRequiredField("service").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
