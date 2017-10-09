package com.easybcp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.easybcp.*.dao")
public class SpringbootDruidOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDruidOneApplication.class, args);
	}
}
