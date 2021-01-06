package pl.minicode.targowiska.gallery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.minicode.targowiska.common.IPageableCommonService;

public interface IImageGalleryService extends IPageableCommonService<ImageGallery> {
	
	Page<ImageGallery> getLastXRows(int numberOfRows, Pageable pageable);

}
