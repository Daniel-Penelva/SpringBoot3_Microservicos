package com.microservice.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceCourseApplication.class, args);
	}

}

/**
 * A anotação @EnableFeignClients é uma anotação do Spring Cloud que é usada para habilitar o uso do Feign, um cliente HTTP declarativo, em um
 * aplicativo Spring Boot. O Feign é uma biblioteca que simplifica a comunicação com serviços HTTP, especialmente em um ambiente de microservices.
 * Com o Feign, você pode criar interfaces Java que descrevem as chamadas aos serviços remotos e, em seguida, usá-las de forma semelhante a invocações
 * de métodos locais. A anotação @EnableFeignClients permite que você aproveite essa funcionalidade em seu aplicativo.
 *
 * Em resumo, a anotação @EnableFeignClients é usada para habilitar a integração do Feign em um aplicativo Spring Boot, simplificando a comunicação
 * com serviços HTTP externos, especialmente em um ambiente de microservices. Ela é uma ferramenta valiosa para construir aplicativos que dependem
 * de interações com serviços remotos de forma mais eficiente e declarativa.*/