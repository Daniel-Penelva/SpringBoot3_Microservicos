# Documentação do Microserviço Spring Boot

## 1. Visão Geral

Este é um sistema distribuído composto por vários microserviços que juntos formam uma aplicação coesa. Cada microserviço tem uma responsabilidade específica e comunica-se entre si para fornecer funcionalidades completas.

### 1.1 Módulo Pai - Projeto Maven

O módulo pai contém as configurações e dependências compartilhadas entre os módulos filhos. Ele unifica a construção e o gerenciamento de dependências.

#### 1.1.1 Criação do Projeto Maven

O projeto Maven foi utilizado o Maven Archetype, que é um modelo ou padrão para a criação de projetos. No caso, foi utilizado o arquétipo `maven-archetype-quickstart` para gerar a estrutura inicial do projeto.

**Comando Maven Archetype**

O comando utilizado para gerar o projeto Maven é o seguinte:

```bash
mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=SpringMicroservices -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```

- `-DgroupId`: Define o identificador do grupo para o projeto. No seu caso, é `com.mycompany.app`.
- `-DartifactId`: Define o identificador do artefato, ou seja, o nome do projeto. No seu caso, é `SpringMicroservices`.
- `-DarchetypeArtifactId`: Especifica o arquétipo a ser utilizado. Neste caso, é o `maven-archetype-quickstart`.
- `-DarchetypeVersion`: Define a versão do arquétipo a ser usado. No seu caso, é a versão `1.4`.
- `-DinteractiveMode=false`: Desativa o modo interativo, para que não seja necessário fornecer informações manualmente durante a criação do projeto.

#### 1.1.2 Detalhes do Arquivo POM

O arquivo `pom.xml` do módulo pai possui as seguintes configurações:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <!-- Configuração do Spring Boot Starter Parent -->
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.5.4</version>
  </parent>

  <!-- Informações do Projeto -->
  <groupId>com.mycompany.app</groupId>
  <artifactId>SpringMicroservices</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>pom</packaging>

  <!-- Módulos Filhos -->
  <modules>
    <module>microservice-gateway</module>
    <module>microservice-eureka</module>
    <module>microservice-config</module>
    <module>microservice-student</module>
    <module>microservice-course</module>
  </modules>

  <!-- Propriedades do Projeto -->
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
  </properties>

  <!-- Dependências -->
  <dependencies>
    <!-- Lombok para reduzir a verbosidade do código -->
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <version>1.18.22</version>
    </dependency>
  </dependencies>

  <!-- Configuração do Plugin Maven para Spring Boot -->
  <build>
    <pluginManagement>
      <plugins>
        <plugin>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-maven-plugin</artifactId>
          <version>2.5.4</version>
          <configuration>
            <!-- Exclui a dependência do Lombok ao gerar o JAR executável -->
            <excludes>
              <exclude>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
              </exclude>
            </excludes>
          </configuration>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
</project>
```
**Configuração do Spring Boot Starter Parent**

O projeto utiliza o `spring-boot-starter-parent` como o pai, herdando configurações e dependências comuns do ecossistema Spring Boot.

```xml
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>2.5.4</version>
</parent>
```

**Informações do Projeto**

Define as informações básicas do projeto, como `groupId`, `artifactId`, e `version`.

```xml
<groupId>com.mycompany.app</groupId>
<artifactId>SpringMicroservices</artifactId>
<version>1.0-SNAPSHOT</version>
<packaging>pom</packaging>
```

**Módulos Filhos**

Lista os módulos filhos que fazem parte deste projeto Maven.

```xml
<modules>
  <module>microservice-gateway</module>
  <module>microservice-eureka</module>
  <module>microservice-config</module>
  <module>microservice-student</module>
  <module>microservice-course</module>
</modules>
```

**Propriedades do Projeto**

Define propriedades específicas do projeto, como a codificação do código-fonte e as versões do compilador Java.

```xml
<properties>
  <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  <maven.compiler.source>17</maven.compiler.source>
  <maven.compiler.target>17</maven.compiler.target>
</properties>
```

**Dependências**

Lista as dependências do projeto, como o Lombok para reduzir a verbosidade do código.

```xml
<dependencies>
  <dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.22</version>
  </dependency>
</dependencies>
```

**Configuração do Plugin Maven para Spring Boot**

Configura o plugin Maven para Spring Boot, especificamente o `spring-boot-maven-plugin`. Exclui a dependência do Lombok ao gerar o JAR executável.

```xml
<build>
  <pluginManagement>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <version>2.5.4</version>
        <configuration>
          <excludes>
            <exclude>
              <groupId>org.projectlombok</groupId>
              <artifactId>lombok</artifactId>
            </exclude>
          </excludes>
        </configuration>
      </plugin>
    </plugins>
  </pluginManagement>
</build>
```

## 2. Módulos Filhos

### 2.1 Microservice API Eureka

#### 2.1.1 Descrição

Este microserviço atua como um servidor de descoberta para permitir a comunicação dinâmica entre microserviços em um ambiente distribuído. Utiliza o Eureka Server, uma implementação do serviço de descoberta.

#### 2.1.2 Detalhes Técnicos

- **Tecnologia:** Spring Cloud Eureka
- **Pacote Principal:** `com.microservice.eureka`

#### 2.1.3 Arquivo `application.yml`

O arquivo `application.yml` contém as configurações específicas para o Microserviço API Eureka. As principais configurações são:

```yaml
spring:
  application:
    name: msvc-eureka
  config:
    import: optional:configserver:http://localhost:8888
