package com.dogognon.sohliou.kone.NouvelleProcedure.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link SecuredMetierController}.
 */
@Generated
public class SecuredMetierController__BeanDefinitions {
  /**
   * Get the bean definition for 'securedMetierController'.
   */
  public static BeanDefinition getSecuredMetierControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(SecuredMetierController.class);
    InstanceSupplier<SecuredMetierController> instanceSupplier = InstanceSupplier.using(SecuredMetierController::new);
    instanceSupplier = instanceSupplier.andThen(SecuredMetierController__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
