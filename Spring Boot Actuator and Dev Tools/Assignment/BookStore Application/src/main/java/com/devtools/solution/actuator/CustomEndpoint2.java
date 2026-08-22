package com.devtools.solution.actuator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;
import com.devtools.solution.entity.Book;
import com.devtools.solution.repo.BookDal;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Endpoint(id = "stock")
public class CustomEndpoint2
{
    private final BookDal repository;

    @ReadOperation
    public Map<String, Integer> getBookQuantities()
    {
        Map<String, Integer> quantities = new HashMap<>();
        List<Book> books = repository.findAll();
        for(Book book : books)
        {
            quantities.put(book.getTitle(), book.getQuantity());
        }
        return quantities;
    }
}