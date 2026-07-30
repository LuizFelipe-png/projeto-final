package com.main.ProjetoFinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ProjetoFinalApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(ProjetoFinalApplication.class, args);
	}
}
