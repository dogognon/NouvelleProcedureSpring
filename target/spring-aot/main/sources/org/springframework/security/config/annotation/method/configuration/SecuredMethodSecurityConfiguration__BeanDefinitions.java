package org.springframework.security.config.annotation.method.configuration;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link SecuredMethodSecurityConfiguration}.
 */
@Generated
public class SecuredMethodSecurityConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'securedMethodSecurityConfiguration'.
   */
  public static BeanDefinition getSecuredMethodSecurityConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(SecuredMethodSecurityConfiguration.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.setInstanceSupplier(SecuredMethodSecurityConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'securedAuthorizationMethodInterceptor'.
   */
  private static BeanInstanceSupplier<MethodInterceptor> getSecuredAuthorizationMethodInterceptorInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<MethodInterceptor>forFactoryMethod(SecuredMethodSecurityConfiguration.class, "securedAuthorizationMethodInterceptor", ObjectProvider.class, ObjectProvider.class, ObjectProvider.class, ObjectProvider.class, SecuredMethodSecurityConfiguration.class)
            .withGenerator((registeredBean, args) -> SecuredMethodSecurityConfiguration.securedAuthorizationMethodInterceptor(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4)));
  }

  /**
   * Get the bean definition for 'securedAuthorizationMethodInterceptor'.
   */
  public static BeanDefinition getSecuredAuthorizationMethodInterceptorBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(SecuredMethodSecurityConfiguration.class);
    beanDefinition.setTargetType(MethodInterceptor.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.setInstanceSupplier(getSecuredAuthorizationMethodInterceptorInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'securedAuthorizationAdvisor'.
   */
  private static BeanInstanceSupplier<MethodInterceptor> getSecuredAuthorizationAdvisorInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<MethodInterceptor>forFactoryMethod(SecuredMethodSecurityConfiguration.class, "securedAuthorizationMethodInterceptor", ObjectProvider.class, ObjectProvider.class, ObjectProvider.class, ObjectProvider.class, SecuredMethodSecurityConfiguration.class)
            .withGenerator((registeredBean, args) -> SecuredMethodSecurityConfiguration.securedAuthorizationMethodInterceptor(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4)));
  }

  /**
   * Get the bean definition for 'securedAuthorizationAdvisor'.
   */
  public static BeanDefinition getSecuredAuthorizationAdvisorBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(SecuredMethodSecurityConfiguration.class);
    beanDefinition.setTargetType(Advisor.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.setInstanceSupplier(getSecuredAuthorizationAdvisorInstanceSupplier());
    return beanDefinition;
  }
}
