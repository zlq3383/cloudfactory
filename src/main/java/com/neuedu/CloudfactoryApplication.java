package com.neuedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.neuedu.mapper")
public class CloudfactoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudfactoryApplication.class, args);
	}

}
