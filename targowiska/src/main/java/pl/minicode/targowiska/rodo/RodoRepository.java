package pl.minicode.targowiska.rodo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RodoRepository extends CrudRepository<Rodo, Long> {

}
