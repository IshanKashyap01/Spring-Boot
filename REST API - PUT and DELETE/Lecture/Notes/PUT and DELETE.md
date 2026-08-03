# DELETE Method

```java
@DeleteMapping("/{id}")
public void deleteHotel(@PathVariable int id)
{
    this.hotelService.delete(id);
}
@PutMapping("/{id}")
public void changeHotel(@PathVariable int id, @RequestBody Hotel updatedHotel)
{
    this.hotelService.update(id, updatedHotel);
}
```
