package pl.minicode.targowiska.productprice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceListViewRepository extends JpaRepository<ProductPriceListView, Long> {

	@Query(nativeQuery= true, value ="select * from product_price_list_view n where n.price_list_id = (select max(m.price_list_id) from product_price_list_view m) ")
	public List<ProductPriceListView> findLastPriceList();
}
