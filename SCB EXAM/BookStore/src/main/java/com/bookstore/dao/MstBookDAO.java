package com.bookstore.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.model.dao.MstBook;

public interface MstBookDAO extends CrudRepository<MstBook, Integer> {
	 List<MstBook> findAllByOrderByRecommendedDesc();
}
