package com.chinazxt.stdzfp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableScheduling
@ComponentScan
public class ChinazxtApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChinazxtApplication.class, args);
	}
}
