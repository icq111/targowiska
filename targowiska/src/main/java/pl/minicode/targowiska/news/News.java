package pl.minicode.targowiska.news;


import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.multipart.MultipartFile;

import pl.minicode.targowiska.entity.BasicEntity;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class News extends BasicEntity {
     
    @NotBlank(message = "Title is mandatory")
    @Length(max = 255, min = 10, message = "Tekst musi mieć długość od 10 do 255 znaków")  
    private String title;
    
    @NotBlank(message = "Opis jest wymagany")
    @Column(columnDefinition="TEXT NOT NULL") 
    //@Lob 
    private String longDescription;
    
    @Column(columnDefinition="TEXT NOT NULL") 
    private String longDescriptionPlainText;
    
    private String imageName;
    
    private String minImageName;
    
    private boolean important;
    
    @Transient
    private MultipartFile file;
    
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public boolean isImportant() {
		return important;
	}

	public void setImportant(boolean important) {
		this.important = important;
	}
	

	public String getLongDescriptionPlainText() {
		return longDescriptionPlainText;
	}

	public void setLongDescriptionPlainText(String longDescriptionPlainText) {
		this.longDescriptionPlainText = longDescriptionPlainText;
	}

	@Override
	public String toString() {
		String s = super.toString();
		return s + " News [title=" + title + ", longDescription=" + longDescription + ", longDescriptionPlainText="
				+ longDescriptionPlainText + ", imageName=" + imageName + ", minImageName=" + minImageName
				+ ", important=" + important + ", file=" + file + "]";
	}



	
}
