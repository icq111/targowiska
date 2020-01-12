package pl.minicode.targowiska.service;

import pl.minicode.targowiska.domain.Product;
import pl.minicode.targowiska.domain.dto.ProductsDto;

public interface IProductService extends IPageableCommonService<Product> {

	public void updateProductPrices(ProductsDto productsDto);
}
