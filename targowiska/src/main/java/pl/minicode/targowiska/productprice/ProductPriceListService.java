package pl.minicode.targowiska.productprice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pl.minicode.targowiska.common.Status;
import pl.minicode.targowiska.product.Product;
import pl.minicode.targowiska.product.ProductRepository;


@Service
public class ProductPriceListService implements IProductPriceListService {

	@Autowired
	private ProductPriceListRepository productPriceListRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<ProductPriceList> findAll() {
		return (List<ProductPriceList>) productPriceListRepository.findAll();
	}

	@Override
	public ProductPriceList save(ProductPriceList entity) {
		return productPriceListRepository.save(entity);
	}

	@Override
	public ProductPriceList update(ProductPriceList entity) {
		return save(entity);
	}

	@Override
	public ProductPriceList delete(ProductPriceList entity) {
		entity.setStatus(Status.DELETED);
		return save(entity);
	}

	@Override
	public ProductPriceList findById(Long id) throws EntityNotFoundException {
		return  productPriceListRepository.findById(id).orElseThrow(()-> throwEntityNotFoundException(id));
	}

	@Override
	public Page<ProductPriceList> findAll(Pageable pageable) {
		return productPriceListRepository.findAll(pageable);
	}

	@Override
	public EntityNotFoundException throwEntityNotFoundException(Long id) {
		return new EntityNotFoundException("ProductPriceList: Not found entity with given id:" + id);
	}

	@Override
	public void savePriceList(ProductPriceListCreationDto creationDto) {
		Long priceListId = productPriceListRepository.getNextPriceListId();
		List<ProductPriceListView> creationPriceList = creationDto.getCreationPriceList();
		List<ProductPriceList> priceListItemsToSave = new ArrayList<ProductPriceList>();
		for(ProductPriceListView item : creationPriceList) {
			ProductPriceList listItem = createPriceListItem(item, priceListId);
			if(listItem.isValid()) {
				priceListItemsToSave.add(listItem);
			}
		}
		
		productPriceListRepository.saveAll(priceListItemsToSave);
	}

	
    private ProductPriceList createPriceListItem(ProductPriceListView listView, Long priceListId) {
    	Optional<Product> product = productRepository.findById(listView.getProductId());
    	
    	ProductPriceList priceList = new ProductPriceList();
    	priceList.setStatus(Status.ACTIVE);
    	priceList.setProduct(product.get());
    	priceList.setPriceListId(priceListId);
    	priceList.setPrice(listView.getProductPrice());
    	return priceList;
    }
}
