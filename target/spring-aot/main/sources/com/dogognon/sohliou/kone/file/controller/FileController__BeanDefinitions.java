package com.dogognon.sohliou.kone.file.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link FileController}.
 */
@Generated
public class FileController__BeanDefinitions {
  /**
   * Get the bean definition for 'fileController'.
   */
  public static BeanDefinition getFileControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(FileController.class);
    InstanceSupplier<FileController> instanceSupplier = InstanceSupplier.using(FileController::new);
    instanceSupplier = instanceSupplier.andThen(FileController__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
