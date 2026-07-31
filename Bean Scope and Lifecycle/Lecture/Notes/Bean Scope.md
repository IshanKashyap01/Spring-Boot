# Bean Scope

- It determines **how many instances of a bean are created and reused in a**
**Spring Container**

- There are *six* types of bean scopes in Spring boot but we'll only learn two
for now

- We specify a bean's scope through the `scope` attribute of `bean` tag

## Singleton

- It is the **default** scope, where only **one instance of each bean** is kept
around

- Naturally it is faster and consumes less memory

## Prototype

- It creates **a new instance every time** a bean is requested

```xml
<bean id="exampleBean" class="com.example.Example" scope="prototype"></bean>
```

- Assume you have a social media app. Users will potentially make lots of posts

- Singleton won't allow you to keep more than one post object around

- This is when prototype comes into play
