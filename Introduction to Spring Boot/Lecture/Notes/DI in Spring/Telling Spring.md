# Telling Spring What to Do

- We will tell Spring what to do through using a configuration file (we'll be
using XML in this case)

- Spring requires us to use its **XML Schema-based configuration** to do so
when using XML

- We will create the following file and name it `applicationContext.xml`

```xml
<?xml version="1.0" encoding "UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- When Spring creates an object for us, we call it a bean instead -->
    <bean id="shortTable" class="com.spring.demo.ShortTable"></bean>
    <bean id="longTable" class="com.spring.demo.LongTable"></bean>
</beans>
```

- In this file, we specify all the different kinds of `Table` objects that
exist

- Now, we can call Spring to create the objects for us
