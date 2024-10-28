package com.dogognon.sohliou.kone.security.config;

import com.dogognon.sohliou.kone.security.oauth2login.HttpCookieOAuth2AuthorizationRequestRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.web.cors.CorsConfigurationSource;

/**
 * Bean definitions for {@link SecurityConfig}.
 */
@Generated
public class SecurityConfig__BeanDefinitions {
  /**
   * Get the bean definition for 'securityConfig'.
   */
  public static BeanDefinition getSecurityConfigBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(SecurityConfig.class);
    beanDefinition.setTargetType(SecurityConfig.class);
    ConfigurationClassUtils.initializeConfigurationClass(SecurityConfig.class);
    InstanceSupplier<SecurityConfig> instanceSupplier = InstanceSupplier.using(SecurityConfig$$SpringCGLIB$$0::new);
    instanceSupplier = instanceSupplier.andThen(SecurityConfig__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'configureFirewall'.
   */
  private static BeanInstanceSupplier<HttpFirewall> getConfigureFirewallInstanceSupplier() {
    return BeanInstanceSupplier.<HttpFirewall>forFactoryMethod(SecurityConfig.class, "configureFirewall")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(SecurityConfig.class).configureFirewall());
  }

  /**
   * Get the bean definition for 'configureFirewall'.
   */
  public static BeanDefinition getConfigureFirewallBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(HttpFirewall.class);
    beanDefinition.setInstanceSupplier(getConfigureFirewallInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'passwordEncoder'.
   */
  private static BeanInstanceSupplier<PasswordEncoder> getPasswordEncoderInstanceSupplier() {
    return BeanInstanceSupplier.<PasswordEncoder>forFactoryMethod(SecurityConfig.class, "passwordEncoder")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(SecurityConfig.class).passwordEncoder());
  }

  /**
   * Get the bean definition for 'passwordEncoder'.
   */
  public static BeanDefinition getPasswordEncoderBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(PasswordEncoder.class);
    beanDefinition.setInstanceSupplier(getPasswordEncoderInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'authenticationManager'.
   */
  private static BeanInstanceSupplier<AuthenticationManager> getAuthenticationManagerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<AuthenticationManager>forFactoryMethod(SecurityConfig.class, "authenticationManager", UserDetailsService.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(SecurityConfig.class).authenticationManager(args.get(0, UserDetailsService.class)));
  }

  /**
   * Get the bean definition for 'authenticationManager'.
   */
  public static BeanDefinition getAuthenticationManagerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AuthenticationManager.class);
    beanDefinition.setInstanceSupplier(getAuthenticationManagerInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'cookieAuthorizationRequestRepository'.
   */
  private static BeanInstanceSupplier<HttpCookieOAuth2AuthorizationRequestRepository> getCookieAuthorizationRequestRepositoryInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<HttpCookieOAuth2AuthorizationRequestRepository>forFactoryMethod(SecurityConfig.class, "cookieAuthorizationRequestRepository")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(SecurityConfig.class).cookieAuthorizationRequestRepository());
  }

  /**
   * Get the bean definition for 'cookieAuthorizationRequestRepository'.
   */
  public static BeanDefinition getCookieAuthorizationRequestRepositoryBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(HttpCookieOAuth2AuthorizationRequestRepository.class);
    beanDefinition.setInstanceSupplier(getCookieAuthorizationRequestRepositoryInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'corsConfigurationSource'.
   */
  private static BeanInstanceSupplier<CorsConfigurationSource> getCorsConfigurationSourceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<CorsConfigurationSource>forFactoryMethod(SecurityConfig.class, "corsConfigurationSource")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(SecurityConfig.class).corsConfigurationSource());
  }

  /**
   * Get the bean definition for 'corsConfigurationSource'.
   */
  public static BeanDefinition getCorsConfigurationSourceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(CorsConfigurationSource.class);
    beanDefinition.setInstanceSupplier(getCorsConfigurationSourceInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'filterChain'.
   */
  private static BeanInstanceSupplier<SecurityFilterChain> getFilterChainInstanceSupplier() {
    return BeanInstanceSupplier.<SecurityFilterChain>forFactoryMethod(SecurityConfig.class, "filterChain", HttpSecurity.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(SecurityConfig.class).filterChain(args.get(0)));
  }

  /**
   * Get the bean definition for 'filterChain'.
   */
  public static BeanDefinition getFilterChainBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(SecurityFilterChain.class);
    beanDefinition.setInstanceSupplier(getFilterChainInstanceSupplier());
    return beanDefinition;
  }
}
