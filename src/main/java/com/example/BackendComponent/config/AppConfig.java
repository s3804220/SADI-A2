package com.example.BackendComponent.config;

import com.example.BackendComponent.entity.*;

import com.example.BackendComponent.repository.CategoryRepository;
import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Properties;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Configuration
@EnableTransactionManagement
@EnableWebMvc
@EnableJpaRepositories("com.example.BackendComponent.repository")

public class AppConfig {
    @Bean
    public Category category() {
        return new Category();
    }

    @Bean
    public Product product(){return new Product();}

    @Bean
    public Staff staff(){return new Staff();}

    @Bean
    public Customer customer(){return new Customer();}

    @Bean
    public Provider provider(){return new Provider();}

    @Bean
    public Order order(){return new Order();}
//    @Bean
//    public JdbcTemplate jdbcTemplate(){return new JdbcTemplate();}

    @Bean(name="entityManagerFactory")
    public LocalSessionFactoryBean SessionFactory(){

        Properties properties = new Properties();
        //For Postgresql
        //properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL82Dialect");
        //For mysql
        //properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.hbm2ddl.auto", "update");

        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();

//        sessionFactoryBean.setPackagesToScan("com.example.practice2.model");
        sessionFactoryBean.setPackagesToScan("com.example.BackendComponent.entity");

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/enterprise");
        dataSource.setUsername("postgres");
        dataSource.setPassword("aBc999*");

        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setHibernateProperties(properties);

        return sessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager tx = new HibernateTransactionManager(sessionFactory);

        return tx;
    }

}