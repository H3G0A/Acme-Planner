package acme.features.authenticated.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedTaskShowService implements AbstractShowService<Authenticated, Task>{

	
	@Autowired
	protected AuthenticatedTaskRepository repository;
	
	@Override
	public boolean authorise(final Request<Task> request) {
		final boolean result;
		final int taskId;
		final Task task;

		taskId=request.getModel().getInteger("id");
		task=this.repository.findOneTaskById(taskId);
		
		result = task.getIsPublic();
		return result;
	}
	
	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title","start","end","workload","description","isPublic","link");
		
	}


	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;
		Task result;
		int id;
		id= request.getModel().getInteger("id");
		result = this.repository.findOneTaskById(id);
		return result;
	}

}
