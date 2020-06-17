package pl.minicode.targowiska.service;

import pl.minicode.targowiska.domain.ProductCategory;

public interface IProductCategoryService extends IPageableCommonService<ProductCategory> {

	Long countRows();
}
