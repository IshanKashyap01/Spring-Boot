# Service Discovery and Registration

- It is a phone-book system for microservices that allows them to talk to each
other without hard-coding URLs

- Netflix's **Eureka Server** is a centralized registry server

- It keeps a dynamic list of all up-and-running microservices, their IP
addresses and port number

- When a microservice that's a **Eureka Client** starts, it automatically calls
the server

- When another microservice need it, it asks the *Eureka Server* which provides
the address for the call

- Clients periodically ping the Eureka Server so if one crashes, the server can
remove it from the client list

- Thus preventing other microservices from calling a downed service
