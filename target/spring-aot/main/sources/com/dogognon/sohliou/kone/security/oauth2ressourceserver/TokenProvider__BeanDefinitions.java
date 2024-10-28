package com.dogognon.sohliou.kone.security.oauth2ressourceserver;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassUtils;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;

/**
 * Bean definitions for {@link TokenProvider}.
 */
@Generated
public class TokenProvider__BeanDefinitions {
  /**
   * Get the bean definition for 'tokenProvider'.
   */
  public static BeanDefinition getTokenProviderBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(TokenProvider.class);
    beanDefinition.setTargetType(TokenProvider.class);
    ConfigurationClassUtils.initializeConfigurationClass(TokenProvider.class);
    InstanceSupplier<TokenProvider> instanceSupplier = InstanceSupplier.using(TokenProvider$$SpringCGLIB$$0::new);
    instanceSupplier = instanceSupplier.andThen(TokenProvider__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'jwtEncoder'.
   */
  private static BeanInstanceSupplier<JwtEncoder> getJwtEncoderInstanceSupplier() {
    return BeanInstanceSupplier.<JwtEncoder>forFactoryMethod(TokenProvider.class, "jwtEncoder")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(TokenProvider.class).jwtEncoder());
  }

  /**
   * Get the bean definition for 'jwtEncoder'.
   */
  public static BeanDefinition getJwtEncoderBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(JwtEncoder.class);
    beanDefinition.setInstanceSupplier(getJwtEncoderInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'jwtDecoder'.
   */
  private static BeanInstanceSupplier<JwtDecoder> getJwtDecoderInstanceSupplier() {
    return BeanInstanceSupplier.<JwtDecoder>forFactoryMethod(TokenProvider.class, "jwtDecoder")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(TokenProvider.class).jwtDecoder());
  }

  /**
   * Get the bean definition for 'jwtDecoder'.
   */
  public static BeanDefinition getJwtDecoderBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(JwtDecoder.class);
    beanDefinition.setInstanceSupplier(getJwtDecoderInstanceSupplier());
    return beanDefinition;
  }
}
