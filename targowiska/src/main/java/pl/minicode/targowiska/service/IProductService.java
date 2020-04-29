package pl.minicode.targowiska.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.minicode.targowiska.domain.Product;
import pl.minicode.targowiska.domain.dto.ProductsDto;

public interface IProductService extends IPageableCommonService<Product> {

	void updateProductPrices(ProductsDto productsDto);
	
	List<Product> find3RandomProductPrices();
	
	Page<Product> findAllByStatusActiveInactive(Pageable pageable);
}
