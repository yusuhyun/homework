package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.vo.Book;
import com.example.demo.vo.BookID;
import com.example.demo.vo.BookRepository;

@Service
public class BookService {
	
	 
	@Autowired
	private BookRepository bookRepository;

	//전체조회
	public List<Book> selectAll() {
		List<Book> bList = new ArrayList<>();
		bookRepository.findAll().forEach(e -> bList.add(e));
		return bList;
	}

	// 도서검색
	public Optional<Book> searchBook(String bookKey) {
		
		BookID bookId = bookRepository.findById(bookKey);
		Optional<Book> book = bookRepository.findById(bookId);
		return book;
	}

	// 도서등록
	public Book insertBook(BookID bookId, Book book) {
		bookRepository.save(bookId, book);
		return book;
	}


	
	
	
	
	
}
