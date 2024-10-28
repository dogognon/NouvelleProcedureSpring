package com.dogognon.sohliou.kone.file;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link FileStorageServiceImpl}.
 */
@Generated
public class FileStorageServiceImpl__BeanDefinitions {
  /**
   * Get the bean definition for 'fileStorageServiceImpl'.
   */
  public static BeanDefinition getFileStorageServiceImplBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(FileStorageServiceImpl.class);
    InstanceSupplier<FileStorageServiceImpl> instanceSupplier = InstanceSupplier.using(FileStorageServiceImpl::new);
    instanceSupplier = instanceSupplier.andThen(FileStorageServiceImpl__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
