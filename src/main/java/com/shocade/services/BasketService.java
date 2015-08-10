package com.shocade.services;

import java.util.List;

import com.shocade.beans.BasketBean;
import com.shocade.beans.ProductBean;
import com.shocade.dtos.BasketTo;
import com.shocade.dtos.ProductTo;

public interface BasketService {

	BasketTo createBasket(ProductTo productTo);

	BasketTo addProduct(BasketTo basketTo);

	List<BasketBean> getAllProduct(String id);

	boolean deleteProduct(BasketTo basketTo, ProductTo productTo);

}
