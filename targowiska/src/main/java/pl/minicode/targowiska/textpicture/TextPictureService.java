package pl.minicode.targowiska.textpicture;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TextPictureService implements ITextPictureService {

	@Autowired
	private TextPictoreRepository textPictureRepository;

	@Override
	public List<TextPicture> findAll() {
		return (List<TextPicture>) textPictureRepository.findAll();
	}

	@Override
	public TextPicture findById(Long id) throws EntityNotFoundException {
		return textPictureRepository.findById(id).orElseThrow(() -> throwEntityNotFoundException(id));
	}

	@Override
	public TextPicture save(TextPicture entity) {
		return textPictureRepository.save(entity);
	}

	@Override
	public TextPicture update(TextPicture entity) {
		return save(entity);
	}

	@Override
	public TextPicture delete(TextPicture entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityNotFoundException throwEntityNotFoundException(Long id) {
		return new EntityNotFoundException("TEXT PICTURE: Not found entity with given id:" + id);

	}

	@Override
	public List<TextPicture> findByOwnerControllerClass(String ownerControllerClass) {
		return textPictureRepository.findByOwnerControllerClass(ownerControllerClass);
	}

}
