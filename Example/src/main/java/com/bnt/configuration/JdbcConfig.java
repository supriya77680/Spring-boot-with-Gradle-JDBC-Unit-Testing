package com.bnt.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/* Overall, this class allows you to configure the DataSource bean for JDBC operations in a 
Spring application in a flexible and externalized manner, allowing easy customization through 
the application.properties file. */

@Configuration
public class JdbcConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

        /* DataSource is an interface provided by JDBC for connecting to a database. */

    @Bean
    public DataSource dataSource() {

        /*
        Driver Manager is a service of the JDBC APIs that manages the drivers and establishes a 
        connection between a JDBC client and a database server.
        Driver Manager is a class.
        */

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
