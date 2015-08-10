package com.shocade.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shocade.beans.ProductBean;

public interface ProductRepository extends MongoRepository<ProductBean, String> {

	public List<ProductBean> deleteByName(String name);

	public ProductBean findByName(String name) ;
	

	
	
}
