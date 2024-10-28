package org.springframework.beans.factory.aspectj;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link AnnotationBeanConfigurerAspect}.
 */
@Generated
public class AnnotationBeanConfigurerAspect__BeanDefinitions {
  /**
   * Get the bean definition for 'internalBeanConfigurerAspect'.
   */
  public static BeanDefinition getInternalBeanConfigurerAspectBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AnnotationBeanConfigurerAspect.class);
    beanDefinition.setTargetType(AnnotationBeanConfigurerAspect.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.setInstanceSupplier(BeanInstanceSupplier.<AnnotationBeanConfigurerAspect>forFactoryMethod(AnnotationBeanConfigurerAspect.class, "aspectOf").withGenerator((registeredBean) -> AnnotationBeanConfigurerAspect.aspectOf()));
    return beanDefinition;
  }
}
