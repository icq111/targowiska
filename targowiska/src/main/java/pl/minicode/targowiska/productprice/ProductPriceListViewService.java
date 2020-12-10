package pl.minicode.targowiska.productprice;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductPriceListViewService implements IProductPriceListViewService {

	@Autowired
	private ProductPriceListViewRepository productPriceListViewRepository;

	@Override
	public Page<ProductPriceListView> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductPriceListView> findAll() {
		return productPriceListViewRepository.findAll();
	}

	@Override
	public ProductPriceListView findById(Long id) throws EntityNotFoundException {
		// Not supported;
		throwEntityNotFoundException(id);
		return null;
	}

	@Override
	public ProductPriceListView save(ProductPriceListView entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductPriceListView update(ProductPriceListView entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductPriceListView delete(ProductPriceListView entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityNotFoundException throwEntityNotFoundException(Long id) {
		return new EntityNotFoundException("PRODUCT PRICE LIST VIEW: Not found entity with given id:" + id);
	}

	@Override
	public List<ProductPriceListView> findLastPriceList() {
		return productPriceListViewRepository.findLastPriceList();
	}

}
