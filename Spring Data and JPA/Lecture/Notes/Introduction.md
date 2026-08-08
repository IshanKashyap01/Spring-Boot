# Introduction to Spring Data and JPA

## 1. JDBC

- JDBC provides a direct communication to the database

- Every step of the process needs to be done manually

- It provides minimum abstraction but maximum control

## 2. Hibernate

- Hibernate maps the relational model of database to Objects for Java

- It automatically generates SQL

- Tracks entity state and auto persist changes with *dirty checking*

- Manages associations between entities (one-to-one, one-to-many, etc.)

- Provides first-level cache through *Persistence Context*

- Cascades changes in an entity to its parents/children

## 3. JPA

- It is a standardized specification for ORMs in Java

- Defines APIs such as `EntityManager` and annotations like `@Entity`, `@Id`,
`@OneToOne`, etc.

- Provides *database-agnostic* queries through *JPQL*

- Hibernate implements this specification

## 4. Spring Boot

- Takes away manually connecting different layers of the app through *Spring*
*MVC* and *dependency injection*

- Abstracts transaction management (`@Transactional`)

## 5. Spring Data JPA

- Abstracts repository logic away with `JpaRepository`

- Can generate queries through repository method names alone

- Integrates JPA/Hibernate with the Spring application
