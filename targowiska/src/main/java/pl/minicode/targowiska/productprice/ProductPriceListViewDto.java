package pl.minicode.targowiska.productprice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.thymeleaf.util.ListUtils;

import pl.minicode.targowiska.unit.Unit;

public class ProductPriceListViewDto {

	private List<ProductPriceListView> localFruitsList = new ArrayList<>();
	private List<ProductPriceListView> foreignFruitsList = new ArrayList<>();
	private List<ProductPriceListView> localVegetablesList = new ArrayList<>();
	private List<ProductPriceListView> foreignVegetablesList = new ArrayList<>();

	
	private boolean isAddProductPricesAllowed = false;
	
	private Date priceListDate;
	
	public ProductPriceListViewDto() {
	}

	private ProductPriceListViewDto(List<ProductPriceListView> priceList, boolean isAddProductPricesAllowed) {
		sortByProductCategory(priceList);
		setPriceListDate(priceList);
		this.isAddProductPricesAllowed = isAddProductPricesAllowed;

	}
	
	private void setPriceListDate(List<ProductPriceListView> priceList) {
		if(!ListUtils.isEmpty(priceList)) {
			this.priceListDate = priceList.get(0).getPriceListDate();
		}
		
	}

	public static ProductPriceListViewDto createProductPriceListViewDto(List<ProductPriceListView> priceList, boolean isAddProductPricesAllowed) {
		return new ProductPriceListViewDto(priceList, isAddProductPricesAllowed);
	}
	
	private void sortByProductCategory(List<ProductPriceListView> priceList) {
		for(ProductPriceListView row : priceList) {
			if(row.getProductCategory().isLocalFruit()) {localFruitsList.add(row);}
			if(row.getProductCategory().isForeignFruit()) {foreignFruitsList.add(row);}
			if(row.getProductCategory().isLocalVegetable()) {localVegetablesList.add(row);}
			if(row.getProductCategory().isForeignVegetable()) {foreignVegetablesList.add(row);}
			
		}
	}

	public List<ProductPriceListView> getLocalFruitsList() {
		return localFruitsList;
	}

	public List<ProductPriceListView> getForeignFruitsList() {
		return foreignFruitsList;
	}

	public List<ProductPriceListView> getLocalVegetablesList() {
		return localVegetablesList;
	}

	public List<ProductPriceListView> getForeignVegetablesList() {
		return foreignVegetablesList;
	}

	public List<ProductPriceListView> getAll(){
		List<ProductPriceListView> result = new ArrayList<>();
		result.addAll(localFruitsList);
		result.addAll(localVegetablesList);
		result.addAll(foreignFruitsList);
		result.addAll(foreignVegetablesList);
		return result;
		
	}

	public boolean isAddProductPricesAllowed() {
		return isAddProductPricesAllowed;
	}

	public Date getPriceListDate() {
		return priceListDate;
	}
	
}
