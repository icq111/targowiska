package pl.minicode.targowiska.pricing;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import pl.minicode.targowiska.entity.BasicEntity;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Charges  extends BasicEntity{

    @NotBlank(message = "Name is mandatory")
    private String name;
    
    @NotBlank(message = "Long Description is mandatory")
    @Column(columnDefinition="TEXT NOT NULL") 
    //@Lob 
    private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return super.toString() + " Charges [name=" + name + ", description=" + description + "]";
	}


}
