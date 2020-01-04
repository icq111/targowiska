package pl.minicode.targowiska.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
public class ProductPrice {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date insertStamp;
    
    private BigDecimal productPrice;
    
    //procentowa zmiana ceny od ostatniego notowania
    private BigDecimal productPriceDifference;
    
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date productPriceUpdateStamp;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	private List<ProductPriceHistory> productPriceHistories;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getInsertStamp() {
		return insertStamp;
	}

	public void setInsertStamp(Date insertStamp) {
		this.insertStamp = insertStamp;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public BigDecimal getProductPriceDifference() {
		return productPriceDifference;
	}

	public void setProductPriceDifference(BigDecimal productPriceDifference) {
		this.productPriceDifference = productPriceDifference;
	}

	public Date getProductPriceUpdateStamp() {
		return productPriceUpdateStamp;
	}

	public void setProductPriceUpdateStamp(Date productPriceUpdateStamp) {
		this.productPriceUpdateStamp = productPriceUpdateStamp;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<ProductPriceHistory> getProductPriceHistories() {
		return productPriceHistories;
	}

	public void setProductPriceHistories(List<ProductPriceHistory> productPriceHistories) {
		this.productPriceHistories = productPriceHistories;
	}

	@Override
	public String toString() {
		return "ProductPrice [id=" + id + ", insertStamp=" + insertStamp + ", productPrice=" + productPrice
				+ ", productPriceDifference=" + productPriceDifference + ", productPriceUpdateStamp="
				+ productPriceUpdateStamp + ", product=" + product + ", productPriceHistories=" + productPriceHistories
				+ "]";
	}
}
