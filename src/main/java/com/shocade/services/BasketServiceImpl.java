package com.shocade.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shocade.beans.BasketBean;
import com.shocade.beans.ProductBean;
import com.shocade.dtos.BasketTo;
import com.shocade.dtos.ProductTo;
import com.shocade.repositories.BasketRepository;
@Service
public class BasketServiceImpl implements BasketService{

	@Autowired
	BasketRepository basketRepository;
	BasketBean basketBean =new BasketBean();
	BasketTo basketTo =new BasketTo();
	 ProductBean productBean =new ProductBean();
	List<ProductBean> list=new ArrayList<ProductBean>();
	 
	@Override
	public BasketTo addProduct(BasketTo basketTo) {		
		// TODO Auto-generated method stub
		
		basketBean.setId(basketTo.getId());
		basketBean.setBasket(basketTo.getBasket());
		
		      basketBean=basketRepository.save(basketBean);
	      basketTo.setBasket(basketBean.getBasket());
	      basketTo.setId(basketBean.getId());
		return basketTo;
	}
/*
 * 
		ProductBean productBean = new ProductBean();
		productBean.setId(productTo.getId());
		productBean.setDescription(productTo.getDescription());
		productBean.setName(productTo.getName());
		productBean.setPrice(productTo.getPrice());
		List<ProductBean> list=new ArrayList<ProductBean>();
		list.add(productBean);

 * 
 */
	@Override
	public List<BasketBean> getAllProduct(String id) {
		// TODO Auto-generated method stub
		List<BasketBean> list=basketRepository.findById(id);
		return list;
	}

	@Override
	public boolean deleteProduct(BasketTo basketTo , ProductTo productTo) {
		// TODO Auto-generated method stub
		boolean status=true;
	//	BasketBean entity =new BasketBean();
		BasketBean basketBean=(BasketBean) basketRepository.findById(basketTo.getId());
		
		 basketRepository.delete(basketBean);
		
		return status;
	}

	
	@Override
	public BasketTo createBasket(ProductTo productTo) {
		// TODO Auto-generated method stub
		
		 productBean.setId(productTo.getId());
		 productBean.setName(productTo.getName());
		 productBean.setDescription(productTo.getDescription());
		 productBean.setPrice(productTo.getPrice());
		 list.add(productBean);
		 basketBean.setBasket(list);
		 
		 
		   basketBean=basketRepository.save(basketBean);
		 basketTo.setBasket(basketBean.getBasket());
		 basketTo.setId(basketBean.getId());
		
		return basketTo;
	}
	
	

}
