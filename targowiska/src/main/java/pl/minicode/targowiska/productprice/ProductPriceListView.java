package pl.minicode.targowiska.productprice;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;



@Entity
@Immutable
@Subselect("select uuid() as id, hs.* from product_price_list_view hs")
public class ProductPriceListView implements Serializable{

	@Id
	private String id;
	private Long pricelistId;
	private Long productCategoryId;
	private Long productId;
	private String productName;
	private String productCategoryName;
	private BigDecimal productPrice;
	private BigDecimal oldProductPrice;
	private BigDecimal percentualPriceDifference;
	public Long getPricelistId() {
		return pricelistId;
	}
	public Long getProductCategoryId() {
		return productCategoryId;
	}
	public Long getProductId() {
		return productId;
	}
	public String getProductName() {
		return productName;
	}
	public String getProductCategoryName() {
		return productCategoryName;
	}
	public BigDecimal getProductPrice() {
		return productPrice;
	}
	public BigDecimal getOldProductPrice() {
		return oldProductPrice;
	}
	public BigDecimal getPercentualPriceDifference() {
		return percentualPriceDifference;
	}
	
	
}
