package pl.minicode.targowiska.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.minicode.targowiska.domain.Product;
import pl.minicode.targowiska.type.ProductType;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>{

	@Query("SELECT p FROM Product p WHERE p.productType = :type AND p.status = 'A' ")
	public List<Product> findByProductType(@Param("type") ProductType type);
	
	
	@Query(nativeQuery = true, value = "select * "
			+ "from product p "
			+ "where p.product_price is not null and p.status = 'ACTIVE' order by random() limit 3 ")
	public List<Product> find3RandomProductPrices();
}
