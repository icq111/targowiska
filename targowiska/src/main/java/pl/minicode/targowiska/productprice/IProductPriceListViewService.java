package pl.minicode.targowiska.productprice;

import java.util.List;

import pl.minicode.targowiska.common.IPageableCommonService;

public interface IProductPriceListViewService extends IPageableCommonService<ProductPriceListView> {
	List<ProductPriceListView> findLastPriceList();
}
