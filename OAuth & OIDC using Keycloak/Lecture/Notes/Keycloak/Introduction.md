# Introduction to Keycloak

- It is an open source identity and access management software platform

- A **realm** in keycloak is an isolated space for a set of users, credentials,
roles and applications

- You can create a *separate realm* for development, testing and production

- A realm manages users, roles, and clients where:

  - clients define application-specific settings, and

  - roles determine permissions for users

- Realm roles are across the entire realm whereas client roles are specific to
a particular application only

## Normal Flow of the Application

- If the user logs in directly, your own services authenticate and authorize
the user checking against the database

- If the user's already logged in, authentication is skipped

- If you are using JWTs, each request comes in with a JWT token that was
created on login

- Once the token expires, the client is logged out

- If you are keeping sessions, then spring checks the session id to verify

This flow changes when you use a third-party authentication service and the
actual flow depends on whether the server serves web pages or the client is a
separate entity
