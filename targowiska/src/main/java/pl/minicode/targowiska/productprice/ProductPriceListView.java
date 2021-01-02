package pl.minicode.targowiska.productprice;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;

import pl.minicode.targowiska.productcategory.ProductCategory;

@Entity
@Immutable
@Subselect("select hs.* from product_price_list_view hs")
public class ProductPriceListView implements Serializable {

	@Id
	private String id;
	private Long priceListId;
	@Enumerated(EnumType.STRING)
	private ProductCategory productCategory;
	private Long productId;
	private String productName;
	private BigDecimal minimumProductPrice;
	private BigDecimal maximumProductPrice;
	private BigDecimal oldProductPrice;
	private String unit;

	@Temporal(TemporalType.TIMESTAMP)
	private Date priceListDate;
	private String productStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getPriceListId() {
		return priceListId;
	}

	public void setPriceListId(Long priceListId) {
		this.priceListId = priceListId;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getMinimumProductPrice() {
		return minimumProductPrice;
	}

	public void setMinimumProductPrice(BigDecimal minimumProductPrice) {
		this.minimumProductPrice = minimumProductPrice;
	}

	public BigDecimal getMaximumProductPrice() {
		return maximumProductPrice;
	}

	public void setMaximumProductPrice(BigDecimal maximumProductPrice) {
		this.maximumProductPrice = maximumProductPrice;
	}

	public BigDecimal getOldProductPrice() {
		return oldProductPrice;
	}

	public void setOldProductPrice(BigDecimal oldProductPrice) {
		this.oldProductPrice = oldProductPrice;
	}

	public Date getPriceListDate() {
		return priceListDate;
	}

	public void setPriceListDate(Date priceListDate) {
		this.priceListDate = priceListDate;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
