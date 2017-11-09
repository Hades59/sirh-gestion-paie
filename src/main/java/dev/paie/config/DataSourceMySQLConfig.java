/**
 * 
 */
package dev.paie.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author ETY3
 *
 */
@Configuration
@ComponentScan({"dev.paie.service", "dev.paie.util"})
public class DataSourceMySQLConfig {

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/sirh-paie?useSSL=false");
		dataSource.setUsername("sirh-paie");
		dataSource.setPassword("sirh-paie");
		return dataSource;
	}
}
