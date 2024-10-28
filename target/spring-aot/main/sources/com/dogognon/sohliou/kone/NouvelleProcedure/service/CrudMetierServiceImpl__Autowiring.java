package com.dogognon.sohliou.kone.NouvelleProcedure.service;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link CrudMetierServiceImpl}.
 */
@Generated
public class CrudMetierServiceImpl__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static CrudMetierServiceImpl apply(RegisteredBean registeredBean,
      CrudMetierServiceImpl instance) {
    instance.departementRepository = AutowiredFieldValueResolver.forRequiredField("departementRepository").resolve(registeredBean);
    instance.procedureRepository = AutowiredFieldValueResolver.forRequiredField("procedureRepository").resolve(registeredBean);
    instance.posteRepository = AutowiredFieldValueResolver.forRequiredField("posteRepository").resolve(registeredBean);
    instance.serviceRepository = AutowiredFieldValueResolver.forRequiredField("serviceRepository").resolve(registeredBean);
    instance.fileRepository = AutowiredFieldValueResolver.forRequiredField("fileRepository").resolve(registeredBean);
    instance.userRepository = AutowiredFieldValueResolver.forRequiredField("userRepository").resolve(registeredBean);
    instance.roleRepository = AutowiredFieldValueResolver.forRequiredField("roleRepository").resolve(registeredBean);
    AutowiredFieldValueResolver.forRequiredField("fileStorageService").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
