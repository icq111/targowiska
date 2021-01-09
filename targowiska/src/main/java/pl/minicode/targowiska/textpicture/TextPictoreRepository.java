package pl.minicode.targowiska.textpicture;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextPictoreRepository extends CrudRepository<TextPicture, Long> {

	List<TextPicture> findByOwnerControllerClass(String ownerControllerClass);
}