```

- **`spring.application.name`**: Define o nome da aplicação Eureka como "msvc-eureka".
- **`spring.config.import`**: Especifica a importação opcional de configurações do Config Server localizado em "http://localhost:8888".

#### 2.1.3 Arquivo `pom.xml`

O arquivo `pom.xml` define as dependências e configurações do projeto Maven para o Microserviço API Eureka.

**Dependências**

As dependências incluídas no projeto são:

```xml
<dependencies>
    <!-- Dependências do Spring Boot -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Dependências do Spring Cloud -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-config</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>

    <!-- Dependência do MySQL Connector -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- Dependência para Testes -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

**`Spring Boot Starters`**

- `spring-boot-starter-actuator`: Fornece funcionalidades para monitoramento da aplicação.
- `spring-boot-starter-data-jpa`: Integração do Spring Data JPA para persistência de dados.
- `spring-boot-starter-web`: Configuração padrão para criar aplicativos da web usando o Spring MVC.

**`Spring Cloud Starters`**

- `spring-cloud-starter-config`: Integração com o serviço de configuração distribuída.
- `spring-cloud-starter-netflix-eureka-client`: Cliente Eureka para registrar e descobrir serviços.

**`MySQL Connector`**

- `mysql-connector-j`: Driver JDBC para integração com o MySQL.

**`Spring Boot Starter Test`**

- `spring-boot-starter-test`: Dependências para testes unitários e de integração.

**`Configurações do Maven`**

O bloco de construção do Maven define configurações específicas para o Maven, incluindo o plugin Spring Boot Maven.

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

### 2.2 Microservice API Config Server

#### 2.2.1 Descrição

O Microserviço API Config Server é responsável por centralizar as configurações da aplicação, permitindo que os microserviços acessem configurações específicas sem a necessidade de recompilação e por gerenciar as configurações externas dos outros microserviços no ambiente.

#### 2.2.2 Detalhes Técnicos

- **Tecnologia:** Spring Cloud Config
- **Pacote Principal:** `com.microservice.config`

#### 2.2.3 pom.xml

O arquivo `pom.xml` contém as configurações do Maven para o projeto Microserviço Config Server.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>com.mycompany.app</groupId>
		<artifactId>SpringMicroservices</artifactId>
		<version>1.0-SNAPSHOT</version>
	</parent>
	<groupId>com.microservice.config</groupId>
	<artifactId>microservice-config</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>microservice-config</name>
	<description>Spring Boot 3 Microservice Config Server</description>
	<properties>
		<java.version>17</java.version>
		<spring-cloud.version>2022.0.4</spring-cloud.version>
	</properties>
	<dependencies>
		<!-- Dependência do Spring Cloud Config Server -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-config-server</artifactId>
		</dependency>

		<!-- Dependência para testes -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
	<dependencyManagement>
		<dependencies>
			<!-- Dependência do Spring Cloud -->
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

	<build>
		<plugins>
			<!-- Plugin do Spring Boot Maven para construir e empacotar a aplicação -->
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>
```

**Dependências Principais**

- **`org.springframework.cloud:spring-cloud-config-server`**: Dependência para o Spring Cloud Config Server.

#### 2.2.4 application.yml

O arquivo `application.yml` contém as configurações específicas do Microserviço Config Server.

```yaml
server:
  port: 8888

spring:
  profiles:
    active: native
  application:
    name: config-server
  cloud:
    config:
      server:
        native:
          search-locations: classpath:/configurations
```

**Configurações Principais**

- **`server.port`**: Define a porta em que o Config Server será executado.
- **`spring.profiles.active: native`**: Configura o perfil ativo como `native`, indicando que as configurações são buscadas localmente.
- **`spring.application.name: config-server`**: Define o nome da aplicação, usado para identificar o Config Server no ambiente.
- **`spring.cloud.config.server.native.search-locations`**: Especifica os locais onde as configurações serão procuradas localmente. No exemplo, são procuradas no diretório `classpath:/configurations`.

#### 2.2.5 Configurações de Microserviços no Config Server

A pasta `configurations` contém os arquivos de configuração para cada microserviço. Abaixo estão as configurações específicas de cada um deles:

##### 2.2.5.1 Configurações do Microserviço Eureka (`msvc-eureka.yml`)

```yaml
server:
  port: 8761

spring:
  application:
    name: msvc-eureka

eureka:
  instance:
    hostname: localhost
  client:
    register-with-eureka: false
    fetch-registry: false
    server-url:
      defaultZone: http://localhost:${server.port}/eureka/
```

- **`server.port`**: Porta em que o Eureka Server será executado.
- **`spring.application.name`**: Nome da aplicação, usado para identificar o serviço no Eureka.
- **Configurações do Eureka Client**: Impede que este serviço se registre no Eureka e recupere informações do registro.

##### 2.2.5.2 Configurações do Microserviço Student (`msvc-student.yml`)

```yaml
server:
  port: 8090

spring:
  application:
    name: msvc-student
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/studentDb
    username: root
    password: root
  jpa:
    hibernate:
      ddl-auto: create
    database: mysql
    database-platform: org.hibernate.dialect.MySQL8Dialect

eureka:
  instance:
    hostname: localhost
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
```

- **`server.port`**: Porta em que o Microserviço Student será executado.
- **`spring.application.name`**: Nome da aplicação, usado para identificar o serviço no Eureka.
- **Configurações do Banco de Dados**: Configurações para a conexão com o banco de dados MySQL.
- **Configurações do Eureka Client**: URL do Eureka Server para registro.

##### 2.2.5.3 Configurações do Microserviço Course (`msvc-course.yml`)

```yaml
server:
  port: 9090

