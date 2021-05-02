package acme.features.manager.workPlan;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.workPlan.WorkPlan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerWorkPlanRepository extends AbstractRepository{

	@Query("select w from WorkPlan w where w.manager.id = ?1")
	Collection<WorkPlan> findManyByManagerId(int activeRoleId);

	@Query("select w from WorkPlan w where w.id = ?1")
	WorkPlan findOneWorkPlanById(int id);

	@Query("select m from Manager m where m.id = ?1")
	Manager findOneManagerById(int activeRoleId);
	
	@Query("select t from Task t where (t.isPublic=1 or t.manager.id =?1)")
	public Collection<Task> findTasksAvailable(int id, int idWP);
}