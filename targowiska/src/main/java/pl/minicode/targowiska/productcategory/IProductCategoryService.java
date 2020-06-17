package pl.minicode.targowiska.productcategory;

import pl.minicode.targowiska.common.IPageableCommonService;

public interface IProductCategoryService extends IPageableCommonService<ProductCategory> {

	Long countRows();
}
