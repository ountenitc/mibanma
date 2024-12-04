package com.mibanma;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class MibanmaApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(MibanmaApplication.class, args);
//		final String Audio = "src/main/resources/Audio";
//
//	    
//	   
//	        File dir = new File(Audio);
//	        if (!dir.exists()) {
//	            dir.mkdirs();
//	        }
//	    
		
		
	}

}