spring:
  application:
    name: msvc-course
  datasource:
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5432/coursesDb
    username: postgres
    password: admin
  jpa:
    hibernate:
      ddl-auto: create
    database: postgresql
    database-platform: org.hibernate.dialect.PostgreSQLDialect

eureka:
  instance:
    hostname: localhost
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
```

- **`server.port`**: Porta em que o Microserviço Course será executado.
- **`spring.application.name`**: Nome da aplicação, usado para identificar o serviço no Eureka.
- **Configurações do Banco de Dados**: Configurações para a conexão com o banco de dados PostgreSQL.
- **Configurações do Eureka Client**: URL do Eureka Server para registro.

##### 2.2.5.4 Configurações do Microserviço Gateway (`msvc-gateway.yml`)

```yaml
eureka:
  client:
    register-with-eureka: false

server:
  port: 8080

spring:
  application:
    name: msvc-gateway
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true
      routes:
        - id: students
          uri: http://localhost:8090
          predicates:
            - Path=/api/student/**
        - id: courses
          uri: http://localhost:9090
          predicates:
            - Path=/api/course/**
```

- **Configurações do Eureka Client**: Impede que este serviço se registre no Eureka.
- **`server.port`**: Porta em que o Gateway será executado.
- **`spring.application.name`**: Nome da aplicação, usado para identificar o serviço no Eureka.
- **Configurações do Gateway**: Configurações de roteamento para os microserviços Student e Course.

Essas configurações são essenciais para a integração bem-sucedida dos microserviços no ambiente de microserviços.

#### 2.2.6 Observações

- Este microserviço é configurado para buscar as configurações localmente (`native`). Pode ser estendido para sincronizar com repositórios remotos, como o GitHub.
- Este microserviço geralmente é o primeiro a ser executado no ambiente, pois fornece as configurações necessárias para outros microserviços.

### 2.3 Microservice API Student

#### 2.3.1 Descrição

Fornece funcionalidades relacionadas a estudantes, como cadastro, consulta e atualização. Divide-se nas camadas de entidade, persistência, serviço e controlador.

#### 2.3.2 Detalhes Técnicos

- **Tecnologia:** Spring Web, Spring Data JPA
- **Pacote Principal:** `com.microservice.student`

#### 2.3.3 Entidade `Student`

A classe `Student` representa a entidade de estudante no contexto do Microserviço Student. Esta entidade é mapeada para uma tabela chamada "students" no banco de dados.

```java
package com.microservice.student.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    @Column(name = "last_name")
    private String lastname;
    
    private String email;

    @Column(name = "course_id")
    private Long courseId;
}
```

- **`@Entity`**: Indica que a classe é uma entidade JPA, sendo mapeada para uma tabela no banco de dados.
- **`@Data`**: Do projeto Lombok, gera automaticamente os métodos `toString`, `equals`, `hashCode`, os getters e setters.
- **`@Builder`**: Do projeto Lombok, facilita a construção de objetos através de um padrão de construção fluente.
- **`@Table`**: Permite especificar o nome da tabela no banco de dados.
- **`@Id`**: Indica o campo que é a chave primária da entidade.
- **`@GeneratedValue`**: Especifica a estratégia de geração de valor automático para a chave primária.
- **`@Column`**: Permite especificar o nome da coluna no banco de dados.
- **Campos da Classe**:
  - `private Long id`: Representa o id do course.
  - `private String name`: Representa o nome do estudante.
  - `private String lastname`: Representa o sobrenome do estudante.
  - `private String email`: Representa o endereço de e-mail do estudante.
  - `private Long courseId`: Representa o ID do curso ao qual o estudante está associado.

#### 2.3.4 Repositório `StudentRepository`

O `StudentRepository` no pacote `com.microservice.student.persistence` é um repositório personalizado que estende `CrudRepository`. 

```java
package com.microservice.student.persistence;

import com.microservice.student.entities.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    /**
     * Este método busca todos os estudantes que têm o id do curso.
     */
    @Query("SELECT s FROM Student s WHERE s.courseId = :idCourse")
    List<Student> findAllStudents(Long idCourse);
}
```

- **`@Repository`**: Indica que a interface é um componente do Spring e será automaticamente detectada durante a varredura de componentes.

- **Extensão do `CrudRepository<Student, Long>`**: Essa interface estende o `CrudRepository` do Spring Data JPA, fornecendo métodos CRUD padrão para a entidade `Student`. O tipo `Long` refere-se ao tipo da chave primária da entidade.

**Método Personalizado `findAllStudents`**

```java
// Este método busca todos os estudantes que têm o id do curso.
@Query("SELECT s FROM Student s WHERE s.courseId = :idCourse")
List<Student> findAllStudents(Long idCourse);
```

- **`@Query`**: Essa anotação é usada para especificar uma consulta personalizada em JPQL (Java Persistence Query Language). Neste caso, a consulta retorna todos os estudantes cujo `courseId` é igual ao parâmetro `idCourse`.

**Alternativa usando Naming Convention**

```java
/**
 * Ou pode fazer assim:
 * List<Student> findAllByCourseId(Long idCourse);
 */
