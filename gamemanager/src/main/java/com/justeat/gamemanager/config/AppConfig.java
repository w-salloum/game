package com.justeat.gamemanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

import com.justeat.gamemanager.dto.Game;

@Configuration
public class AppConfig {
	
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
	

}
