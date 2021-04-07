package acme.features.administrator.task;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Task;
import acme.framework.services.AbstractListService;
@Service
public class AdministratorTaskListService implements AbstractListService<Administrator, Task> {

	@Autowired
	AdministratorTaskRepository repository;

	@Override
	public boolean authorise(final Request<Task> request) {
		// TODO Auto-generated method stub
		assert request != null;
		
		return true;
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title","executionPeriod","workload","description","link","esPublico");
	}

	@Override
	public Collection<Task> findMany(final Request<Task> request) {
		// TODO Auto-generated method stub
		assert request != null;
		
	    Collection<Task> result;
	    
	    result = this.repository.findMany();
		
		return result;
	}
	
	
	
}
