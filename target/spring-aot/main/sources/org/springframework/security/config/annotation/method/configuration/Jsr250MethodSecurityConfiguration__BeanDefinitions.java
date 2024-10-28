package org.springframework.security.config.annotation.method.configuration;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link Jsr250MethodSecurityConfiguration}.
 */
@Generated
public class Jsr250MethodSecurityConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'jsr250MethodSecurityConfiguration'.
   */
  public static BeanDefinition getJsrMethodSecurityConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(Jsr250MethodSecurityConfiguration.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.setInstanceSupplier(Jsr250MethodSecurityConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'jsr250AuthorizationMethodInterceptor'.
   */
  private static BeanInstanceSupplier<MethodInterceptor> getJsrAuthorizationMethodInterceptorInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<MethodInterceptor>forFactoryMethod(Jsr250MethodSecurityConfiguration.class, "jsr250AuthorizationMethodInterceptor", ObjectProvider.class, ObjectProvider.class, ObjectProvider.class, ObjectProvider.class, ObjectProvider.class, Jsr250MethodSecurityConfiguration.class)
            .withGenerator((registeredBean, args) -> Jsr250MethodSecurityConfiguration.jsr250AuthorizationMethodInterceptor(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5)));
  }

  /**
   * Get the bean definition for 'jsr250AuthorizationMethodInterceptor'.
   */
  public static BeanDefinition getJsrAuthorizationMethodInterceptorBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(Jsr250MethodSecurityConfiguration.class);
    beanDefinition.setTargetType(MethodInterceptor.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.setInstanceSupplier(getJsrAuthorizationMethodInterceptorInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'jsr250AuthorizationAdvisor'.
   */
  private static BeanInstanceSupplier<MethodInterceptor> getJsrAuthorizationAdvisorInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<MethodInterceptor>forFactoryMethod(Jsr250MethodSecurityConfiguration.class, "jsr250AuthorizationMethodInterceptor", ObjectProvider.class, ObjectProvider.class, ObjectProvider.class, ObjectProvider.class, ObjectProvider.class, Jsr250MethodSecurityConfiguration.class)
            .withGenerator((registeredBean, args) -> Jsr250MethodSecurityConfiguration.jsr250AuthorizationMethodInterceptor(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5)));
  }

  /**
   * Get the bean definition for 'jsr250AuthorizationAdvisor'.
   */
  public static BeanDefinition getJsrAuthorizationAdvisorBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(Jsr250MethodSecurityConfiguration.class);
    beanDefinition.setTargetType(Advisor.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.setInstanceSupplier(getJsrAuthorizationAdvisorInstanceSupplier());
    return beanDefinition;
  }
}
