package com.devtools.solution.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devtools.solution.entity.Book;

public interface BookDal extends JpaRepository<Book, Integer>
{

}