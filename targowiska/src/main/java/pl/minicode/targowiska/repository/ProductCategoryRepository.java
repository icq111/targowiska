package pl.minicode.targowiska.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import pl.minicode.targowiska.domain.ProductCategory;

public interface ProductCategoryRepository extends PagingAndSortingRepository<ProductCategory, Long> {

}
