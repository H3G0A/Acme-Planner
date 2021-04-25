package acme.features.anonymous.workPlan;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.workPlan.WorkPlan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousWorkPlanRepository extends AbstractRepository {

	@Query("select w from WorkPlan w where w.isPublic=true")
	Collection<WorkPlan> findPublicWorkPlans();
	
	@Query("SELECT w FROM WorkPlan w WHERE w.id = ?1")
	WorkPlan findOneWorkPlanById(int id);
}
