# Inversion of Control

- IOC is a design principle that suggests delegating control of something you
need to someone else

- It says, a piece of code should only focus on *what* it does and delegate the
*when*, *how* and/or *who* when they're not its responsibility

- For example:

    1. *who* should create my dependencies?

    2. *When* does my code run?

    3. *How* the mechanics around my code works?

    4. *What* is my code supposed to do?

- These are all separate concerns and can be answered by different components

- This promotes loose coupling, modularity and high cohesion

- The act of delegating creation of dependencies/objects, is known as
**Dependency Injection** (DI)
