# Constructor Injection

- Sometimes, Objects have parameterised constructors that initialise its
dependencies during object creation itself

- Spring allows us to inject these dependencies to the object through its
constructor as follows:

```xml
<bean id="allWeather" class="com.example.car_dealership.tyre.AllWeatherTyre"></bean>
<bean id="family" class="com.example.car_dealership.car.FamilyCar">
    <constructor-arg ref="allWeather"></constructor-arg>
</bean>
```

- Using `constructor-arg`, we can specify all parameters for an Object's
constructor

- `ref` takes the `id` of the `bean` to be inserted

- If the parameter takes a primitive value instead, you can provide it by using
the `value` attribute instead

- Arguments can correspond to either a specific index or be matched by type
