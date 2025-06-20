package com.pluralsight.dealership.config;

import javax.sql.DataSource;

import com.pluralsight.dealership.dao.DealershipDAO;
import com.pluralsight.dealership.dao.ProductDAOJDBCImpl;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {

    @Bean
    public DataSource dataSource(
            @Value("${datasource.url}") String url,
            @Value("${datasource.username}") String username,
            @Value("${datasource.password}") String password) {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }

    public DealershipDAO dealershipDAO(DataSource dataSource) {
        return new ProductDAOJDBCImpl(dataSource);
    }


}