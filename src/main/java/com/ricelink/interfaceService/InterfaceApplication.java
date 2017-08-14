package com.ricelink.interfaceService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.ricelink")
@ServletComponentScan("com.ricelink")
@SpringBootApplication
public class InterfaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterfaceApplication.class, args);
	}
}
