package pl.minicode.targowiska.contractor;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContractorService implements IContractorService {

	public ContractorService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Page<Contractor> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contractor> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contractor findById(Long id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contractor save(Contractor entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contractor update(Contractor entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contractor delete(Contractor entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityNotFoundException throwEntityNotFoundException(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
