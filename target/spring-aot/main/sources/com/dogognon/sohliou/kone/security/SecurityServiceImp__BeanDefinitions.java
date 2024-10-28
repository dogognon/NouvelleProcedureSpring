package com.dogognon.sohliou.kone.security;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link SecurityServiceImp}.
 */
@Generated
public class SecurityServiceImp__BeanDefinitions {
  /**
   * Get the bean definition for 'securityServiceImp'.
   */
  public static BeanDefinition getSecurityServiceImpBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(SecurityServiceImp.class);
    InstanceSupplier<SecurityServiceImp> instanceSupplier = InstanceSupplier.using(SecurityServiceImp::new);
    instanceSupplier = instanceSupplier.andThen(SecurityServiceImp__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
