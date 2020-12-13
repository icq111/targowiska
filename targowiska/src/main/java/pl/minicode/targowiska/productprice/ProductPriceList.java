package pl.minicode.targowiska.productprice;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToOne;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import pl.minicode.targowiska.entity.BasicEntity;
import pl.minicode.targowiska.product.Product;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class ProductPriceList extends BasicEntity {

	private BigDecimal price;
	
	private Long priceListId;
	
    @OneToOne
    private Product product;
    
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

	public boolean isValid() {
		boolean result = this.getPrice() != null;
		result = result && this.getPriceListId() != null;
		result = result && this.getProduct() != null;
		result = result && this.getStatus() != null;
		return result;
	}

}
