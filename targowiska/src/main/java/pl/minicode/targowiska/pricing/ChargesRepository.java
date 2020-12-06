package pl.minicode.targowiska.pricing;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import pl.minicode.targowiska.common.Status;

public interface ChargesRepository extends JpaRepositoryImplementation<Charges, Long> {

	List<Charges> findByStatus(Status status);
}
