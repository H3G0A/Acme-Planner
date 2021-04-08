package acme.features.administrator.task;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Task;
import acme.framework.services.AbstractCreateService;
@Service
public class AdministratorTaskCreateService implements AbstractCreateService<Administrator, Task> {

	@Autowired
	protected AdministratorTaskRepository repository;

	@Override
	public boolean authorise(final Request<Task> request) {
		// TODO Auto-generated method stub
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title","workload","description","link","esPublico");
		
	}

	@Override
	public Task instantiate(final Request<Task> request) {
		// TODO Auto-generated method stub
		assert request != null;
		
		final Task result;
		final LocalDateTime executionPeriod;
		
		executionPeriod = LocalDateTime.now();
		
		result = new Task();
		result.setTitle("DP");
		result.setDescription("Hola");
		result.setExecutionPeriod(executionPeriod);
		result.setLink("https://www.marca.com");
		result.setEsPublico(true);
		result.setWorkload(2);
		
		
		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Task> request, final Task entity) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}
	
	
}
