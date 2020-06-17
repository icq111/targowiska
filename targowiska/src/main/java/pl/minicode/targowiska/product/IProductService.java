package pl.minicode.targowiska.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.minicode.targowiska.common.IPageableCommonService;

public interface IProductService extends IPageableCommonService<Product> {

	void updateProductPrices(ProductsDto productsDto);
	
	List<Product> find3RandomProductPrices();
	
	Page<Product> findAllByStatusActiveInactive(Pageable pageable);
	
	Long countRows();
}
