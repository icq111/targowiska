package pl.minicode.targowiska.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.minicode.targowiska.domain.Rodo;
import pl.minicode.targowiska.repository.RodoRepository;
import pl.minicode.targowiska.service.IRodoService;

@Service
public class RodoService implements IRodoService {

	@Autowired
	private RodoRepository rodoRepository;

	@Override
	public List<Rodo> findAll() {
		return (List<Rodo>) rodoRepository.findAll();
	}

	@Override
	public Rodo findById(Long id) throws EntityNotFoundException {
		return rodoRepository.findById(id).orElseThrow(() -> throwEntityNotFoundException(id));
	}

	@Override
	public Rodo save(Rodo entity) {
		return rodoRepository.save(entity);
	}

	@Override
	public Rodo update(Rodo entity) {
		return save(entity);
	}

	@Override
	public Rodo delete(Rodo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityNotFoundException throwEntityNotFoundException(Long id) {
		return new EntityNotFoundException("RODO: Not found entity with given id:" + id);

	}

}
