package com.bookstore.model.dto;

import java.util.List;

import com.bookstore.model.dao.MstBook;

public class Books {

	private List<MstBook> books;

	public List<MstBook> getBooks() {
		return books;
	}

	public void setBooks(List<MstBook> books) {
		this.books = books;
	}


}
