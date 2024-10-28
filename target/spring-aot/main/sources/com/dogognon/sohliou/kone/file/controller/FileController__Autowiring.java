package com.dogognon.sohliou.kone.file.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link FileController}.
 */
@Generated
public class FileController__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static FileController apply(RegisteredBean registeredBean, FileController instance) {
    AutowiredFieldValueResolver.forRequiredField("fileStorageService").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
