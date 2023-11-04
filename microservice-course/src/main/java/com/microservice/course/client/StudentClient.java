package com.microservice.course.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-student", url="localhost:8090/api/student")
public interface StudentClient {

    @GetMapping("/search-ny-course/{idCourse}")
    List<?> findAllStudentByCourse(@PathVariable Long idCourse);
}


/**
 * A anotação @FeignClient é uma anotação do Spring Cloud que é usada em conjunto com o Feign para declarar um cliente HTTP em um aplicativo Spring
 * Boot. Ela permite que você crie interfaces Java que descrevem chamadas a serviços HTTP externos e, em seguida, gera automaticamente implementações
 * dessas interfaces. Essas implementações do Feign simplificam a comunicação com serviços remotos, especialmente em um ambiente de microservices.
 *
 * A minha anotação é para criar um cliente HTTP declarativo em um aplicativo Spring Boot que se comunica com um serviço externo chamado "msvc-student".
 *
 * A url: O parâmetro url especifica o URL base para o serviço externo que o cliente Feign irá acessar. Neste caso, o URL base é
 * "http://localhost:8090/api/student", o que significa que todas as chamadas feitas pelo cliente Feign serão relativas a esse URL.
 *
 * Neste cenário, o cliente Feign está configurado para se comunicar diretamente com o serviço "msvc-student" usando o URL
 * "http://localhost:8090/api/student". Isso é útil quando você não deseja depender de um servidor de descoberta para localizar o serviço e quando sabe
 * exatamente o URL do serviço externo com o qual deseja se comunicar.
 *
 * No geral, o script representa a configuração de um cliente Feign chamado "msvc-student" que se comunica com um serviço externo localizado no URL
 * base "http://localhost:8090/api/student". A interface StudentClient define o método findAllStudentByCourse que é usado para buscar estudantes por
 * um ID de curso específico.
 * */