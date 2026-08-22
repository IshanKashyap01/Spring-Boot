package com.devtools.solution.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.devtools.solution.DTO.BookDto;
import com.devtools.solution.entity.Book;
import com.devtools.solution.repo.BookDal;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService
{
    private final BookDal repository;

    public List<Book> getAllBooks()
    {
        return repository.findAll();
    }

    public Book getBookById(Integer id)
    {
        return repository.findById(id).get();
    }

    public void saveBook(BookDto dto)
    {
        repository.save(new Book(null, dto.getTitle(), dto.getAuthor(), dto.getPrice(), dto.getQuantity()));
    }

    public void deleteBook(Integer id)
    {
        Book book = getBookById(id);
        repository.delete(book);
    }
}