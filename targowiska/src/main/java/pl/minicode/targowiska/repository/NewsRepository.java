package pl.minicode.targowiska.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.minicode.targowiska.domain.News;

@Repository
public interface NewsRepository extends CrudRepository<News, Long>{

}
