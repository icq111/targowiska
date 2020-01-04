package pl.minicode.targowiska.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ProductPriceHistory {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
	
	private BigDecimal productPrice;
	
    @Temporal(TemporalType.TIMESTAMP)
    private Date productPriceStamp;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public Date getProductPriceStamp() {
		return productPriceStamp;
	}

	public void setProductPriceStamp(Date productPriceStamp) {
		this.productPriceStamp = productPriceStamp;
	}

	@Override
	public String toString() {
		return "ProductPriceHistory [id=" + id + ", product=" + product + ", productPrice=" + productPrice
				+ ", productPriceStamp=" + productPriceStamp + "]";
	}
}
