package com.dogognon.sohliou.kone.security.config;

import java.lang.Class;
import java.lang.String;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RegisteredBean;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.context.properties.ConstructorBound;
import org.springframework.boot.context.properties.bind.BindMethod;

/**
 * Bean definitions for {@link RsakeysConfig}.
 */
@Generated
public class RsakeysConfig__BeanDefinitions {
  /**
   * Get the bean instance for 'rsa-com.dogognon.sohliou.kone.security.config.RsakeysConfig'.
   */
  private static RsakeysConfig getRsakeysConfigInstance(RegisteredBean registeredBean) {
    BeanFactory beanFactory = registeredBean.getBeanFactory();
    String beanName = registeredBean.getBeanName();
    Class<?> beanClass = registeredBean.getBeanClass();
    return (RsakeysConfig) ConstructorBound.from(beanFactory, beanName, beanClass);
  }

  /**
   * Get the bean definition for 'rsakeysConfig'.
   */
  public static BeanDefinition getRsakeysConfigBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RsakeysConfig.class);
    beanDefinition.setAttribute("org.springframework.boot.context.properties.bind.BindMethod", BindMethod.VALUE_OBJECT);
    beanDefinition.setInstanceSupplier(InstanceSupplier.of(RsakeysConfig__BeanDefinitions::getRsakeysConfigInstance));
    return beanDefinition;
  }
}
