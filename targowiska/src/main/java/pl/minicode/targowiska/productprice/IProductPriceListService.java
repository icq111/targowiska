package pl.minicode.targowiska.productprice;

import pl.minicode.targowiska.common.IPageableCommonService;

public interface IProductPriceListService extends IPageableCommonService<ProductPriceList> {

	void savePriceList(ProductPriceListCreationDto creationDto);
}
