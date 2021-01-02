package pl.minicode.targowiska.slider;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import pl.minicode.targowiska.entity.BasicEntity;

@Entity
public class Slider extends BasicEntity {

	private String bigText;
	
	private String smallText;
	
	private String fileName;
	
	private int slideId;
	
    @Transient
    private MultipartFile file;

	public String getBigText() {
		return bigText;
	}

	public void setBigText(String bigText) {
		this.bigText = bigText;
	}

	public String getSmallText() {
		return smallText;
	}

	public void setSmallText(String smallText) {
		this.smallText = smallText;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getSlideId() {
		return slideId;
	}

	public void setSlideId(int slideId) {
		this.slideId = slideId;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}
