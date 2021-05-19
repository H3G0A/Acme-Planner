package acme.features.management.workPlan;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Management;
import acme.entities.tasks.Task;
import acme.entities.workPlan.WorkPlan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagementWorkPlanRepository extends AbstractRepository{

	@Query("select w from WorkPlan w where w.management.id = ?1 ORDER BY w DESC")
	Collection<WorkPlan> findManyByManagementId(int activeRoleId);

	@Query("select w from WorkPlan w where w.id = ?1")
	WorkPlan findOneWorkPlanById(int id);

	@Query("select m from Management m where m.id = ?1")
	Management findOneManagementById(int activeRoleId);
	
	@Query("select t from Task t where (t.isPublic=1 or t.management.id =?1)")
	public Collection<Task> findTasksAvailable(int id, int idWP);
}
