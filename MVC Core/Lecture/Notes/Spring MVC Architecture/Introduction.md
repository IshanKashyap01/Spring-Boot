# Spring MVC Architecture

Spring has the following three layers:

## 1. Presentation Layer

- Controllers handle incoming requests and returns appropriate responses

- Views (if applicable) are sent to the client to interact with and sends
requests to the server

## 2. Service Layer

- This is the brain of the application where all the business logic lives

- It processes data validation and ensures data integrity by enforcing
transactions

- Each service pulls together everything needed to complete a single user
action

## 3. Repository Layer

- Directly communicates with the database to perform CRUD operations

- It is also known as *Data Access layer* and *Persistence layer*
