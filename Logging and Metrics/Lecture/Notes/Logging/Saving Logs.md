# Saving Logs

- You can save your logs by configuring its location in your `application.yml`

- You can also change the format of your logs

```yml
logging:
  file:
    name: logger.log
  pattern:
    file: '%d{yyyy-mm-dd} [%level%] %c{1.} [%t] %m%n'
```

## Security Considerations

- Do not log sensitive information such as API keys, passwords, etc.

- Do not use log levels below info as that will blow up the file size

- Maintain strict access control to log files

- Regularly delete obsolete log records and retain important/relevant ones only

- Sanitize input before logging it in to prevent malicious code injection
