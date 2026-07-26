# Spring Container

- It is the runtime object manager that creates, stores, configures and manages
the lifecycle of your app's beans

- `ApplicationContext`'s implementations all create a Spring container from
Java configurations

## How It Works

- It works by taking the factory pattern to the next level

- Based on bean definitions, it maps out all dependencies for the Bean

- It then handles the creation of not only the bean but all its dependencies
as well

- This saves us from creating endless factories for our objects

- In the container:

    1. A **factory** creates objects

    2. A **registry** keeps track of them

    3. A **dependency resolver** figures out what each object needs

    4. A **lifecycle manager** initialises and destroys them when appropriate
