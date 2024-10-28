package org.springframework.boot.autoconfigure.security.oauth2.client.servlet;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

/**
 * Bean definitions for {@link OAuth2ClientRegistrationRepositoryConfiguration}.
 */
@Generated
public class OAuth2ClientRegistrationRepositoryConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'oAuth2ClientRegistrationRepositoryConfiguration'.
   */
  public static BeanDefinition getOAuthClientRegistrationRepositoryConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(OAuth2ClientRegistrationRepositoryConfiguration.class);
    beanDefinition.setInstanceSupplier(OAuth2ClientRegistrationRepositoryConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'clientRegistrationRepository'.
   */
  private static BeanInstanceSupplier<InMemoryClientRegistrationRepository> getClientRegistrationRepositoryInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<InMemoryClientRegistrationRepository>forFactoryMethod(OAuth2ClientRegistrationRepositoryConfiguration.class, "clientRegistrationRepository", OAuth2ClientProperties.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(OAuth2ClientRegistrationRepositoryConfiguration.class).clientRegistrationRepository(args.get(0)));
  }

  /**
   * Get the bean definition for 'clientRegistrationRepository'.
   */
  public static BeanDefinition getClientRegistrationRepositoryBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(InMemoryClientRegistrationRepository.class);
    beanDefinition.setInstanceSupplier(getClientRegistrationRepositoryInstanceSupplier());
    return beanDefinition;
  }
}
