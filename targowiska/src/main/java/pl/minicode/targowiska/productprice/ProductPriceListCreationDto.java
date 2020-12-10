package pl.minicode.targowiska.productprice;

import java.util.ArrayList;
import java.util.List;

public class ProductPriceListCreationDto {
	
	private List<ProductPriceListView> creationPriceList = new ArrayList<ProductPriceListView>();
	
	public void addPriceListItem(ProductPriceListView item) {
		creationPriceList.add(item);
	}

	public List<ProductPriceListView> getCreationPriceList() {
		return creationPriceList;
	}

	public void setCreationPriceList(List<ProductPriceListView> creationPriceList) {
		this.creationPriceList = creationPriceList;
	}

	
}
