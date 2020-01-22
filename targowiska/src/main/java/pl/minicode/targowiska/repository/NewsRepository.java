package pl.minicode.targowiska.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import pl.minicode.targowiska.domain.News;

@Repository
public interface NewsRepository extends PagingAndSortingRepository<News, Long>{
	
	@Query(nativeQuery= true, value ="select * from news n order by n.insertStamp limit 4 ")
	public List<News> find4LastNews();
}
