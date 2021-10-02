package com.justeat.gamemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.context.request.RequestContextHolder;

import com.sun.net.httpserver.HttpContext;


@SpringBootApplication
public class GamemanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamemanagerApplication.class, args);
	}

}
