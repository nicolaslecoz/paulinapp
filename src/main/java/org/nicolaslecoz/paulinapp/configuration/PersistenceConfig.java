package org.nicolaslecoz.paulinapp.configuration;

import org.nicolaslecoz.paulinapp.domain.EtatApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@EntityScan(basePackageClasses = EtatApplication.class)
public class PersistenceConfig  {

}

