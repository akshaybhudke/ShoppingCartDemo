package com.shocade.resources;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.shocade.beans.ProductBean;
import com.shocade.dtos.ProductTo;
import com.shocade.services.ProductService;



@Component
@Path("product")
public class ProductResource {
	@Autowired
	private ProductService productService;

	private static Set<String> l1 = new HashSet<String>();
	ProductTo productTo = new ProductTo();

	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProduct(ProductTo productTo) {
       
		productTo = productService.addProduct(productTo);
		return Response.ok(
				productTo).build();
	}

	@GET
	@Path("list")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response getAllProduct() {
		List<ProductBean> products = productService.getAllProduct();
		return Response.ok(products).build();

	}

	@DELETE
	@Path("delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProduct(ProductTo productTo) {
		List<ProductBean> list = productService.deleteProduct(productTo);
		System.out.println(list);
		if (!(list == null))
			return Response.ok("team " + productTo.getName() + " Deleted").build();
		else
			return Response.ok(
					"team " + productTo.getName()
							+ " not Deleted because it is not available")
					.build();

	}

	@GET
	@Path("find")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findProduct(@QueryParam("name") String name) {
		ProductTo productTo = productService.findProduct(name);
		// System.out.println(teamTo);
		if (productTo != null)
			return Response.ok(productTo).build();
		else
			return Response.ok().status(Response.Status.NOT_FOUND).build();

	}

}
