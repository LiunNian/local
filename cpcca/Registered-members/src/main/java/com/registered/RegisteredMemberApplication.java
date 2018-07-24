package com.registered;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;



@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableScheduling
@MapperScan("com.registered.*.dao")
public class RegisteredMemberApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegisteredMemberApplication.class, args);
	}
}


