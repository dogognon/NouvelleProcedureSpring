package com.dogognon.sohliou.kone.security.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link LoginController}.
 */
@Generated
public class LoginController__BeanDefinitions {
  /**
   * Get the bean definition for 'loginController'.
   */
  public static BeanDefinition getLoginControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(LoginController.class);
    InstanceSupplier<LoginController> instanceSupplier = InstanceSupplier.using(LoginController::new);
    instanceSupplier = instanceSupplier.andThen(LoginController__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
