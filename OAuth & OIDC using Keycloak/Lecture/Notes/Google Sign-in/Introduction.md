# Google Sign-in

- Unlike keycloak, Google Cloud Platform is only an authentication manager

- That is, *authorization* has to be done by the server itself

The workflow will be as follows:

1. Fetch users from google

2. Maintain user database

3. Using user details from google, fetch the corresponding roles from the
database

4. Authorize for the requested endpoint/route
