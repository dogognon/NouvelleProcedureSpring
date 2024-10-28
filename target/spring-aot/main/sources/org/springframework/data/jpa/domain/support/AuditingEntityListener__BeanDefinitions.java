package org.springframework.data.jpa.domain.support;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectFactory__BeanDefinitions;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link AuditingEntityListener}.
 */
@Generated
public class AuditingEntityListener__BeanDefinitions {
  /**
   * Get the bean definition for 'auditingEntityListener'.
   */
  public static BeanDefinition getAuditingEntityListenerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AuditingEntityListener.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.getPropertyValues().addPropertyValue("auditingHandler", ObjectFactory__BeanDefinitions.getAuditingHandlerBeanDefinition());
    beanDefinition.setInstanceSupplier(AuditingEntityListener::new);
    return beanDefinition;
  }
}
