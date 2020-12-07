package com.solvd.secondTeamProject.algorithm;

import java.util.ArrayList;
import java.util.List;
import com.solvd.secondTeamProject.model.Company;
import com.solvd.secondTeamProject.model.Product;
import com.solvd.secondTeamProject.model.Transport;

public class ResultRepresentation {
	private Transport transport;
	private List<ProductShipping> productsShipped = new ArrayList<ProductShipping>();
	
	public String toString() {
		return "ResultRepresentation [transport="+transport+", productsShipped="+productsShipped;
	}
	
	public void addProduct(Company company, Product product) {
		ProductShipping ps = new ProductShipping();
		ps.setC(company);
		ps.setP(product);
		productsShipped.add(ps);
	}
	
	//GETTERS
	public List<ProductShipping> getProductsShipped() {return productsShipped;}
	public Transport getTransport() {return transport;}
	//SETTERS
	public void setProductsShipped(List<ProductShipping> productsShipped) {this.productsShipped = productsShipped;}
	public void setTransport(Transport transport) {this.transport = transport;}
	
	
	private class ProductShipping{
		private Company company;
		private Product product;
		
		public String toString() {
			return "ProductShipping [company="+company+"product="+product;
		}
		
		//GETTERS
		public Company getC() {return company;}
		public Product getP() {return product;}
		//SETTERS
		public void setC(Company company) {this.company = company;}
		public void setP(Product product) {this.product = product;}
		
	}
}
