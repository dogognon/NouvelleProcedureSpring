package com.dogognon.sohliou.kone.file;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link FileStorageServiceImpl}.
 */
@Generated
public class FileStorageServiceImpl__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static FileStorageServiceImpl apply(RegisteredBean registeredBean,
      FileStorageServiceImpl instance) {
    AutowiredFieldValueResolver.forRequiredField("fileDir").resolveAndSet(registeredBean, instance);
    instance.fileRepository = AutowiredFieldValueResolver.forRequiredField("fileRepository").resolve(registeredBean);
    return instance;
  }
}
