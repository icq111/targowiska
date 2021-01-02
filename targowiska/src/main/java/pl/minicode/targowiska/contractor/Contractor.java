package pl.minicode.targowiska.contractor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import pl.minicode.targowiska.entity.BasicEntity;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Contractor extends BasicEntity{

    @NotBlank(message = "Name is mandatory")
	private String name;
	
    private String imageLogoName;
    
    private String minImageLogoName;
    
    @Column(columnDefinition="TEXT NOT NULL") 
    private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageLogoName() {
		return imageLogoName;
	}

	public void setImageLogoName(String imageLogoName) {
		this.imageLogoName = imageLogoName;
	}
	

	public String getMinImageLogoName() {
		return minImageLogoName;
	}

	public void setMinImageLogoName(String minImageLogoName) {
		this.minImageLogoName = minImageLogoName;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return super.toString() +  " Contractor [name=" + name + ", imageLogoName=" + imageLogoName + "]";
	}
    
    

}
