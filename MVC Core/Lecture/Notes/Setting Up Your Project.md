# Setting Up Your Project

- We will be using **JSP** (Java Server Pages) to dynamically create web pages

- But they are their own thing and neither a part of nor included in Spring
framework

- Therefore, to use JSP in your project, you'll need the following two
dependencies:

```xml
<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-jasper</artifactId>
</dependency>
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>jstl</artifactId>
    <version>1.2</version>
</dependency>
```

- You will also need to configure your application to tell it where the jsp
files live

```yml
# JSP files go in: src/main/webapp/WEB-INF/
spring:
  mvc:
      view:
          prefix: /WEB-INF/views/
          suffix: .jsp
```

- However, you'll have to reload your webpages and restart your web server
every time you change them

- To avoid that, you can use the dev tools provided by Spring Boot

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <version>3.0.1</version>
</dependency>
```
