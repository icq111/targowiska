package pl.minicode.targowiska.guicontent;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuiContentServiceImpl implements IGuiContentService {

	@Autowired
	private GuiContentRepository repository;
	
	@Override
	public List<GuiContent> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GuiContent findById(Long id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GuiContent save(GuiContent entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GuiContent update(GuiContent entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GuiContent delete(GuiContent entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityNotFoundException throwEntityNotFoundException(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GuiContent> findByContentTypeIn(List<String> contentTypes) {
		return repository.findByContentTypeIn(contentTypes);
	}

}
