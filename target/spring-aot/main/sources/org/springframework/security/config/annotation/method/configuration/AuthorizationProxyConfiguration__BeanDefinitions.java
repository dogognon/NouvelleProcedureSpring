package org.springframework.security.config.annotation.method.configuration;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.security.authorization.method.AuthorizationAdvisorProxyFactory;

/**
 * Bean definitions for {@link AuthorizationProxyConfiguration}.
 */
@Generated
public class AuthorizationProxyConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'authorizationProxyConfiguration'.
   */
  public static BeanDefinition getAuthorizationProxyConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AuthorizationProxyConfiguration.class);
    beanDefinition.setInstanceSupplier(AuthorizationProxyConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'authorizationProxyFactory'.
   */
  private static BeanInstanceSupplier<AuthorizationAdvisorProxyFactory> getAuthorizationProxyFactoryInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<AuthorizationAdvisorProxyFactory>forFactoryMethod(AuthorizationProxyConfiguration.class, "authorizationProxyFactory", ObjectProvider.class, ObjectProvider.class)
            .withGenerator((registeredBean, args) -> AuthorizationProxyConfiguration.authorizationProxyFactory(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'authorizationProxyFactory'.
   */
  public static BeanDefinition getAuthorizationProxyFactoryBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AuthorizationProxyConfiguration.class);
    beanDefinition.setTargetType(AuthorizationAdvisorProxyFactory.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.setInstanceSupplier(getAuthorizationProxyFactoryInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'authorizeReturnObjectMethodInterceptor'.
   */
  private static BeanInstanceSupplier<MethodInterceptor> getAuthorizeReturnObjectMethodInterceptorInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<MethodInterceptor>forFactoryMethod(AuthorizationProxyConfiguration.class, "authorizeReturnObjectMethodInterceptor", ObjectProvider.class, AuthorizationAdvisorProxyFactory.class)
            .withGenerator((registeredBean, args) -> AuthorizationProxyConfiguration.authorizeReturnObjectMethodInterceptor(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'authorizeReturnObjectMethodInterceptor'.
   */
  public static BeanDefinition getAuthorizeReturnObjectMethodInterceptorBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AuthorizationProxyConfiguration.class);
    beanDefinition.setTargetType(MethodInterceptor.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.setInstanceSupplier(getAuthorizeReturnObjectMethodInterceptorInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'authorizeReturnObjectAdvisor'.
   */
  private static BeanInstanceSupplier<MethodInterceptor> getAuthorizeReturnObjectAdvisorInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<MethodInterceptor>forFactoryMethod(AuthorizationProxyConfiguration.class, "authorizeReturnObjectMethodInterceptor", ObjectProvider.class, AuthorizationAdvisorProxyFactory.class)
            .withGenerator((registeredBean, args) -> AuthorizationProxyConfiguration.authorizeReturnObjectMethodInterceptor(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'authorizeReturnObjectAdvisor'.
   */
  public static BeanDefinition getAuthorizeReturnObjectAdvisorBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AuthorizationProxyConfiguration.class);
    beanDefinition.setTargetType(Advisor.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.setInstanceSupplier(getAuthorizeReturnObjectAdvisorInstanceSupplier());
    return beanDefinition;
  }
}
