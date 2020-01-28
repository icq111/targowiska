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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import pl.minicode.targowiska.type.OfferType;
import pl.minicode.targowiska.type.Status;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class ImageGallery {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
     
    @NotBlank(message = "Image name is mandatory")
    private String imageName;
    
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date insertStamp;
    
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updateStamp;
    
    @Enumerated(EnumType.STRING)
    private Status status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ImageGallery [id=" + id + ", imageName=" + imageName + ", insertStamp=" + insertStamp + ", updateStamp="
				+ updateStamp + ", status=" + status + "]";
	}
}