```

- Essa é uma alternativa mais simples usando a convenção de nomenclatura do Spring Data JPA. O método `findAllByCourseId` será automaticamente interpretado pelo Spring Data JPA como uma consulta para encontrar todos os estudantes por `courseId`.

#### 2.3.4 Camada de Serviço do Microserviço Student

A camada de serviço no pacote `com.microservice.student.service` é responsável por implementar a lógica de negócios associada aos estudantes. Vamos explorar a interface `IStudentService` e sua implementação `StudentServiceImpl`.

##### 2.3.4.1 Interface `IStudentService`

A interface `IStudentService` define os métodos que a camada de serviço deve implementar para realizar operações relacionadas aos estudantes.

```java
package com.microservice.student.service;

import com.microservice.student.entities.Student;

import java.util.List;

public interface IStudentService {

    List<Student> findall();

    Student findById(Long id);

    void save(Student student);

    List<Student> findByIdCourse(Long idCourse);
}
```

**Métodos da Interface**

- **`findall()`**: Retorna uma lista de todos os estudantes.
- **`findById(Long id)`**: Retorna um estudante com base no ID fornecido.
- **`save(Student student)`**: Salva um novo estudante ou atualiza um existente.
- **`findByIdCourse(Long idCourse)`**: Retorna uma lista de estudantes com base no ID do curso.

##### 2.3.4.2 Implementação `StudentServiceImpl`

A classe `StudentServiceImpl` implementa a interface `IStudentService` e fornece a implementação concreta dos métodos definidos.

```java
package com.microservice.student.service;

import com.microservice.student.entities.Student;
import com.microservice.student.persistence.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> findall() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Student> findByIdCourse(Long idCourse) {
        return studentRepository.findAllStudents(idCourse);
    }
}
```

**Anotações**

- **`@Service`**: Indica que a classe é um componente de serviço do Spring e será automaticamente detectada durante a varredura de componentes.

**Métodos Implementados**

- **`findall()`**: Utiliza o método `findAll()` do repositório para recuperar todos os estudantes.
- **`findById(Long id)`**: Utiliza o método `findById()` do repositório para recuperar um estudante com base no ID.
- **`save(Student student)`**: Utiliza o método `save()` do repositório para salvar ou atualizar um estudante.
- **`findByIdCourse(Long idCourse)`**: Utiliza o método personalizado `findAllStudents()` do repositório para recuperar estudantes com base no ID do curso.

#### 2.3.5 Controlador `StudentController`

O `StudentController` no pacote `com.microservice.student.controller` é responsável por lidar com as solicitações HTTP relacionadas aos estudantes. 

**Anotações e Configurações do Controller**

```java
package com.microservice.student.controller;

import com.microservice.student.entities.Student;
import com.microservice.student.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private IStudentService studentService;
```

- **`@RestController`**: Indica que esta classe é um controlador Spring MVC e que cada método retorna um objeto que será serializado diretamente para o corpo da resposta HTTP.
  
- **`@RequestMapping("/api/student")`**: Define o mapeamento de URL de nível de classe para todas as operações do controlador. Todas as URLs fornecidas são relativas a "/api/student".

- **`@Autowired`**: Indica que o Spring deve injetar automaticamente uma instância de `IStudentService` no controlador.

**Criação de um Estudante**

```java
    // Criar um estudante - http://localhost:8090/api/student/create
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStudent(@RequestBody Student student) {
        studentService.save(student);
    }
```

- **`@PostMapping("/create")`**: Mapeia a URL "/api/student/create" para a operação de criação de estudante usando o método HTTP POST.

- **`@ResponseStatus(HttpStatus.CREATED)`**: Retorna o status HTTP 201 (CREATED) para indicar que o recurso foi criado com sucesso.

- **`@RequestBody Student student`**: Indica que o corpo da solicitação HTTP deve ser convertido para um objeto `Student`. Isso é feito automaticamente pelo Spring.

**Busca de Todos os Estudantes**

```java
    // Buscar todos os estudantes - http://localhost:8090/api/student/all
    @GetMapping("/all")
    public ResponseEntity<?> findAllStudent() {
        return ResponseEntity.ok(studentService.findall());
    }
```

- **`@GetMapping("/all")`**: Mapeia a URL "/api/student/all" para a operação de busca de todos os estudantes usando o método HTTP GET.

- **`ResponseEntity.ok(studentService.findall())`**: Retorna uma resposta HTTP 200 (OK) com o corpo contendo a lista de todos os estudantes.

**Busca de Estudante por ID**

```java
    // Buscar estudante por id - http://localhost:8090/api/student/search/1
    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findById(id));
    }
```

- **`@GetMapping("/search/{id}")`**: Mapeia a URL "/api/student/search/{id}" para a operação de busca de estudante por ID usando o método HTTP GET.

- **`@PathVariable Long id`**: Captura o valor da variável de caminho na URL e a associa ao parâmetro do método.

**Busca de Estudantes por ID do Curso**

```java
    // Buscar estudantes por id do curso - http://localhost:8090/api/student/search-ny-course/2
    @GetMapping("/search-ny-course/{idCourse}")
    public ResponseEntity<?> findByIdCourse(@PathVariable("idCourse") Long idCourse){
        return ResponseEntity.ok(studentService.findByIdCourse(idCourse));
    }
