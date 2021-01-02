package pl.minicode.targowiska.news;


import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.multipart.MultipartFile;

import pl.minicode.targowiska.entity.BasicEntity;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class News extends BasicEntity {
     
    @NotBlank(message = "Title is mandatory")
    private String title;
     
    @NotBlank(message = "Short Description is mandatory")
    private String shortDescription;
    
    @NotBlank(message = "Long Description is mandatory")
    @Column(columnDefinition="TEXT NOT NULL") 
    //@Lob 
    private String longDescription;
    
    private String imageName;
    
    private String minImageName;
    
    @Transient
    private MultipartFile file;
    
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}


	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public String getMinImageName() {
		return minImageName;
	}

	public void setMinImageName(String minImageName) {
		this.minImageName = minImageName;
	}

	public String getImageURL() {
		return File.separator + "news" + File.separator + getImageName();
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	@Override
	public String toString() {
		String s = super.toString();
		return s + " News [title=" + title + ", shortDescription=" + shortDescription + ", longDescription="
				+ longDescription + ", imageName=" + imageName + "]";
	}

	
}
