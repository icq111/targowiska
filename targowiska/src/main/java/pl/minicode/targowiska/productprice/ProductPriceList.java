package pl.minicode.targowiska.productprice;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToOne;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import pl.minicode.targowiska.entity.BasicEntity;
import pl.minicode.targowiska.product.Product;
import pl.minicode.targowiska.unit.Unit;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class ProductPriceList extends BasicEntity {

	private BigDecimal minimumPrice;
	
	private BigDecimal maximumPrice;
	
	private Long priceListId;
	
    @OneToOne
    private Product product;
    
	public BigDecimal getMinimumPrice() {
		return minimumPrice;
	}

	public void setMinimumPrice(BigDecimal minimumPrice) {
		this.minimumPrice = minimumPrice;
	}

	public BigDecimal getMaximumPrice() {
		return maximumPrice;
	}

	public void setMaximumPrice(BigDecimal maximumPrice) {
		this.maximumPrice = maximumPrice;
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
		boolean result = this.getMinimumPrice() != null;
		result = result && this.getMaximumPrice() != null;
		result = result && this.getPriceListId() != null;
		result = result && this.getProduct() != null;
		result = result && this.getStatus() != null;
		return result;
	}

}
