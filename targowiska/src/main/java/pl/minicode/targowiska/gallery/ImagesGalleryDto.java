package pl.minicode.targowiska.gallery;

import java.util.ArrayList;
import java.util.List;

import org.thymeleaf.util.ListUtils;

public class ImagesGalleryDto {
	
	private List<ImageGallery> imageGalleries;
	
	public void addImageGallery(ImageGallery ig) {
		if(ListUtils.isEmpty(imageGalleries)) {
			this.imageGalleries = new ArrayList<ImageGallery>();
		}
		this.imageGalleries.add(ig);
	}
	
	public int getImageGaleriesSize() {
		return getImageGalleries() != null ? getImageGalleries().size() : 0;
	}

	public List<ImageGallery> getImageGalleries() {
		return imageGalleries;
	}

	public void setImageGalleries(List<ImageGallery> imageGalleries) {
		this.imageGalleries = imageGalleries;
	}

	@Override
	public String toString() {
		return "ImagesGalleryDto [imageGalleries=" + imageGalleries + "]";
	}
}
