package pl.minicode.targowiska.productprice;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceListViewRepository extends JpaRepository<ProductPriceListView, Long> {

	@Query(nativeQuery= true, value ="select * from product_price_list_view n where n.price_list_id = (select max(m.price_list_id) from product_price_list_view m) and n.product_status in (:statuses)  ")
	public List<ProductPriceListView> findLastPriceListWhereStatusIn(@Param("statuses") List<String> statuses);
	
	@Query(nativeQuery= true, value ="select * from product_price_list_view n where date_trunc('day', n.price_list_date) = :date ")
	public List<ProductPriceListView> getByDate(@Param("date") Date date);
	
	@Query(nativeQuery= true, value ="select * from product_price_list_view n where date_trunc('day', n.price_list_date) = :date and product_category = :productCategory ")
	public List<ProductPriceListView> getByDateAndProductCategory(@Param("date") Date date, @Param("productCategory")String productCategory);
}
