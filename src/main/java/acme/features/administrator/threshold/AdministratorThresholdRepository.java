package acme.features.administrator.threshold;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spam.SpamWord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorThresholdRepository extends AbstractRepository{
	
	@Query("select s.threshold from SpamWord s")
    double findThreshold();
	
	@Query("select s from SpamWord s")
	SpamWord findSpam();

}
