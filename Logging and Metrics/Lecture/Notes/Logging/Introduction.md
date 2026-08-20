# Introduction to Logging

- By default spring generates logs in the following format:

```regex
<timestamp> <level> <thread id> <process id> <logger name> <log message>
```

## The Five Levels of Logs

1. `ERROR` unhandled exception and critical system failures

2. `WARN` deprecated APIs, and potential failures

3. `INFO` application lifecycle events

4. `DEBUG` trace the flow of code to find errors

5. `TRACE` extremely detailed, step-by-step record of events

- By default, spring boot only generates logs of level 3 and above

- To change the minimum log level, configure your `application.yml` as follows:

```yml
logging:
  level:
    root: WARN
    com:
      example:
        app:
          connection: DEBUG
```

- To color code logs in the console, add the following configurations:

```yml
spring:
  output:
    ansi:
      enabled: always
```
