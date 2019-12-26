package pl.minicode.targowiska.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ProductPriceHistory {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
//	private long productId;
	
	private BigDecimal productPrice;
	
    @Temporal(TemporalType.TIMESTAMP)
    private Date productPriceStamp;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productId", nullable = false) 
    private Product product;
}
