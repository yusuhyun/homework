package com.example.demo.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;

@Entity 
public class Book implements Serializable {
	
	@EmbeddedId
	private BookID bookID;
	
	@Column(name="writer", nullable =false)
	private String writer;
	
	@Column(name="category", nullable =false)
	private String category;

	public Book(BookID bookID, String writer, String category) {
		super();
		this.bookID = bookID;
		this.writer = writer;
		this.category = category;
	}

	public BookID getBookID() {
		return bookID;
	}

	public void setBookID(BookID bookID) {
		this.bookID = bookID;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Book [bookID=" + bookID + ", writer=" + writer + ", category=" + category + "]";
	}
	/*
	 * @Builder public Book(String writer, String category) { this.writer = writer;
	 * this.category = category; }
	 */
	
	
}
