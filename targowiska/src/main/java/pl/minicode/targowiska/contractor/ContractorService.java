package pl.minicode.targowiska.contractor;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pl.minicode.targowiska.common.Status;

@Service
public class ContractorService implements IContractorService {

	@Autowired
	private ContractorRepository contractorRepository;

	@Override
	public Page<Contractor> findAll(Pageable pageable) {
		return contractorRepository.findAll(pageable);
	}

	@Override
	public List<Contractor> findAll() {
		return contractorRepository.findAll();
	}

	@Override
	public Contractor findById(Long id) throws EntityNotFoundException {
		return contractorRepository.findById(id).orElseThrow(()-> throwEntityNotFoundException(id));
	}

	@Override
	public Contractor save(Contractor entity) {
		return contractorRepository.save(entity);
	}

	@Override
	public Contractor update(Contractor entity) {

		return save(entity);
	}

	@Override
	public Contractor delete(Contractor entity) {
		entity.setStatus(Status.DELETED);
		return save(entity);
	}

	@Override
	public EntityNotFoundException throwEntityNotFoundException(Long id) {
		return new EntityNotFoundException("CONTRACTOR: Not found entity with given id:" + id);
	}

}
