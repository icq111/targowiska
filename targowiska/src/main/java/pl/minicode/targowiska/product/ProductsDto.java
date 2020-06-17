package pl.minicode.targowiska.domain.dto;

import java.util.ArrayList;
import java.util.List;

import org.thymeleaf.util.ListUtils;

import pl.minicode.targowiska.domain.Product;

public class ProductsDto {

	private List<Product> products;

	public void addProduct(Product p) {
		if(ListUtils.isEmpty(products)) {
			this.products = new ArrayList<Product>();
		}
		this.products.add(p);
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "ProductsDto [products=" + products + "]";
	}
}