```

- **`@GetMapping("/search-ny-course/{idCourse}")`**: Mapeia a URL "/api/student/search-ny-course/{idCourse}" para a operação de busca de estudantes por ID do curso usando o método HTTP GET.

- **`@PathVariable("idCourse") Long idCourse`**: Captura o valor da variável de caminho na URL e a associa ao parâmetro do método.


#### 2.3.4 Classe Principal MicroserviceStudentApplication com `@EnableDiscoveryClient`

A anotação `@EnableDiscoveryClient` é usada para habilitar a descoberta de serviços no Eureka Server. Vamos adicionar documentação apropriada à classe principal do Microserviço Student.

```java
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
```

**Anotação `@EnableDiscoveryClient`**

- **`@EnableDiscoveryClient`**: Esta anotação, parte do Spring Cloud, é usada para habilitar a descoberta de serviços. O aplicativo registrado com o Eureka Server pode ser descoberto por outros serviços e pode descobrir outros serviços registrados.

**Anotação `@SpringBootApplication`**

- **`@SpringBootApplication`**: Esta anotação combina várias anotações, incluindo `@Configuration`, `@EnableAutoConfiguration`, e `@ComponentScan`. Ela é usada para indicar que esta classe é a classe principal de uma aplicação Spring Boot.

#### 2.3.5 pom.xml

O arquivo `pom.xml` contém as configurações do Maven para o projeto Microserviço Student. Vamos documentar as principais seções e dependências.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>com.mycompany.app</groupId>
		<artifactId>SpringMicroservices</artifactId>
		<version>1.0-SNAPSHOT</version>
	</parent>
	<groupId>com.microservice.student</groupId>
	<artifactId>microservice-student</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>microservice-student</name>
	<description>Spring Boot 3 Microservice Student</description>
	<properties>
		<java.version>17</java.version>
		<spring-cloud.version>2022.0.4</spring-cloud.version>
	</properties>
	<dependencies>
		<!-- Dependências do Spring Boot -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<!-- Dependências do Spring Cloud para integração com Eureka Server e Config Server -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>

		<!-- Dependência do MySQL Connector -->
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>

		<!-- Dependência para testes -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
	<dependencyManagement>
		<dependencies>
			<!-- Dependência do Spring Cloud -->
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

	<build>
		<plugins>
			<!-- Plugin do Spring Boot Maven para construir e empacotar a aplicação -->
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>
```

**Dependências Principais**

- **Spring Boot Starter Dependencies**: Conjunto de dependências básicas para um aplicativo Spring Boot.
- **Spring Cloud Starter Config**: Integração com o Config Server para gerenciamento de configurações externas.
- **Spring Cloud Starter Netflix Eureka Client**: Integração com o Eureka Server para descoberta de serviços.
- **MySQL Connector**: Driver JDBC para MySQL.
- **Spring Boot Starter Test**: Dependência para testes.

**Configurações Adicionais**

- **`<java.version>`**: Versão do Java para o projeto.
- **`<spring-cloud.version>`**: Versão do Spring Cloud para as dependências.

#### 2.3.6 `application.yml`

O arquivo `application.yml` contém configurações específicas do Microserviço Student, incluindo a configuração para integrar-se com o Config Server.

```yaml
spring:
  application:
    name: msvc-student
  config:
    import: optional:configserver:http://localhost:8888
```

**Configurações Principais**

- **`spring.application.name`**: Define o nome da aplicação, que será usado para registrar o serviço no Eureka Server.
- **`spring.config.import`**: Especifica a importação opcional das configurações do Config Server. Neste caso, o Config Server está em `http://localhost:8888`.

### 2.4 Microservice API Course

#### 2.4.1 Descrição

Oferece funcionalidades relacionadas a cursos, incluindo operações de criação, leitura e atualização. Estruturado em entidade, persistência, serviço e controlador.

#### 2.4.2 Detalhes Técnicos

- **Tecnologia:** Spring Web, Spring Data JPA
- **Pacote Principal:** `com.microservice.course`

#### 2.4.2 Entidade `Course`

```java
package com.microservice.course.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String teacher;
}
```

- **`@Entity`**: Indica que a classe é uma entidade JPA.
- **`@Table(name = "courses")`**: Especifica o nome da tabela no banco de dados.
- **`@Id`**: Indica que o campo é a chave primária da entidade.
- **`@GeneratedValue(strategy = GenerationType.IDENTITY)`**: Configura a estratégia de geração de valor automático para a chave primária.
- **Campos da Classe**:
  - `private Long id`: Representa o id do course.
  - `private String name`: Representa o nome do curso.
  - `private String teacher`: Representa o nome do professor do curso.
  
#### 2.4.2 `CourseRepository.java`

```java
package com.microservice.course.persistence;

import com.microservice.course.entities.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
}
```

- **`@Repository`**: Indica que a classe é um componente de repositório gerenciado pelo Spring.
- **`CrudRepository<Course, Long>`**: Interface que fornece operações CRUD básicas para a entidade Course, incluindo salvar, buscar, atualizar e excluir.

Esses componentes formam a base de dados do Microserviço Course, permitindo a persistência e recuperação de informações relacionadas a cursos. Certifique-se de que esses componentes estejam alinhados com os requisitos específicos do seu projeto.

#### 2.4.3 Camada de Serviço do Microserviço Course

A camada de serviço no pacote `com.microservice.course.service` é responsável por implementar a lógica de negócios associada aos cursos. Vamos explorar a interface `ICourseService` e sua implementação `CourseServiceImpl`.

##### 2.4.3.1 Interface `ICourseService`

