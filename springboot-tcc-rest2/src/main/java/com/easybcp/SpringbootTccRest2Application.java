package com.easybcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableEurekaClient
public class SpringbootTccRest2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTccRest2Application.class, args);
	}
}
