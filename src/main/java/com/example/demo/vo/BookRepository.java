package com.example.demo.vo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, BookID>{


	ResponseEntity<Book> save(BookID bookId, Book book);

	BookID findById(String bookKey);

 
}
