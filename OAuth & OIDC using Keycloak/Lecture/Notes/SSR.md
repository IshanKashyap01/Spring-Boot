# Application Flow with Keycloak and Server Side Rendering

- Suppose the client chooses the third-party (keycloak) authentication service

- Spring redirects the client to the keycloak login page

- User enters their details and keycloak verifies it on its end

- If the user details are valid, it redirects the client to the app's
*callback URL* attaching a one-time **authorization code**

- Spring intercepts this request, and extracts the code

- Then it makes a secure *server-to-server* network call directly to Keycloak
with the extracted code and its own secret password

- Keycode verifies the server's identity and returns a JWT access token

- Spring delegates to your code to decode this JWT and marks the user
authenticated if everything goes right

- Spring also delegates to your code to extract user roles

- Spring stores the roles in an `Authentication` object and places it inside
the `SecurityContextHolder`

- It generates a tracking code that's sent and stored as a browser session
cookie

- After authentication, Spring continues forward with your filter chain
