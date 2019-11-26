package pl.minicode.targowiska.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import pl.minicode.targowiska.type.OfferType;
import pl.minicode.targowiska.type.Status;

@Entity
public class Offer {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
     
    @NotBlank(message = "Title is mandatory")
    private String offerName;
     
    @NotBlank(message = "Short Description is mandatory")
    private String offerShortDescription;
    
    //@NotBlank(message = "Long Description is mandatory")
    private String offerLongDescription;
    
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

	@Override
	public String toString() {
		return "Offer [id=" + id + ", offerName=" + offerName + ", offerShortDescription=" + offerShortDescription
				+ ", offerLongDescription=" + offerLongDescription + "]";
	}
}
