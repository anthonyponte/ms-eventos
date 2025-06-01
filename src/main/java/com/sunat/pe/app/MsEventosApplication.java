package com.sunat.pe.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsEventosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsEventosApplication.class, args);
	}

}
