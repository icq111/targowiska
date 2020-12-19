package pl.minicode.targowiska.guicontent;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GuiContentRepository extends CrudRepository<GuiContent, Long> {

	
	@Query(nativeQuery= true, value ="select * from gui_content n where n.content_type in (:contentTypes)  ")
	public List<GuiContent> findByContentTypeIn(@Param("contentTypes") List<String> contentTypes);
	
}
