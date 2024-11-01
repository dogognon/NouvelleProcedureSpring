package com.dogognon.sohliou.kone.security.oauth2ressourceserver;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link CustomUserDetailsService}.
 */
@Generated
public class CustomUserDetailsService__BeanDefinitions {
  /**
   * Get the bean definition for 'customUserDetailsService'.
   */
  public static BeanDefinition getCustomUserDetailsServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(CustomUserDetailsService.class);
    InstanceSupplier<CustomUserDetailsService> instanceSupplier = InstanceSupplier.using(CustomUserDetailsService::new);
    instanceSupplier = instanceSupplier.andThen(CustomUserDetailsService__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
