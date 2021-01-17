package pl.minicode.targowiska.textpicture;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import pl.minicode.targowiska.entity.BasicEntity;

@Entity
public class TextPicture extends BasicEntity {

	@NotBlank(message = "Text is mandatory")
    @Column(columnDefinition="TEXT NOT NULL") 
	private String text;

	private String imageName;
	
	private String ownerControllerClass;
	
    @Transient
    private MultipartFile file;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getOwnerControllerClass() {
		return ownerControllerClass;
	}

	public void setOwnerControllerClass(String ownerControllerClass) {
		this.ownerControllerClass = ownerControllerClass;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return super.toString() + "TextPicture [rodoText=" + text + ", imageName=" + imageName + ", ownerControllerClass="
				+ ownerControllerClass + "]";
	}
	
	
}
