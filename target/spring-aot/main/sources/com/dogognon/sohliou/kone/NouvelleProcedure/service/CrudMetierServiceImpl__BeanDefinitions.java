package com.dogognon.sohliou.kone.NouvelleProcedure.service;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link CrudMetierServiceImpl}.
 */
@Generated
public class CrudMetierServiceImpl__BeanDefinitions {
  /**
   * Get the bean definition for 'crudMetierServiceImpl'.
   */
  public static BeanDefinition getCrudMetierServiceImplBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(CrudMetierServiceImpl.class);
    InstanceSupplier<CrudMetierServiceImpl> instanceSupplier = InstanceSupplier.using(CrudMetierServiceImpl::new);
    instanceSupplier = instanceSupplier.andThen(CrudMetierServiceImpl__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
