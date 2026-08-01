# Directory Structure for Spring MVC

```ltf
              MVC
        ┌──────┼──────┐
        M      V      C
        │      │      │
   ┌────┴────┐ │   controller/
 domain/     │ │
 service/    │ templates/
 repository/ │
```

- For simple apps, you'd only need a `controller/` and a `model/` alongside
`templates`

- However, as apps grow bigger, all that complexity gets concentrated into the
controllers

- And they end up playing multiple roles such as:

    1. Managing the control flow

    2. Handling database connection and read/writes

    3. Containing the business logic

- You might instead let the model represent the database layer and handle the
read/write logic while the controller only calls it

- That's when domain-service-repository comes to the rescue

```ltf
src/main/java/com/example/app/
├── controller/     ← MVC Controller
├── service/        ← application/control flow
├── repository/     ← persistence: DB/files/etc.
└── domain/         ← your business objects

src/main/resources/
└── templates/      ← MVC Views
```

- You refactor db/persistence specific logic to `repository/`

- Put control flow specific logic into `service/`

- Let your objects handle what business logic belongs to them

- And put your business objects in `domain/`

- This way, you effectively eliminate `model/` as it just gets scattered around
