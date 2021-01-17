package pl.minicode.targowiska.news;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import pl.minicode.targowiska.common.Status;

@Repository
public interface NewsRepository extends PagingAndSortingRepository<News, Long>{
	
	@Query(nativeQuery= true, value ="select * from news n order by n.insert_stamp limit 4 ")
	public List<News> find4LastNews();
	
	@Query(nativeQuery= true, value ="select * from news n where n.important = true order by n.insert_stamp limit 4 ")
	public List<News> find4LastImportantNews();
	
	Page<News> findByStatus(Pageable pageable, Status status);
	
	Page<News> findByStatusNotIn(List<Status> statuses, Pageable pageable);
}
