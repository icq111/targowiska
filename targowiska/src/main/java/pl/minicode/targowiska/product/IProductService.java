package pl.minicode.targowiska.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.minicode.targowiska.common.IPageableCommonService;

public interface IProductService extends IPageableCommonService<Product> {
	
	Page<Product> findAllByStatusActiveInactive(Pageable pageable);
	
	Long countRows();
}
