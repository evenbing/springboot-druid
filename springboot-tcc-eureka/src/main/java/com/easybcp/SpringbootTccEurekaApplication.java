package com.easybcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringbootTccEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTccEurekaApplication.class, args);
	}
}
