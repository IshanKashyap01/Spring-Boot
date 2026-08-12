# Authentication Mechanisms

## HTTP Basic Authentication

- Simple built-in protocol that allows a browser to pass user credentials to
the server using a standard HTTP request header

- It works by encoding the `username:password` string to **Base64** and placing
it in the request

## Form-Based Authentication

- *Stateful* authentication mechanism where user enters their credentials into
a form

- The credentials are sent through a `POST` request and validated by the server

## JSON Web Token (JWT) Authentication

- It is a *Stateless* authentication mechanism

- The server verifies the user's identity by validating a *cryptographically*
*signed JSON string*

## Lightweight Directory Access Protocol

Used at organizational level to authenticate employees
