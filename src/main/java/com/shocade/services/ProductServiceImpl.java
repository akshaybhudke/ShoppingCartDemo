package com.shocade.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shocade.beans.ProductBean;
import com.shocade.dtos.ProductTo;
import com.shocade.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	public ProductTo addProduct(ProductTo productTo) {
		ProductBean productBean = new ProductBean();
		productBean.setName(productTo.getName());
		productBean.setDescription(productTo.getDescription());
		productBean.setPrice(productTo.getPrice());
		productBean.setId(productTo.getId());
		productBean = productRepository.save(productBean);
		productTo.setName(productBean.getName());
        productTo.setId(productBean.getId());
        productTo.setDescription(productBean.getDescription());
        productTo.setPrice(productTo.getPrice());
		
		return productTo;
	}

	public List<ProductBean> getAllProduct() {
		List<ProductBean> teams = productRepository.findAll();
		return teams;

	}

	public List<ProductBean> deleteProduct(ProductTo productTo) {
		List<ProductBean> list = productRepository.deleteByName(productTo.getName());
		System.out.println(list);
		if (!list.isEmpty())
			return list;
		else
			return null;

	}

	@GET
	@Path("find")
	@Produces(MediaType.APPLICATION_JSON)
	public ProductTo findProduct(@QueryParam("name") String name) {
		ProductBean productBean = productRepository.findByName(name);
		System.out.println(productBean);
		if (productBean != null) {
			ProductTo productTo = new ProductTo();
			productTo.setName(productBean.getName());
			productTo.setDescription(productBean.getDescription());
			productTo.setId(productBean.getId());
			productTo.setPrice(productBean.getPrice());
			return productTo;
		} else
			return null;

	}

	
	
}
