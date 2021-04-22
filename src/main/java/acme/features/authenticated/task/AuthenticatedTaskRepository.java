package acme.features.authenticated.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface AuthenticatedTaskRepository extends AbstractRepository {

    @Query("select tsk from Task tsk WHERE tsk.isPublic = 1")
	Collection<Task> findMany();

    @Query("select tsk from Task tsk where tsk.id = ?1")
   	Task findOneTaskById(int id);

}
	
