package acme.features.spamFilter;


import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spam.SpamWord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSpamWordRepository extends AbstractRepository {
	
	@Query("select s from SpamWord s")
	SpamWord findOne();
	
	@Query("SELECT s FROM SpamWord s WHERE s.id = ?1")
	SpamWord findOneSpamWordById(int id);
}

