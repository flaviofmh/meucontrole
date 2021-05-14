package com.gerenciador.meucontrole;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MeuControleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeuControleApplication.class, args);
	}
	
	@Bean
    public ModelMapper modelMapper() {
	    return new ModelMapper();
    }

}
