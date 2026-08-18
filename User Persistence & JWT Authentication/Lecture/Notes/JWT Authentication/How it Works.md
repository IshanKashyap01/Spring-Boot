# How JWT Works

- Once the server authenticates the user after login, it creates a JWT token
and sends it to the client

```ltf
LOGIN

POST /login
   │
   ▼
AuthController
   │
   ▼
AuthService
   │
   ├── AuthenticationManager
   │       │
   │       └── check username/password
   │
   └── JwtManager
           │
           └── create signed JWT
   │
   ▼
JwtResponse
   │
   ▼
client receives JWT
```

- The client then attaches it with every subsequent request until it expires

- The server runs the algorithm every time it receives a JWT token

- If the signature comes out as is, the token is valid and the server can
trust the payload data to be accurate

```ltf
GET /something
Authorization: Bearer <JWT>
           │
           ▼
   JwtAuthenticationFilter
           │
           ├── extract JWT
           ├── parse/verify signature
           ├── get username
           ├── check expiration
           ├── load UserDetails
           └── put Authentication
               into SecurityContext
                       │
                       ▼
              remaining filters
                       │
                       ▼
                   Controller
```

- Thus saving the server from having to hit the database for user validation
for every request
