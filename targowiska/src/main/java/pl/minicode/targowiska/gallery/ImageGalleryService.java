package pl.minicode.targowiska.gallery;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pl.minicode.targowiska.common.Status;

@Service
public class ImageGalleryService implements IImageGalleryService {
	
	@Autowired
	private ImageGalleryRepository imageGalleryRepository;

	@Override
	public Page<ImageGallery> findAll(Pageable pageable) {
		return imageGalleryRepository.findAll(pageable);
	}

	@Override
	public List<ImageGallery> findAll() {
		return (List<ImageGallery>) imageGalleryRepository.findAll();
	}

	@Override
	public ImageGallery findById(Long id) throws EntityNotFoundException {
		return imageGalleryRepository.findById(id).orElseThrow(()-> throwEntityNotFoundException(id));
	}

	@Override
	public ImageGallery save(ImageGallery entity) {
		return imageGalleryRepository.save(entity);
	}

	@Override
	public ImageGallery update(ImageGallery entity) {
		return imageGalleryRepository.save(entity);
	}

	@Override
	public ImageGallery delete(ImageGallery entity) {
		entity.setStatus(Status.DELETED);
		return save(entity);
	}

	@Override
	public EntityNotFoundException throwEntityNotFoundException(Long id) {
		return new EntityNotFoundException("IMAGE: Not found entity with given id:" + id);
	}

	@Override
	public Page<ImageGallery> getLastXRows(int numberOfRows, Pageable pageable) {
		return imageGalleryRepository.getLastXRows(numberOfRows, pageable);
	}

}
