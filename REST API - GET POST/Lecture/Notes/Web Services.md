# Web Services

- A web service is a piece of software *on the server* that acts as a
*standardized* communication layer between the server and the client

- Unlike regular websites, which are made for humans to read, web services are
built for *machine-to-machine communication*

- There are mainly two *standards* that are followed nowadays viz.
[SOAP](SOAP.md) and [REST](REST.md)

## REST vs SOAP

1. Network Protocol and data format

    - SOAP provides flexibility in terms of the *network protocol* while fixing
    the messaging format to strictly `XML`

    - Whereas, REST fixes the protocol to `HTTP` but supports various types of
    data formats

2. Speed vs reliability

    - SOAP's `XML` request/responses are very verbose and can very large fairly
    quickly however, it can *guarantee delivery*

    - REST *primarily* uses `JSON`, which is very fast and lightweight but can
    *fail silently* if the network drops

3. Classification of web services

    - Web services using SOAP are called *traditional web services* as it came
    first and became the standard

    - Whereas, web services following the REST architectural style are called
    *RESTful web services*
