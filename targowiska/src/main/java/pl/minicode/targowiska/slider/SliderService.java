package pl.minicode.targowiska.slider;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pl.minicode.targowiska.common.Status;

@Service
public class SliderService implements ISliderService {
	
	@Autowired
	private SliderRepository sliderRepository;

	@Override
	public List<Slider> findAll() {
		return (List<Slider>) sliderRepository.findAll();
	}

	@Override
	public Slider findById(Long id) throws EntityNotFoundException {
		return sliderRepository.findById(id).orElseThrow(() -> throwEntityNotFoundException(id));
	}

	@Override
	public Slider save(Slider entity) {
		return sliderRepository.save(entity);
	}

	@Override
	public Slider update(Slider entity) {
		return save(entity);
	}

	@Override
	public Slider delete(Slider entity) {
		entity.setStatus(Status.DELETED);
		return save(entity);
	}

	@Override
	public EntityNotFoundException throwEntityNotFoundException(Long id) {
		return new EntityNotFoundException("SLIDER: Not found entity with given id:" + id);	}

	@Override
	public List<Slider> findAll(Sort sort) {
		return (List<Slider>) sliderRepository.findAll(sort);
	}

}
