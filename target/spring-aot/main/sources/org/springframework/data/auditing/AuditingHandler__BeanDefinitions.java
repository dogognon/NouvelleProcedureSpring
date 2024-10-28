package org.springframework.data.auditing;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.data.mapping.context.MappingContext;

/**
 * Bean definitions for {@link AuditingHandler}.
 */
@Generated
public class AuditingHandler__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'jpaAuditingHandler'.
   */
  private static BeanInstanceSupplier<AuditingHandler> getJpaAuditingHandlerInstanceSupplier() {
    return BeanInstanceSupplier.<AuditingHandler>forFactoryMethod(AuditingHandler.class, "from", MappingContext.class)
            .withGenerator((registeredBean, args) -> AuditingHandler.from(args.get(0)));
  }

  /**
   * Get the bean definition for 'jpaAuditingHandler'.
   */
  public static BeanDefinition getJpaAuditingHandlerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AuditingHandler.class);
    beanDefinition.setTargetType(AuditingHandler.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0, new RuntimeBeanReference("jpaMappingContext"));
    beanDefinition.getPropertyValues().addPropertyValue("dateTimeForNow", true);
    beanDefinition.getPropertyValues().addPropertyValue("modifyOnCreation", true);
    beanDefinition.getPropertyValues().addPropertyValue("dateTimeProvider", CurrentDateTimeProvider.INSTANCE);
    beanDefinition.setInstanceSupplier(getJpaAuditingHandlerInstanceSupplier());
    return beanDefinition;
  }
}
