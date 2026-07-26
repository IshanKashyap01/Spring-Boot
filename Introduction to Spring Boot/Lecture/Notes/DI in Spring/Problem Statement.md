# Problem Statement

Suppose you need to buy a study table and want to find out information on
different kinds of tables.

We will to write a program where we'll let *Spring Boot's dependency injection*
mechanism decide which kind of table to create and print.

## Implementation

```java
public static void main(String[] args)
{
    // take input
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the type of table you need :");
    // create the table
    Table table = getTable(sc.next());
    // print its details
    System.out.println(table.getDetails());
}
```

`getTable()` uses Spring's dependency injection mechanism to create the correct
type of table
