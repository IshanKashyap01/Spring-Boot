# Setting Up Projects to Use MySQL and Hibernate

```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>9.7.0</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
    <version>4.1.0</version>
</dependency>
```

- Add the above two dependencies to connect to MySQL and use Hibernate

```yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/student
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
  jpa:
    properties.hibernate-dialect: org.hibernate.dialect.MySQLDialect
    hibernate.ddl-auto: update
```

- Add the above configuration to your `Application.yml` file
