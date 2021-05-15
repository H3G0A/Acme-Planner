package acme.features.management.task;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Management;
import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class ManagementTaskListService implements AbstractListService<Management, Task>{

	@Autowired
	protected ManagementTaskRepository repository;
	
	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		return true;
	}
	


	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title","start","end");
		
	}


	@Override
	public Collection<Task> findMany(final Request<Task> request) {
		assert request != null;	
	    Collection<Task> result;
	    result = this.repository.findManyByManagementId(request.getPrincipal().getActiveRoleId());
		
		return result;
	}
}
