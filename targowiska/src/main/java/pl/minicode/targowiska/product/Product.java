package pl.minicode.targowiska.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import pl.minicode.targowiska.entity.BasicEntity;
import pl.minicode.targowiska.productcategory.ProductCategory;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Product extends BasicEntity {

	
	@NotBlank(message = "Name is mandatory")
	private String productName;
    
    private String imageName;
    
	@Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;
    
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
//	private List<ProductPriceHistory> productPriceHistories;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
}
