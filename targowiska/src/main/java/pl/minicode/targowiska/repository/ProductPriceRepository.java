package pl.minicode.targowiska.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import pl.minicode.targowiska.domain.ProductPrice;

public interface ProductPriceRepository extends PagingAndSortingRepository<ProductPrice, Long> {

}
