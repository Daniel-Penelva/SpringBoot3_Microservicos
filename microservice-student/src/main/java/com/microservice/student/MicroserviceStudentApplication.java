package com.microservice.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceStudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceStudentApplication.class, args);
	}

}

/**
 * @EnableDiscoveryClient -  é uma anotação do Spring Cloud usada para habilitar a integração de um aplicativo Spring Boot com serviços de descoberta
 * de microservices, como o Eureka, Consul ou ZooKeeper. Ela faz parte do Spring Cloud Discovery e é frequentemente usada em conjunto com serviços de
 * registro e descoberta, como o Eureka Server, para permitir que seu aplicativo seja registrado e descoberto por outros serviços em um ambiente de
 * microsserviços.*/