```java
package com.microservice.course.service;

import com.microservice.course.entities.Course;
import com.microservice.course.http.response.StudentByCourseResponse;

import java.util.List;

public interface ICourseService {

    List<Course> findAll();

    Course findById(Long id);

    void save(Course course);

    StudentByCourseResponse findStudentsByIdCourse(Long idCourse);
}
```

**Explicação:**

- **`ICourseService`**: Esta é uma interface que define um contrato para as operações relacionadas ao serviço do Microserviço Course.

- **`List<Course> findAll()`**: Método que retorna uma lista de todos os cursos.

- **`Course findById(Long id)`**: Método que retorna um curso com base no seu ID.

- **`void save(Course course)`**: Método que salva um novo curso.

- **`StudentByCourseResponse findStudentsByIdCourse(Long idCourse)`**: Método que retorna informações sobre os estudantes associados a um determinado curso.

---

##### 2.4.3.2 `CourseServiceImpl`

```java
package com.microservice.course.service;

import com.microservice.course.client.StudentClient;
import com.microservice.course.dto.StudentDTO;
import com.microservice.course.entities.Course;
import com.microservice.course.http.response.StudentByCourseResponse;
import com.microservice.course.persistence.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentClient studentClient;

    @Override
    public List<Course> findAll() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    public StudentByCourseResponse findStudentsByIdCourse(Long idCourse) {

        // Consultar o curso pelo o id
        Course course = courseRepository.findById(idCourse).orElse(new Course());

        // Obter os estudantes
        List<StudentDTO> studentDTOList = studentClient.findAllStudentByCourse(idCourse);

        return StudentByCourseResponse.builder()
                .courseName(course.getName())
                .teacher(course.getTeacher())
                .studentDTOList(studentDTOList)
                .build();
    }
}
```

**Explicação:**

- **`@Service`**: Anotação que marca a classe como um componente de serviço, gerenciado pelo Spring.

- **`@Autowired`**: Anotação que injeta automaticamente as dependências necessárias (repositório e cliente).

- **`List<Course> findAll()`**: Implementação do método da interface que busca e retorna todos os cursos.

- **`Course findById(Long id)`**: Implementação do método da interface que busca e retorna um curso pelo ID.

- **`void save(Course course)`**: Implementação do método da interface que salva um novo curso.

- **`StudentByCourseResponse findStudentsByIdCourse(Long idCourse)`**: Implementação do método da interface que retorna informações sobre os estudantes associados a um determinado curso, usando um cliente Feign (`studentClient`).

Essa implementação fornece uma lógica de serviço para as operações relacionadas a cursos e interage com o repositório e outros microserviços conforme necessário.

#### 2.4.4 DTO (Data Transfer Object)

Um DTO (Data Transfer Object) representa dados relacionados a estudantes no contexto do Microserviço Course. Vamos entender o que é um DTO e qual é o propósito desse script:

- **Definição**: Um DTO é um padrão de design utilizado para transferir dados entre subsistemas de um software. Ele é especialmente útil em ambientes distribuídos, como arquiteturas de microserviços, onde diferentes partes do sistema podem precisar trocar informações.

- **Propósito**: O principal objetivo de um DTO é encapsular dados e transferi-los entre camadas ou microsserviços sem expor a estrutura interna do objeto de dados. Isso ajuda a evitar a dependência excessiva entre as camadas e permite uma comunicação mais flexível e desacoplada.

##### 2.4.4.1  DTO `StudentDTO.java`

```java
package com.microservice.course.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private String name;

    private String lastname;

    private String email;

    private Long courseId;
}
```

**Explicação:**

- **`@Data`**: Anotação do projeto Lombok que automaticamente gera métodos como `toString()`, `equals()`, `hashCode()`, entre outros, com base nos campos da classe.

- **`@Builder`**: Anotação do Lombok que gera um padrão Builder para a classe. O padrão Builder facilita a criação de instâncias da classe com uma sintaxe mais limpa.

- **`@AllArgsConstructor` e `@NoArgsConstructor`**: Anotações do Lombok que geram construtores que incluem todos os campos (`@AllArgsConstructor`) e um construtor vazio (`@NoArgsConstructor`).

- **Campos da Classe**:
  - `private String name`: Representa o nome do estudante.
  - `private String lastname`: Representa o sobrenome do estudante.
  - `private String email`: Representa o endereço de e-mail do estudante.
  - `private Long courseId`: Representa o ID do curso ao qual o estudante está associado.

### Uso do DTO:

O DTO `StudentDTO` é utilizado para transferir informações sobre estudantes entre diferentes partes do sistema, como na resposta de um serviço que busca informações sobre estudantes associados a um curso (`findStudentsByIdCourse`). Ele encapsula os dados relevantes do estudante sem expor a estrutura interna da entidade de estudante. Isso é útil para garantir uma comunicação mais eficiente e desacoplada entre os microsserviços envolvidos.


#### 2.4.5 Feign

O script define uma interface chamada `StudentClient` que utiliza a biblioteca Feign. Vamos entender o que é o Feign e qual é o propósito desse script:

- **Definição**: Feign é uma biblioteca declarativa de clientes HTTP para Java. Ela simplifica o desenvolvimento de clientes para serviços web, especialmente em arquiteturas de microsserviços.

- **Propósito**: O Feign permite que você defina clientes HTTP de maneira declarativa, usando anotações Java, em vez de escrever manualmente o código para fazer chamadas HTTP. Ele facilita a integração entre microsserviços, permitindo que um microsserviço faça chamadas a outro de maneira fácil e eficiente.

##### 2.4.5.1 Client `StudentClient.java`

