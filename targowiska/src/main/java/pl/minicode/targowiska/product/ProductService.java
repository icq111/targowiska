package pl.minicode.targowiska.product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pl.minicode.targowiska.common.Status;

@Service
public class ProductService implements IProductService {
	
	private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> findAll() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public Product save(Product entity) {
		return productRepository.save(entity);
	}

	@Override
	public Product update(Product entity) {
		return save(entity);
	}

	@Override
	public Product delete(Product entity) {
		entity.setStatus(Status.DELETED);
		return save(entity);
	}

	@Override
	public Product findById(Long id) throws EntityNotFoundException {
		return  productRepository.findById(id).orElseThrow(()-> throwEntityNotFoundException(id));
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	public EntityNotFoundException throwEntityNotFoundException(Long id) {
		return new EntityNotFoundException("PRODUCT: Not found entity with given id:" + id);
	}

	@Override
	public void updateProductPrices(ProductsDto productsDto) {
		List<Product> formUpdatedProducts = productsDto.getProducts();
		List<Product> databaseProducts = findAll();

		Map<Long, Product> idProductMap = new HashMap<>();
		for (Product formProduct : formUpdatedProducts) {
			idProductMap.put(formProduct.getId(), formProduct);
		}
		
		for (Product dbProduct : databaseProducts) {
			if (idProductMap.containsKey(dbProduct.getId())) {
				Product formUpdateProduct = idProductMap.get(dbProduct.getId());
				//updateSingleProductPrice(formUpdateProduct, dbProduct);
			}
		}

	}



	@Override
	public List<Product> find3RandomProductPrices() {
		return productRepository.find3RandomProductPrices();
	}
	
	public Page<Product> findAllByStatusActiveInactive(Pageable pageable){
		return productRepository.findAllByStatusActiveInactive(pageable);
	}

	@Override
	public Long countRows() {
		return productRepository.count();
	}

}
