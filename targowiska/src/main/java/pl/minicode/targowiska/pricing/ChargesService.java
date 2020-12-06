package pl.minicode.targowiska.pricing;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pl.minicode.targowiska.common.Status;

@Service
public class ChargesService implements IChargesService {
	@Autowired
	private ChargesRepository chargesRepository;

	@Override
	public List<Charges> findAll() {
		return (List<Charges>) chargesRepository.findAll();
	}

	@Override
	public Charges save(Charges entity) {
		return chargesRepository.save(entity);
	}

	@Override
	public Charges update(Charges entity) {
		return save(entity);
	}

	@Override
	public Charges delete(Charges entity) {
		entity.setStatus(Status.DELETED);
		return save(entity);
	}

	@Override
	public Charges findById(Long id) throws EntityNotFoundException {
		return  chargesRepository.findById(id).orElseThrow(()-> throwEntityNotFoundException(id));
	}

	@Override
	public Page<Charges> findAll(Pageable pageable) {
		return chargesRepository.findAll(pageable);
	}

	@Override
	public EntityNotFoundException throwEntityNotFoundException(Long id) {
		return new EntityNotFoundException("Charges: Not found entity with given id:" + id);
	}
	
	public List<Charges> findActiveCharges() {
		return chargesRepository.findByStatus(Status.ACTIVE);
	}
}
