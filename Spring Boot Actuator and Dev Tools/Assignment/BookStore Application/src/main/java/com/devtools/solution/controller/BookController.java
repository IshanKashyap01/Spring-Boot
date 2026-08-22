package com.devtools.solution.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.devtools.solution.DTO.BookDto;
import com.devtools.solution.entity.Book;
import com.devtools.solution.service.BookService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController
{
    private final BookService service;

    @GetMapping("/all")
    public List<Book> getAllBooks()
    {
        return service.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Integer id)
    {
        return service.getBookById(id);
    }

    @PostMapping("/save")
    public void saveBook(@RequestBody BookDto dto)
    {
        service.saveBook(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Integer id)
    {
        service.deleteBook(id);
    }
}