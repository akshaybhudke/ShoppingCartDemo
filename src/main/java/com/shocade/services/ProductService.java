package com.shocade.services;

import java.util.List;

import com.shocade.dtos.ProductTo;
import com.shocade.beans.ProductBean;

public interface ProductService {

	ProductTo addProduct(ProductTo productTo);

	List<ProductBean> getAllProduct();

	List<ProductBean> deleteProduct(ProductTo productTo);

	ProductTo findProduct(String name);

}
