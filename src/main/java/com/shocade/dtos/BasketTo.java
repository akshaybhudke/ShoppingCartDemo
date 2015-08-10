package com.shocade.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shocade.beans.ProductBean;

public class BasketTo {

@JsonProperty
	private String id;
	private List<ProductBean> basket;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<ProductBean> getBasket() {
		return basket;
	}
	public void setBasket(List<ProductBean> basket) {
		this.basket = basket;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((basket == null) ? 0 : basket.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BasketTo other = (BasketTo) obj;
		if (basket == null) {
			if (other.basket != null)
				return false;
		} else if (!basket.equals(other.basket))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
