package org.springframework.boot.autoconfigure.security.oauth2.client.servlet;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;

/**
 * Bean definitions for {@link OAuth2WebSecurityConfiguration}.
 */
@Generated
public class OAuth2WebSecurityConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'oAuth2WebSecurityConfiguration'.
   */
  public static BeanDefinition getOAuthWebSecurityConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(OAuth2WebSecurityConfiguration.class);
    beanDefinition.setInstanceSupplier(OAuth2WebSecurityConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'authorizedClientService'.
   */
  private static BeanInstanceSupplier<OAuth2AuthorizedClientService> getAuthorizedClientServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<OAuth2AuthorizedClientService>forFactoryMethod(OAuth2WebSecurityConfiguration.class, "authorizedClientService", ClientRegistrationRepository.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(OAuth2WebSecurityConfiguration.class).authorizedClientService(args.get(0)));
  }

  /**
   * Get the bean definition for 'authorizedClientService'.
   */
  public static BeanDefinition getAuthorizedClientServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(OAuth2AuthorizedClientService.class);
    beanDefinition.setInstanceSupplier(getAuthorizedClientServiceInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'authorizedClientRepository'.
   */
  private static BeanInstanceSupplier<OAuth2AuthorizedClientRepository> getAuthorizedClientRepositoryInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<OAuth2AuthorizedClientRepository>forFactoryMethod(OAuth2WebSecurityConfiguration.class, "authorizedClientRepository", OAuth2AuthorizedClientService.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(OAuth2WebSecurityConfiguration.class).authorizedClientRepository(args.get(0)));
  }

  /**
   * Get the bean definition for 'authorizedClientRepository'.
   */
  public static BeanDefinition getAuthorizedClientRepositoryBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(OAuth2AuthorizedClientRepository.class);
    beanDefinition.setInstanceSupplier(getAuthorizedClientRepositoryInstanceSupplier());
    return beanDefinition;
  }
}
