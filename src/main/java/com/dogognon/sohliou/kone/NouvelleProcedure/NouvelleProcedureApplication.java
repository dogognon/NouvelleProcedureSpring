package com.dogognon.sohliou.kone.NouvelleProcedure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.scheduling.annotation.EnableScheduling;

import com.dogognon.sohliou.kone.security.config.RsakeysConfig;
import com.dogognon.sohliou.kone.NouvelleProcedure.NouvelleProcedureApplication;

//@EnableScheduling
@ComponentScan({"com.dogognon.sohliou.kone.NouvelleProcedure","com.dogognon.sohliou.kone.security.exception","com.dogognon.sohliou.kone.security","com.dogognon.sohliou.kone.security.config","com.dogognon.sohliou.kone.file"})
@EntityScan(basePackageClasses = {
		NouvelleProcedureApplication.class,
		Jsr310JpaConverters.class

},basePackages ={"com.dogognon.sohliou.kone.security.data","com.dogognon.sohliou.kone.file.data"})
@EnableJpaAuditing
@EnableJpaRepositories({"com.dogognon.sohliou.kone.security.data","com.dogognon.sohliou.kone.NouvelleProcedure.data","com.dogognon.sohliou.kone.file.data"})
@EnableConfigurationProperties(RsakeysConfig.class)
@PropertySource(value = {"classpath:application.properties", "file:nouvelle-procedure.config"},
ignoreResourceNotFound = true)
@SpringBootApplication
public class NouvelleProcedureApplication {

	public static void main(String[] args) {
		SpringApplication.run(NouvelleProcedureApplication.class, args);
	}
	

}
