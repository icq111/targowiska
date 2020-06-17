package pl.minicode.targowiska.service.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.minicode.targowiska.service.IProductCategoryService;
import pl.minicode.targowiska.service.IProductService;

@Service
public class ProductValidatorService {

	@Autowired
	IProductService productService;
	
	@Autowired 
	IProductCategoryService productCategoryService;
	
	public boolean isAddNewProductAllowed() {
		boolean result = false;
		//jesli sa juz produkty to wszystko ok
		result = result && productService.countRows() > 0;
		if(!result) {
			//jesli nie ma produtow to aby dodac nowy musza byc kategorie
			result = productCategoryService.countRows() > 0;
		}
		
		return result;
	}
	
	public boolean isAddProductPricesAllowed() {
		return productService.countRows() > 0;
	}
	
}
