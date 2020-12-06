package pl.minicode.targowiska.productprice;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import pl.minicode.targowiska.entity.BasicEntity;
import pl.minicode.targowiska.product.Product;
import pl.minicode.targowiska.productcategory.ProductCategory;

@Entity
public class ProductPriceList extends BasicEntity {

	private BigDecimal price;
	
	private Long priceListId;
	
    @OneToOne
    private Product product;
    
    @OneToOne
    private ProductCategory productCategory;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getPriceListId() {
		return priceListId;
	}

	public void setPriceListId(Long priceListId) {
		this.priceListId = priceListId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
    
}
