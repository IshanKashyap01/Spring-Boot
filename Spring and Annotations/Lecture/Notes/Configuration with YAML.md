# Configuration using YAML

- Annotations eliminate the need of a dedicated application context
configuration file (`XML` in our case)

- But there are still some application level configurations such as database
connection, server properties etc.

- Those can be dealt with using an `application.yml` file

```yml
server:
    port: 8087
```

- The above `YAML` code sets the port number for the tomcat server from its
default value of `8080`
