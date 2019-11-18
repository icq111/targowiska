package pl.minicode.targowiska.repository;



import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.minicode.targowiska.domain.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByUsername(String userName);
}
