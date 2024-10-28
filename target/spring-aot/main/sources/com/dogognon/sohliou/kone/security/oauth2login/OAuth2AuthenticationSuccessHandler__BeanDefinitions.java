package com.dogognon.sohliou.kone.security.oauth2login;

import com.dogognon.sohliou.kone.security.oauth2login.util.AppProperties;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link OAuth2AuthenticationSuccessHandler}.
 */
@Generated
public class OAuth2AuthenticationSuccessHandler__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'OAuth2AuthenticationSuccessHandler'.
   */
  private static BeanInstanceSupplier<OAuth2AuthenticationSuccessHandler> getOAuthAuthenticationSuccessHandlerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<OAuth2AuthenticationSuccessHandler>forConstructor(AppProperties.class, HttpCookieOAuth2AuthorizationRequestRepository.class)
            .withGenerator((registeredBean, args) -> new OAuth2AuthenticationSuccessHandler(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'oAuth2AuthenticationSuccessHandler'.
   */
  public static BeanDefinition getOAuthAuthenticationSuccessHandlerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(OAuth2AuthenticationSuccessHandler.class);
    beanDefinition.setInstanceSupplier(getOAuthAuthenticationSuccessHandlerInstanceSupplier());
    return beanDefinition;
  }
}
