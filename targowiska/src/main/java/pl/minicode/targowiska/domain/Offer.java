package pl.minicode.targowiska.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import pl.minicode.targowiska.type.OfferType;
import pl.minicode.targowiska.type.Status;

@Entity
//@EntityListeners(AuditingEntityListener.class)
public class Offer {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
     
    @NotBlank(message = "Offer name is mandatory")
    private String offerName;
     
    //@NotBlank(message = "Short Description is mandatory")
    private String offerShortDescription;
    
    @NotBlank(message = "Offer Description is mandatory")
    @Column(columnDefinition="TEXT NOT NULL") 
    //@Lob 
    private String offerLongDescription;
    
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date insertStamp;
    
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updateStamp;
    
    @Enumerated(EnumType.STRING)
    private OfferType offerType;
    
    @Enumerated(EnumType.STRING)
    private Status status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public String getOfferShortDescription() {
		return offerShortDescription;
	}

	public void setOfferShortDescription(String offerShortDescription) {
		this.offerShortDescription = offerShortDescription;
	}

	public String getOfferLongDescription() {
		return offerLongDescription;
	}

	public void setOfferLongDescription(String offerLongDescription) {
		this.offerLongDescription = offerLongDescription;
	}
	

	public OfferType getOfferType() {
		return offerType;
	}

	public void setOfferType(OfferType offerType) {
		this.offerType = offerType;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	@Override
	public String toString() {
		return "Offer [id=" + id + ", offerName=" + offerName + ", offerShortDescription=" + offerShortDescription
				+ ", offerLongDescription=" + offerLongDescription + ", insertStamp=" + insertStamp + ", updateStamp="
				+ updateStamp + ", offerType=" + offerType + ", status=" + status + "]";
	}

	
}
