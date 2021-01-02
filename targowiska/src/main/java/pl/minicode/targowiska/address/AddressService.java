package pl.minicode.targowiska.address;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pl.minicode.targowiska.common.Status;

@Service
public class AddressService implements IAddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public Page<Address> findAll(Pageable pageable) {
		return addressRepository.findAll(pageable);
	}

	@Override
	public List<Address> findAll() {
		return addressRepository.findAll();
	}

	@Override
	public Address findById(Long id) throws EntityNotFoundException {
		return addressRepository.findById(id).orElseThrow(()-> throwEntityNotFoundException(id));
	}

	@Override
	public Address save(Address entity) {
		return addressRepository.save(entity);
	}

	@Override
	public Address update(Address entity) {
		return save(entity);
	}

	@Override
	public Address delete(Address entity) {
		entity.setStatus(Status.DELETED);
		return save(entity);
	}

	@Override
	public EntityNotFoundException throwEntityNotFoundException(Long id) {
		return new EntityNotFoundException("ADDRESS: Not found entity with given id:" + id);
	}

	@Override
	public Address getLastRowInTable() {
		return addressRepository.findTopByOrderByIdDesc();
	}

}
