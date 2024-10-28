package org.springframework.security.config.annotation.web.configuration;

import java.util.List;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredMethodArgumentsResolver;
import org.springframework.beans.factory.support.RegisteredBean;
import org.springframework.security.core.context.SecurityContextHolderStrategy;

/**
 * Autowiring for {@link OAuth2ClientConfiguration.OAuth2ClientWebMvcSecurityConfiguration}.
 */
@Generated
public class OAuth2ClientConfiguration_OAuth2ClientWebMvcSecurityConfiguration__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static OAuth2ClientConfiguration.OAuth2ClientWebMvcSecurityConfiguration apply(
      RegisteredBean registeredBean,
      OAuth2ClientConfiguration.OAuth2ClientWebMvcSecurityConfiguration instance) {
    AutowiredMethodArgumentsResolver.forMethod("setAuthorizedClientManager", List.class).resolve(registeredBean, args -> instance.setAuthorizedClientManager(args.get(0)));
    AutowiredMethodArgumentsResolver.forMethod("setSecurityContextHolderStrategy", SecurityContextHolderStrategy.class).resolve(registeredBean, args -> instance.setSecurityContextHolderStrategy(args.get(0)));
    AutowiredMethodArgumentsResolver.forRequiredMethod("setAuthorizedClientManagerRegistrar", OAuth2ClientConfiguration.OAuth2AuthorizedClientManagerRegistrar.class).resolve(registeredBean, args -> instance.setAuthorizedClientManagerRegistrar(args.get(0)));
    return instance;
  }
}
