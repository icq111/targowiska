package pl.minicode.targowiska.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import pl.minicode.targowiska.type.ProductType;
import pl.minicode.targowiska.type.Status;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Product {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	private String productName;
	
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date insertStamp;
    
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updateStamp;

    private BigDecimal oldProductPrice;
    
    private BigDecimal productPrice;
    
    //procentowa zmiana ceny od ostatniego notowania
    private BigDecimal productPriceDifference;
    
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date productPriceUpdateStamp;
    
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
//	private List<ProductPriceHistory> productPriceHistories;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public Date getInsertStamp() {
		return insertStamp;
	}

	public void setInsertStamp(Date insertStamp) {
		this.insertStamp = insertStamp;
	}

	public Date getUpdateStamp() {
		return updateStamp;
	}

	public void setUpdateStamp(Date updateStamp) {
		this.updateStamp = updateStamp;
	}

	public BigDecimal getOldProductPrice() {
		return oldProductPrice;
	}

	public void setOldProductPrice(BigDecimal oldProductPrice) {
		this.oldProductPrice = oldProductPrice;
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

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", status=" + status + ", productType="
				+ productType + ", insertStamp=" + insertStamp + ", updateStamp=" + updateStamp + ", oldProductPrice="
				+ oldProductPrice + ", productPrice=" + productPrice + ", productPriceDifference="
				+ productPriceDifference + ", productPriceUpdateStamp=" + productPriceUpdateStamp + "]";
	}
}
