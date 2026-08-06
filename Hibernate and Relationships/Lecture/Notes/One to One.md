# One to One Relationships

```java
@Entity @Table(name = "item")
public class Item
{
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_details_id")
    @JsonManagedReference
    private ItemDetails itemDetails;
    // other variables and methods...
}
```

- If you delete an `Item` record in the database, the associated `ItemDetails`
record will also be deleted

- However, it will throw an error the other way around unless you make the
relationship two-way

- `JoinColumn` goes where the foreign key lives

```java
@Entity @Table(name = "item_details")
public class ItemDetails
{
    @OneToOne(mappedBy = "itemDetails", cascade = CascadeType.ALL)
    @JsonBackReference
    private Item item;
}
```

- Whereas `mappedBy` goes on the opposite side

- Cascade in `ItemDetails` allow deletion of its records to also trigger
deletion for associated `Item` records

- The `JsonManagedReferenced` and `JsonBackReference` annotations prevents
JSON serialization to go into an infinite recursion

- The first one tells the compiler to serialise the object here while the other
one tells to skip it
