package com.elecao.teste.java.teste.java.bancoDeDados;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//Essa classe pe para rodar o banco de dados no Heroku

/*@Configuration
@Profile("prod")*/
public class DataConfigurationPostgreSQL {

	/*@Bean
	public BasicDataSource dataSource() throws URISyntaxException {
		URI dbUri = new URI(System.getenv("DATABASE_URL"));

		String username = "rbflakaulzxabp";
		String password = "0c8775db683e0f0eceaab671afd43bbed0f08abafbce48b5ed42681c207989a3";
		String dbUrl = "jdbc:postgresql://ec2-18-210-214-86.compute-1.amazonaws.com:5432/d10f4s3djl7nb6?sslmode=require";

		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setUrl(dbUrl);
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);

		return basicDataSource;
	}*/
}