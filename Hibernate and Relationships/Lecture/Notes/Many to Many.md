# Many to Many Relationships

```java
@Entity
@Table(name = "order")
public class Order
{
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable
    (
        name = "order_item", 
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items;
}
```

- You can add any cascade type *except* `Remove`

- Otherwise, deleting an order will delete all items associated with it in the
`item` table as well

```java
@Entity
@Table(name = "item")
public class Item
{
    @ManyToMany(mappedBy = "items")
    @JsonIgnore
    private List<Order> orders;
}
```

- `JsonIgnore` ensures the field will not be serialized

```java
@Service
public class OrderService
{
    @Transactional
    public void add(Order order)
    {
        // create a new order
        Order orderToSave = new Order();
        // copy all other attributes from the param to this order
        // fetch all items mentioned in the param order
        for(Item item : order.getItems())
        {
            // add them to the new order
            orderToSave.addItem(itemRepository.get(item.getId()));
        }
        // save the new order
        orderRepository.save(orderToSave);
    }
}
```

- The repository logic to save an `Order` or `Item` object will remain the same
