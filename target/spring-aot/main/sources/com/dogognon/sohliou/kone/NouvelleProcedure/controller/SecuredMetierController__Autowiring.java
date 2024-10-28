package com.dogognon.sohliou.kone.NouvelleProcedure.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link SecuredMetierController}.
 */
@Generated
public class SecuredMetierController__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static SecuredMetierController apply(RegisteredBean registeredBean,
      SecuredMetierController instance) {
    AutowiredFieldValueResolver.forRequiredField("crud").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("service").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
