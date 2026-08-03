# REST (Representational State Transfer)

- It is an **architectural style** that leverages existing *HTTP* capabilities
and design rather than inventing a new messaging layer

- The web service treats data as *resources* identified by unique `URI`s
(Uniform Resource Identifier)

- It maps standard `CRUD` operations directly to *standard HTTP verbs*:

    1. `POST`

    2. `GET`

    3. `PUT`/`PATCH`

    4. `DELETE`

- Unlike SOAP, data can be transmitted in any format: `XML`, `JSON`, `HTML`,
binary data or blobs (multimedia and PDFs), and even plain text

- Every request must contain everything needed to process it; *no context is*
*shared* between requests in a client session
