package com.shocade.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shocade.beans.BasketBean;
import com.shocade.beans.ProductBean;
import com.shocade.dtos.ProductTo;

public interface BasketRepository extends MongoRepository<BasketBean , String>{

	List<BasketBean> findById(String id);

	 void delete(ProductBean productBean);

	

	



	
}
