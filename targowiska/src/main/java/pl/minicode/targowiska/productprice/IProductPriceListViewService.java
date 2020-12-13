package pl.minicode.targowiska.productprice;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import pl.minicode.targowiska.common.IPageableCommonService;

public interface IProductPriceListViewService extends IPageableCommonService<ProductPriceListView> {
	List<ProductPriceListView> findLastPriceListWhereStatusIn(List<String> statuses);
	List<ProductPriceListView> getByDate(Date date);
}
