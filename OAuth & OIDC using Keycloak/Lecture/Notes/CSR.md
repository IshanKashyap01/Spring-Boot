# Application Flow with Keycloak and Client Side Rendering

- Here, the front-end handles the authentication from the third-party
authentication service (keycloak)

- The front-end attaches the keycloak access token to its request to the server

- The server fetches and caches Keycloak's public keys and verifies the token
using them

- If the token is valid, the server extracts user roles (and other information
if needed)

- If the user roles are appropriate for the request, the server returns the
expected response
