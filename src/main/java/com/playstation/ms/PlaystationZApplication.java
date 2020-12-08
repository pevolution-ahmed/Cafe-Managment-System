package com.playstation.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.playstation.ms.services.UserRepository;
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@SpringBootApplication
public class PlaystationZApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaystationZApplication.class, args);
	}

    @Configuration
    static class MyConfig implements WebMvcConfigurer{ 
    	@Override
    	public void addFormatters(FormatterRegistry registry) {
    		// TODO Auto-generated method stub
    		WebMvcConfigurer.super.addFormatters(registry);
    	}
        
    }

}
