# Keycloak Setup

## Dependencies

```xml
<dependency>
    <groupId>org.keycloak</groupId>
    <artifactId>keycloak-spring-boot-starter</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-oauth2-resource-server</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-oauth2-jose</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-oauth2-client</artifactId>
</dependency>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.keycloak.bom</groupId>
            <artifactId>keycloak-adapter-bom</artifactId>
            <version>15.0.2</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

## Server Side Configuration

```yml
spring:
  security:
    oauth2:
      client:
        registration:
          keycloak:
            client-id: demo-application
            client-secret: ${KEYCLOAK_CLIENT_SECRET}
            scope: openid+profile+email
            authorization-grant-type: authorization_code
        provider:
          keycloak:
          issuer-uri: https://lemur-15.cloud-iam.com/auth/realms/ishan-kashyap
```

## Client Side Configuration

```yml
keycloak:
  realm: ishan-kashyap
  auth-server-url: https://lemur-15.cloud-iam.com/auth/
  resource: demo-application
  public-client: true
  bearer-only: true
```
