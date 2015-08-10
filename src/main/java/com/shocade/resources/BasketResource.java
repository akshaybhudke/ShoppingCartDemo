package com.shocade.resources;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shocade.beans.BasketBean;
import com.shocade.beans.ProductBean;
import com.shocade.dtos.BasketTo;
import com.shocade.dtos.ProductTo;
import com.shocade.services.BasketService;


@Component
@Path("basket")
public class BasketResource {

	@Autowired
	private BasketService basketService;
	
	BasketTo basketTo=new BasketTo();
	
	@POST
	@Path("create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createBasket(ProductTo productTo) {
		basketTo= basketService.createBasket(productTo);
		if (basketTo != null)
			return Response.ok(basketTo).build();
		else
			return Response.ok().status(Response.Status.NOT_FOUND).build();

	}
	
	
	@Path("add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProduct(BasketTo basketTo){
		
		
		
	        basketTo= basketService.addProduct(basketTo);
		
		return Response.ok(
				"Product " + basketTo + " Added to basket").build();
		
		
		}
	
	@GET
	@Path("list")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response getBasket(@QueryParam("id") String id ) {
		
		List<BasketBean> products = basketService.getAllProduct(id);
	/*	Iterator iterator=products.iterator();
		List arr=new ArrayList();
		while(iterator.hasNext()){
			String s= iterator.next().toString();
			arr.add(s);
		
		}*/
		return Response.ok(products.isEmpty()+"   "+  products.size()).build();

	}
	
	
	@DELETE
	@Path("delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProduct(BasketTo basketTo, ProductTo productTo) {
		boolean list = basketService.deleteProduct(basketTo,productTo);
		System.out.println(list);
		if (!(list == false))
			return Response.ok("team " + productTo + " Deleted from basket").build();
		else
			return Response.ok(
					"team " + productTo
							+ " not Deleted because it is not available")
					.build();

	}



	
	
}
