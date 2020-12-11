package com.solvd.secondTeamProject.algorithm;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.solvd.homework7.models.CustomDateSerializer;
import com.solvd.secondTeamProject.jackson.CompanySerializer;
import com.solvd.secondTeamProject.model.Company;
import com.solvd.secondTeamProject.model.Product;
import com.solvd.secondTeamProject.model.Transport;

public class ResultRepresentation {
	@JsonProperty("transport_info")
	private Transport transport;
	@JsonProperty("products_shipped")
	private List<ProductShipping> productsShipped = new ArrayList<ProductShipping>();
	
	public String toString() {
		return "ResultRepresentation [transport="+transport+", productsShipped="+productsShipped;
	}
	
	public void addProduct(Company company, Product product) {
		ProductShipping ps = new ProductShipping();
		ps.setCompany(company);
		ps.setProduct(product);
		productsShipped.add(ps);
	}
	
	//GETTERS
	public List<ProductShipping> getProductsShipped() {return productsShipped;}
	public Transport getTransport() {return transport;}
	//SETTERS
	public void setProductsShipped(List<ProductShipping> productsShipped) {this.productsShipped = productsShipped;}
	public void setTransport(Transport transport) {this.transport = transport;}
	
	
	private class ProductShipping {
		@JsonProperty("company_info")
		@JsonSerialize(using = CompanySerializer.class)
		private Company company;
		@JsonProperty("product_info")
		private Product product;
		
		public String toString() {
			return "ProductShipping [company="+company+"product="+product;
		}
		
		//GETTERS
		public Company getCompany() {return company;}
		public Product getProduct() {return product;}
		//SETTERS
		public void setCompany(Company company) {this.company = company;}
		public void setProduct(Product product) {this.product = product;}
		
	}
}
