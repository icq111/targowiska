package pl.minicode.targowiska.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import pl.minicode.targowiska.domain.ProductPriceHistory;

public interface ProductPriceHistoryRepository extends PagingAndSortingRepository<ProductPriceHistory, Long> {

}
