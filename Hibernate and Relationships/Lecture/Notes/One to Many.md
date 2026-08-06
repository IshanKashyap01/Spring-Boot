# One to Many Relationships

```java
@Entity
@Table(name = "item")
public class Item
{
    @OneToMany(mappedBy = "item" cascade = CascadeType.ALL)
    private List<Review> reviews;
}
```

- A one-way relationship here will allow you to enter reviews as part of an
item

- However, you wouldn't be able to add a review and connect it to an item

```java
@Entity
@Table(name = "reviews")
public class Review
{
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item
}
```

- For that, you'll need a two-way relationship established with the above code

- You'll also need to add `mappedBy = "item"` in the `Item` class as well
