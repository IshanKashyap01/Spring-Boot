# SOAP (Simple Object Access Protocol)

- It is a highly structured messaging protocol specification

- It relies entirely on **XML** as its messaging format

- All data is wrapped in a **SOAP envelope** that contains:

    1. A **header** for security and routing, and

    2. A **body** for the data

- The web service must expose a **Web Service Description Language** (WSDL) XML
file

- This file explicitly defines *all functions, data types and communication*
*rules*

- The actual exchange can happen over any communication protocol such as `TCP`,
`HTTP`, `HTTPS`, `SMTP` (email) or `JMS` (Java Message Service)

- It provides built-in, enterprise-grade extensions such as:

    1. **WS-Security** for message-level encryption, and

    2. **WS-ReliableMessaging** for guaranteed delivery

- Thus making it the standard choice for rigid legacy systems, banking and high
security enterprise environments
