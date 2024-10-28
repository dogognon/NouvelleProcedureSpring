package com.dogognon.sohliou.kone.security.oauth2login;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link CustomOAuth2UserService}.
 */
@Generated
public class CustomOAuth2UserService__BeanDefinitions {
  /**
   * Get the bean definition for 'customOAuth2UserService'.
   */
  public static BeanDefinition getCustomOAuthUserServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(CustomOAuth2UserService.class);
    InstanceSupplier<CustomOAuth2UserService> instanceSupplier = InstanceSupplier.using(CustomOAuth2UserService::new);
    instanceSupplier = instanceSupplier.andThen(CustomOAuth2UserService__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
