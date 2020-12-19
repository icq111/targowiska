package pl.minicode.targowiska.guicontent;

import javax.persistence.Column;
import javax.persistence.Entity;

import pl.minicode.targowiska.entity.BasicEntity;

@Entity
public class GuiContent extends BasicEntity {

    @Column(columnDefinition="TEXT NOT NULL") 
	private String text;
	private String contentType;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
