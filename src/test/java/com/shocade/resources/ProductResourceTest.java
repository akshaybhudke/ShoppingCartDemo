package com.shocade.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.jmock.Expectations;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.shocade.beans.ProductBean;
import com.shocade.config.RootConfig;
import com.shocade.dtos.ProductTo;
import com.shocade.services.ProductService;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.LowLevelAppDescriptor;

public class ProductResourceTest extends JerseyTest implements RootConfig {

	
	private LowLevelAppDescriptor descriptor;
	private ProductResource productResource;
	private ProductService mockProductService;
	private ProductTo productTo;

	public ProductResourceTest() {

	}
	
	@BeforeClass
	public void setUp() throws Exception {
		super.setUp();
	}

	@AfterClass
	public void tearDown() throws Exception {
		super.tearDown();
	}

	
	@BeforeTest
	public void setData() {
		productResource = new ProductResource();
		mockProductService = context.mock(ProductService.class);
		ReflectionTestUtils.setField(productResource, "productService",
				mockProductService);
		descriptor.getResourceConfig().getSingletons().add(productResource);
	}

	@Override
	protected AppDescriptor configure() {
		descriptor = new LowLevelAppDescriptor.Builder(
				new DefaultResourceConfig()).build();
		return descriptor;
	}
	
	@Test
	public void shouldFindProduct() {
		// GIVEN
		int expecteStatus = 404;
		final String name = "apple1";
		
		context.checking(new Expectations() {
			{
				oneOf(mockProductService).findProduct(with(name));
				will(returnValue(productTo));
			}
		});

		// WHEN
		WebResource resource = resource().path("product/find").queryParam("name",
				name);
		ClientResponse actualClientResponse = resource
				.get(ClientResponse.class);
		// TeamTo actual = actualClientResponse.getEntity(TeamTo.class);
		// System.out.println(actual);

		// THEN
		Assert.assertEquals(actualClientResponse.getStatus(), expecteStatus);
		// Assert.assertEquals(actual.getName(), name);
	}
	
	
	@Test
	public void shouldListTeam() {
		// GIVEN
		int expecteStatus = 200;
		final List<ProductBean> list = new ArrayList<ProductBean>();
		context.checking(new Expectations() {
			{
				oneOf(mockProductService).getAllProduct();
				will(returnValue(list));
			}
		});

		// WHEN
		WebResource resource = resource().path("product/list");
		ClientResponse actualClientResponse = resource
				.get(ClientResponse.class);

		// THEN
		Assert.assertEquals(actualClientResponse.getStatus(), expecteStatus);
	}

	
	@Test
	public void shouldAddTeam() {

		final ProductBean productBean = new ProductBean();
		final ProductTo productTo = new ProductTo();
		final ProductTo productToWithId=new ProductTo();
		productTo.setName("apple1");
		productToWithId.setName("apple1");
		productToWithId.setId("1111");
		productBean.setName(productTo.getName()); 
		// GIVEN
		int expecteStatus = 200;
		
		context.checking(new Expectations() {

			{
				oneOf(mockProductService).addProduct(productTo);
				will(returnValue(productToWithId));
			}
		});

		// WHEN
		WebResource service = resource().path("product/add");
		ClientResponse resp = service.type(MediaType.APPLICATION_JSON).post(
				ClientResponse.class, productTo);
		System.out.println("Got stuff: " + resp);
		//String text = resp.getEntity(String.class);
		ProductTo actual = resp.getEntity(ProductTo.class);
		
		
		// THEN
		Assert.assertEquals(expecteStatus, resp.getStatus());
Assert.assertNotNull(actual.getId());
	}
	
	
	@Test
	public void shouldDeleteTeam() {
		// GIVEN
		final String name = "apple1";
		final List<ProductBean> list = new ArrayList<ProductBean>();
		final ProductTo productTo = new ProductTo();
		productTo.setName(name);
		context.checking(new Expectations() {
			{
				oneOf(mockProductService).deleteProduct(with(productTo));
				returnValue(list);
			}
		}); // WHEN

		WebResource service = resource().path("product/delete");
		ClientResponse resp = service.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON)
				.delete(ClientResponse.class, productTo);
		System.out.println("Got stuff: " + resp); // THEN
		Assert.assertEquals(200, resp.getStatus());

	}

	
	
}
