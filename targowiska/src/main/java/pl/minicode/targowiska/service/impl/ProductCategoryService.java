package pl.minicode.targowiska.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pl.minicode.targowiska.domain.ProductCategory;
import pl.minicode.targowiska.repository.ProductCategoryRepository;
import pl.minicode.targowiska.service.IProductCategoryService;
import pl.minicode.targowiska.type.Status;

@Service
public class ProductCategoryService implements IProductCategoryService {
	
	@Autowired
	ProductCategoryRepository productCategoryRepository;

	@Override
	public Page<ProductCategory> findAll(Pageable pageable) {
		return productCategoryRepository.findAll(pageable);
	}

	@Override
	public List<ProductCategory> findAll() {
		return (List<ProductCategory>) productCategoryRepository.findAll();
	}

	@Override
	public ProductCategory findById(Long id) throws EntityNotFoundException {
		return productCategoryRepository.findById(id).orElseThrow(()-> throwEntityNotFoundException(id));
	}

	@Override
	public ProductCategory save(ProductCategory entity) {
		return productCategoryRepository.save(entity);
	}

	@Override
	public ProductCategory update(ProductCategory entity) {
		return productCategoryRepository.save(entity);
	}

	@Override
	public ProductCategory delete(ProductCategory entity) {
		entity.setStatus(Status.DELETED);
		return save(entity);
	}

	@Override
	public EntityNotFoundException throwEntityNotFoundException(Long id) {
		return new EntityNotFoundException("PRODUCT CATEGORY: Not found entity with given id:" + id);
	}

	@Override
	public Long countRows() {
		return productCategoryRepository.count();
	}

}
