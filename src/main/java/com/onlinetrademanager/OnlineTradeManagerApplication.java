package com.onlinetrademanager;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class OnlineTradeManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineTradeManagerApplication.class, args);
	}

}
