package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.BookService;
import com.example.demo.vo.Book;
import com.example.demo.vo.BookID;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	
	
	// 전체조회
	@GetMapping("/library")
	public List<Book> selectAll() {
		List<Book> book = bookService.selectAll();
		return book;
	}
	
	// 도서검색
	@GetMapping("/library/{bookKey}")
	public Optional<Book> searchBook(@Param("bookKey")String bookKey) {
		Optional<Book> book = bookService.searchBook(bookKey);
		System.out.println("검색 입력받아온 도서키 : " + bookKey);
		return book;
	}
	
	// 도서추가
	@PostMapping("/library")
	public Book insertBook(@Param("bookName")String bookName, Book book) {
		String bookKey = UUID.randomUUID().toString();
		BookID bookId = new BookID(bookKey, bookName);
		book.setBookID(bookId);
		System.out.println("신규등록도서 : " + book);
		bookService.insertBook(bookId, book);
		return book;
	}
	
	// 도서수정
	@PutMapping("/library")
	public String updateBook(@Param("bookName")String bookName, 
			@Param("bookKey")String bookKey,Book book) {
		BookID bookId = new BookID(bookKey, bookName);
		book.setBookID(bookId);
		System.out.println("수정도서 정보 : " + book);
		return "테스트4";
	}
	
	// 도서삭제
	@DeleteMapping("/library/{bookKey}")
	public String deleteBook(@Param("bookKey")String bookKey) {
		System.out.println("삭제하려는 도서키 : " + bookKey);
		return "테스트5";
	}
	
}
