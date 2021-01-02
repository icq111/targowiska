package pl.minicode.targowiska.unit;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitService implements IUnitService {

	@Autowired
	private UnitRepository unitRepository;

	@Override
	public List<Unit> findAll() {
		return (List<Unit>) unitRepository.findAll();
	}

	@Override
	public Unit findById(Long id) throws EntityNotFoundException {
		return unitRepository.findById(id).orElseThrow(() -> throwEntityNotFoundException(id));
	}

	@Override
	public Unit save(Unit entity) {
		return unitRepository.save(entity);
	}

	@Override
	public Unit update(Unit entity) {
		return save(entity);
	}

	@Override
	public Unit delete(Unit entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityNotFoundException throwEntityNotFoundException(Long id) {
		return new EntityNotFoundException("UNIT: Not found entity with given id:" + id);

	}

}
