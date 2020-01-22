package pl.minicode.targowiska.service;

import java.util.List;

import pl.minicode.targowiska.domain.Product;
import pl.minicode.targowiska.domain.dto.ProductsDto;

public interface IProductService extends IPageableCommonService<Product> {

	void updateProductPrices(ProductsDto productsDto);
	
	List<Product> find3RandomProductPrices();
}
