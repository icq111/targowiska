package pl.minicode.targowiska.productprice;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductPriceListRepository extends PagingAndSortingRepository<ProductPriceList, Long> {

	@Query(nativeQuery = true, value = "select coalesce(max(p.price_list_id),0) + 1 from product_price_list p ")
	public Long getNextPriceListId();
}
