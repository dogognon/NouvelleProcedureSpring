package com.dogognon.sohliou.kone.security.oauth2login;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link OAuth2AuthenticationFailureHandler}.
 */
@Generated
public class OAuth2AuthenticationFailureHandler__BeanDefinitions {
  /**
   * Get the bean definition for 'oAuth2AuthenticationFailureHandler'.
   */
  public static BeanDefinition getOAuthAuthenticationFailureHandlerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(OAuth2AuthenticationFailureHandler.class);
    InstanceSupplier<OAuth2AuthenticationFailureHandler> instanceSupplier = InstanceSupplier.using(OAuth2AuthenticationFailureHandler::new);
    instanceSupplier = instanceSupplier.andThen(OAuth2AuthenticationFailureHandler__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
