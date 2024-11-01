package com.dogognon.sohliou.kone.NouvelleProcedure;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.annotation.ImportAwareAotBeanPostProcessor;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertySourceDescriptor;
import org.springframework.core.io.support.PropertySourceProcessor;

/**
 * {@link ApplicationContextInitializer} to restore an application context based on previous AOT processing.
 */
@Generated
public class NouvelleProcedureApplication__ApplicationContextInitializer implements ApplicationContextInitializer<GenericApplicationContext> {
  @Override
  public void initialize(GenericApplicationContext applicationContext) {
    DefaultListableBeanFactory beanFactory = applicationContext.getDefaultListableBeanFactory();
    beanFactory.setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
    beanFactory.setDependencyComparator(AnnotationAwareOrderComparator.INSTANCE);
    processPropertySources(applicationContext.getEnvironment(), applicationContext);
    addImportAwareBeanPostProcessors(beanFactory);
    new NouvelleProcedureApplication__BeanFactoryRegistrations().registerBeanDefinitions(beanFactory);
    new NouvelleProcedureApplication__BeanFactoryRegistrations().registerAliases(beanFactory);
  }

  /**
   * Apply known @PropertySources to the environment.
   */
  private void processPropertySources(ConfigurableEnvironment environment,
      ResourceLoader resourceLoader) {
    PropertySourceProcessor processor = new PropertySourceProcessor(environment, resourceLoader);
    try {
      processor.processPropertySource(new PropertySourceDescriptor(List.of("classpath:application.properties", "file:nouvelle-procedure.config"), true, null, null, null));
    } catch (IOException ex) {
      throw new UncheckedIOException(ex);
    }
  }

  /**
   * Add ImportAwareBeanPostProcessor to support ImportAware beans.
   */
  private void addImportAwareBeanPostProcessors(DefaultListableBeanFactory beanFactory) {
    Map<String, String> mappings = new HashMap<>();
    mappings.put("org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration", "com.dogognon.sohliou.kone.security.config.SecurityConfig");
    mappings.put("org.springframework.security.config.annotation.method.configuration.PrePostMethodSecurityConfiguration", "com.dogognon.sohliou.kone.security.config.SecurityConfig");
    mappings.put("org.springframework.security.config.annotation.method.configuration.SecuredMethodSecurityConfiguration", "com.dogognon.sohliou.kone.security.config.SecurityConfig");
    mappings.put("org.springframework.security.config.annotation.method.configuration.Jsr250MethodSecurityConfiguration", "com.dogognon.sohliou.kone.security.config.SecurityConfig");
    mappings.put("org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration", "org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration$EnableTransactionManagementConfiguration$CglibAutoProxyConfiguration");
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ImportAwareAotBeanPostProcessor.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.setInstanceSupplier(() -> new ImportAwareAotBeanPostProcessor(mappings));
    beanFactory.registerBeanDefinition("org.springframework.context.annotation.internalImportAwareAotProcessor", beanDefinition);
  }
}