```java
package com.microservice.course.client;

import com.microservice.course.dto.StudentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// A anotação abaixo está comentada porque o microserviço Gateway utiliza a porta 8080, logo para testar o Feign é essencial que esteja com a porta correta.
//@FeignClient(name = "msvc-student", url="localhost:8090/api/student")
@FeignClient(name = "msvc-student", url="localhost:8080/api/student")
public interface StudentClient {

    @GetMapping("/search-ny-course/{idCourse}")
    List<StudentDTO> findAllStudentByCourse(@PathVariable Long idCourse);
}
```

**Explicação:**

- **`@FeignClient`**: Anotação do Spring Cloud Feign que marca a interface como um cliente Feign. Ela possui dois atributos principais:
  - **`name`**: O nome do cliente Feign, que é usado para identificá-lo no contexto do Spring.
  - **`url`**: A URL base para todas as chamadas feitas por esse cliente.

- **`@GetMapping("/search-ny-course/{idCourse}")`**: Anotação que mapeia o método para uma operação HTTP GET, especificando o endpoint relativo para buscar estudantes por curso. O `{idCourse}` é uma variável de caminho que será substituída pelo valor fornecido no momento da chamada.

- **`List<StudentDTO> findAllStudentByCourse(@PathVariable Long idCourse)`**: Método que representa a operação a ser executada quando o cliente Feign faz uma chamada para o serviço associado. Ele retorna uma lista de objetos `StudentDTO` e recebe o `idCourse` como um parâmetro de caminho.

**Uso do Cliente Feign:**

Essa interface `StudentClient` é injetada em outras classes ou componentes do Microserviço Course, e o Feign cuida automaticamente da criação e execução de requisições HTTP para o serviço associado (no caso, o Microserviço Student). Isso simplifica significativamente a comunicação entre microsserviços, eliminando a necessidade de escrever manualmente o código de chamadas HTTP.

#### 2.4.6 HTTP

O script define uma classe chamada `StudentByCourseResponse`, que é um objeto de resposta (response) utilizado no contexto do Microserviço Course. 

#### 2.4.6.1 http `StudentByCourseResponse`:

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentByCourseResponse {

    private String courseName;
    private String teacher;

    List<StudentDTO> studentDTOList;

}
```

**Explicação:**

- **`@Data`**: Anotação do projeto Lombok que automaticamente gera métodos como `toString()`, `equals()`, `hashCode()`, entre outros, com base nos campos da classe.

- **`@AllArgsConstructor` e `@NoArgsConstructor`**: Anotações do Lombok que geram construtores que incluem todos os campos (`@AllArgsConstructor`) e um construtor vazio (`@NoArgsConstructor`).

- **`@Builder`**: Anotação do Lombok que gera um padrão Builder para a classe. O padrão Builder facilita a criação de instâncias da classe com uma sintaxe mais limpa.

- **Campos da Classe**:
  - `private String courseName`: Representa o nome do curso associado à resposta.
  - `private String teacher`: Representa o nome do professor do curso.
  - `List<StudentDTO> studentDTOList`: Representa uma lista de objetos `StudentDTO`, que provavelmente contém informações sobre os estudantes associados ao curso.

**Propósito do `StudentByCourseResponse`:**

Essa classe é projetada para ser utilizada como resposta (response) de uma operação no Microserviço Course. Ela encapsula as informações relacionadas a um curso e aos estudantes associados a esse curso. Essa abordagem é comum em microsserviços, onde os objetos de resposta são modelados de acordo com as necessidades específicas da operação para evitar a exposição desnecessária de detalhes internos e proporcionar uma comunicação mais eficiente entre os microsserviços.

No contexto do método `findStudentsByIdCourse` na interface `ICourseService` e sua implementação em `CourseServiceImpl`, essa classe `StudentByCourseResponse` seria retornada como resultado da operação. Ela fornece um formato estruturado para os dados relacionados ao curso e aos estudantes, facilitando o consumo dessa informação por outras partes do sistema.

#### 2.4.7 Classe Principal `MicroserviceCourseApplication`

```java
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
```

O script está relacionado à classe principal de um microserviço Spring Boot chamado . A anotação `@EnableFeignClients` e `@EnableDiscoveryClient` são anotações específicas do ecossistema Spring Cloud.

1. **`@EnableFeignClients`**: Essa anotação é usada para habilitar o suporte a clientes Feign no aplicativo Spring Boot. O Feign é uma biblioteca declarativa de clientes HTTP para aplicativos Java, que facilita a escrita de clientes HTTP.

2. **`@EnableDiscoveryClient`**: Essa anotação é usada para sinalizar que o aplicativo é um cliente do serviço de descoberta (como Eureka). Isso permite que o aplicativo se registre no servidor de descoberta e descubra outros serviços registrados.

3. **`@SpringBootApplication`**: Esta é uma anotação composta que inclui várias outras anotações, como `@EnableAutoConfiguration`, `@ComponentScan`, e `@Configuration`. Em resumo, ela é usada para marcar a classe como uma classe de configuração, uma classe de componente e habilitar a configuração automática do Spring Boot.

No método `main`, a linha `SpringApplication.run(MicroserviceCourseApplication.class, args);` inicia o aplicativo Spring Boot. O método `run` inicializa toda a aplicação, configura o contexto do aplicativo e inicia o contêiner incorporado.

Essas anotações, em conjunto, desempenham papéis essenciais para um microserviço em um ambiente distribuído:

- `@EnableFeignClients`: Habilita a funcionalidade de cliente Feign, que é útil para fazer chamadas de serviço declarativas a outros serviços dentro da arquitetura de microsserviços.

- `@EnableDiscoveryClient`: Habilita o registro e a descoberta de serviços, permitindo que o microserviço se registre no servidor de descoberta (por exemplo, Eureka) e descubra outros serviços registrados.

Ambas são parte integrante do ecossistema Spring Cloud, que fornece ferramentas para desenvolvimento de microserviços em ambientes distribuídos. Elas garantem que o microserviço esteja pronto para interagir com outros serviços, fazer chamadas declarativas usando Feign e ser descoberto no ambiente distribuído.

#### 2.4.8 `application.yml`

O script fornecido contém informações relacionadas à configuração e dependências do microserviço chamado "msvc-course" no ecossistema Spring Boot.

```yaml
spring:
  application:
    name: msvc-course
  config:
    import: optional:configserver:http://localhost:8888
