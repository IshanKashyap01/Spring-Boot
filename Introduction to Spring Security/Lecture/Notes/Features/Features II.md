# More Spring Security Features

## Session Management

- Tracks and secures a user's authenticated state across multiple HTTP requests
for a session

- It does so by creating *temporary digital session data*

- It controls how the server creates, protects and destroys this data

## Remember Me

- Keeps a user's authentication for multiple sessions

- It does so by keeping a *long-lived browser token* for days or weeks

- It automatically logs a user back in if the current session expires or the
browser closes

## Integration with Other Frameworks

Allow external frameworks to plug directly into your app's security lifecycle
