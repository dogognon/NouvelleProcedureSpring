package org.springframework.data.jpa.domain.support;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link AuditingBeanFactoryPostProcessor}.
 */
@Generated
public class AuditingBeanFactoryPostProcessor__BeanDefinitions {
  /**
   * Get the bean definition for 'auditingBeanFactoryPostProcessor'.
   */
  public static BeanDefinition getAuditingBeanFactoryPostProcessorBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AuditingBeanFactoryPostProcessor.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.setInstanceSupplier(AuditingBeanFactoryPostProcessor::new);
    return beanDefinition;
  }
}
