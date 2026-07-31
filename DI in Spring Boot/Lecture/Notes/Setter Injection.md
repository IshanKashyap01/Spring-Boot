# Setter Injection

- Constructor injection will fetch you an object with all its *required*
dependencies

- However, there can also be *optional dependencies* in an object that can be
set using a `setter` function

- **Setter injection** is used when you need Spring to give you an object with
said optional dependencies

```java
private Tyre tyres;

public FamilyCar() {}
    
public FamilyCar(Tyre tyre)
{
    this.tyres = tyre;
}

public void setTyres(Tyre tyres) 
{
    this.tyres = tyres;
}
```

- In the below example, you get a family car with all weather tyres by default

- But this configuration also allows you to choose sports tyres if you want

```xml
<bean id="family" class="com.example.car_dealership.car.FamilyCar">
    <constructor-arg ref="allWeather"></constructor-arg>
</bean>
<bean id="familySportsTyre" class="com.example.car_dealership.car.FamilyCar">
    <property name="tyres" ref="sportsTyre"></property>
</bean>
```

- Using `property`, we can use the setter function of the instance variable
specified by the `name` attribute
