# Steps to Use JWT

0. To use JWT, add the above three dependencies

1. Either make your `User` entity inherit from `UserDetails` or create a new
class for user details

2. Create a custom `UserDetailsService` that fetches your `UserDetails` from
the database

3. Create a *custom authentication filter* that authenticates JWTs from
incoming requests

4. Configure your `filter chain` to run your custom filter before itself and to
not keep sessions

5. Write a controller to listen for login attempts (ex. `/login` endpoint)

6. Make it delegate to a service that authenticates the user from the database,
creates and returns a new JWT token

This way, you'll create a new JWT token on login and authenticate the same
until it expires

The example code does not decide what happens when a token expires. It simply
denies requests do to failed authentication
