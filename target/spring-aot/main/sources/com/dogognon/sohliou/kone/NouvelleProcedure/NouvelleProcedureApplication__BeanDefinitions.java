package com.dogognon.sohliou.kone.NouvelleProcedure;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassUtils;

/**
 * Bean definitions for {@link NouvelleProcedureApplication}.
 */
@Generated
public class NouvelleProcedureApplication__BeanDefinitions {
  /**
   * Get the bean definition for 'nouvelleProcedureApplication'.
   */
  public static BeanDefinition getNouvelleProcedureApplicationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(NouvelleProcedureApplication.class);
    beanDefinition.setTargetType(NouvelleProcedureApplication.class);
    ConfigurationClassUtils.initializeConfigurationClass(NouvelleProcedureApplication.class);
    beanDefinition.setInstanceSupplier(NouvelleProcedureApplication$$SpringCGLIB$$0::new);
    return beanDefinition;
  }
}
