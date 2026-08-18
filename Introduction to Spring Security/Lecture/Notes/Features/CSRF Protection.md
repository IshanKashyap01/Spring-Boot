# Cross-Site Request Forgery (CSRF) Protection

## CSRF Attack

- Suppose a user is logged into a trusted site/webapp

- A *CSRF attack* is initiated by a malicious third-party web app

- It tricks the browser into executing unwanted actions on the site on behalf
of the user

- It works by exploiting the browser's behavior of automatically attaching the
user's authentication cookies to every request

- It succeeds if/when the site fails to distinguish between a *legitimate* and
a *forged* request

## CSRF Protection

- It counters this attack by requiring a unique, unpredictable secret called a
*CSRF token* for the active session

- The frontend itself embeds this token into the request
