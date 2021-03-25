package io.lindx.sec.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import io.lindx.sec.models.User;

@Configuration
@PropertySource("classpath:hiber.properties")
public class HibernateConfig {

  private Environment environment;
  private DataSource dataSource;

  @Autowired
  public HibernateConfig(Environment environment, DataSource dataSource) {
    this.environment = environment;
    this.dataSource = dataSource;
  }

  @Bean
  public LocalSessionFactoryBean getSessionFactory() {
    
    Properties properties = new Properties();
      properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
      properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
      properties.put("hibernate.databasePlatform", environment.getProperty("hibernate.databasePlatform"));
      properties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));

    LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
      factoryBean.setDataSource(dataSource);
      factoryBean.setHibernateProperties(properties);

      factoryBean.setAnnotatedClasses(User.class);

    return factoryBean;
  }

  @Bean
  public HibernateTransactionManager transactionManager() {

    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
      transactionManager.setSessionFactory(getSessionFactory().getObject());

    return transactionManager;
  }
}
