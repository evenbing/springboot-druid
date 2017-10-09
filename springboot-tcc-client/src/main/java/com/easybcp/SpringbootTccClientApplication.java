package com.easybcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class SpringbootTccClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTccClientApplication.class, args);
	}
}