```

- `spring.application.name`: Define o nome da aplicação Spring, que, neste caso, é "msvc-course". Isso é usado para identificar exclusivamente a aplicação no ecossistema Spring.

- `spring.config.import`: Define a importação de configurações externas do Config Server. Neste caso, está configurado para importar configurações do servidor de configuração local hospedado em "http://localhost:8888". O Config Server é uma parte do ecossistema Spring Cloud que gerencia configurações externas para os serviços.

#### 2.4.9 `pom.xml`:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <!-- ... -->
  <groupId>com.microservice.course</groupId>
  <artifactId>microservice-course</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <name>microservice-course</name>
  <description>Spring Boot 3 Microservice Course</description>
  <!-- ... -->
  <dependencies>
    <!-- Dependências para o microserviço -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-config</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-openfeign</artifactId>
    </dependency>
    <dependency>
      <groupId>org.postgresql</groupId>
      <artifactId>postgresql</artifactId>
      <scope>runtime</scope>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
    </dependency>
  </dependencies>
  <!-- ... -->
</project>
```

- `<groupId>`, `<artifactId>`, `<version>`, `<name>`, `<description>`: Informações de identificação e descrição do projeto Maven.

- `<dependencies>`: Lista as dependências do projeto. Algumas dependências notáveis incluem:
  - `spring-boot-starter-actuator`, `spring-boot-starter-data-jpa`, `spring-boot-starter-web`: Dependências básicas do Spring Boot para atuadores, JPA e desenvolvimento web.
  - `spring-cloud-starter-config`: Dependência para integração com o Spring Cloud Config Server.
  - `spring-cloud-starter-netflix-eureka-client`: Dependência para registro e descoberta de serviços usando o Eureka.
  - `spring-cloud-starter-openfeign`: Dependência para integração com o OpenFeign, um cliente declarativo para serviços web.
  - `postgresql`: Driver JDBC para PostgreSQL, necessário para interagir com um banco de dados PostgreSQL.

- `<build>`: Configurações relacionadas à construção do projeto, como plugins e outras configurações do Maven.

### 2.5 Microservice API Gateway

#### 2.5.1 Descrição

O API Gateway atua como um ponto de entrada unificado para os microserviços, gerenciando as solicitações e encaminhando-as para os serviços apropriados. Pode incluir recursos como roteamento, segurança e monitoramento.
Portanto, o microserviço API Gateway desempenha um papel crucial em uma arquitetura de microsserviços. Ele atua como uma porta de entrada para os clientes e gerencia as solicitações, roteando-as para os serviços apropriados, agregando respostas e fornecendo recursos como autenticação, autorização e monitoramento. 

#### 2.5.2 Detalhes Técnicos

- **Tecnologia:** Spring Cloud Gateway
- **Pacote Principal:** `com.microservice.gateway`


#### 2.5.3 `application.yml`

```yaml
spring:
  application:
    name: msvc-gateway
  config:
    import: optional:configserver:http://localhost:8888
```

Neste arquivo de configuração, algumas configurações específicas são definidas:

- **`spring.application.name`**: Define o nome da aplicação como "msvc-gateway".
- **`spring.config.import`**: Importa as configurações do servidor de configuração. Aqui, ele está configurado para importar as configurações do servidor de configuração no endereço `http://localhost:8888`.

#### 2.5.4 `pom.xml`

#### 2.5.4.1 `spring-boot-starter-actuator`

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

Esta dependência adiciona o Starter Actuator, fornecendo funcionalidades para monitoramento e gestão da aplicação. O Starter Actuator expõe endpoints REST para informações operacionais, métricas, auditoria, entre outros.

#### 2.5.4.2 `spring-cloud-starter-config`

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

Essa dependência permite que o microserviço seja configurado pelo Spring Cloud Config Server. Ele busca as configurações da aplicação no servidor de configuração central.

#### 2.5.4.3 `spring-cloud-starter-gateway`

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
```

Esta dependência adiciona o Starter do Spring Cloud Gateway, que é uma biblioteca para criar gateways em um ambiente de microsserviços. Ele oferece recursos poderosos de roteamento, filtragem e gerenciamento de solicitações.

#### 2.5.4.4 `spring-cloud-starter-netflix-eureka-client`

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

Essa dependência integra o microserviço ao serviço de descoberta Eureka, permitindo que o Gateway descubra e se comunique com outros serviços registrados no Eureka.

#### 2.5.4.5 `spring-boot-starter-test`

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

---
# Autor
## Feito por **`Daniel Penelva de Andrade`**
