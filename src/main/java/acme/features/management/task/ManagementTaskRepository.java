package acme.features.management.task;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Management;
import acme.entities.tasks.Task;
import acme.entities.workPlan.WorkPlan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagementTaskRepository extends AbstractRepository{

	@Query("select t from Task t where t.id = ?1")
	Task findOneTaskById(int id);

	@Query("select m from Management m where m.id = ?1")
	Management findOneManagementById(int id);

	@Query("select t from Task t where t.management.id = ?1")
	Collection<Task> findManyByManagementId(int managerId);

	@Query("select w from WorkPlan w join w.tasks t where t.id = ?1")
    List<WorkPlan> findWorkPlansByTaskId(int id);
	
}
