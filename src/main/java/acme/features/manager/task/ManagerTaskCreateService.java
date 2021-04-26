package acme.features.manager.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerTaskCreateService implements AbstractCreateService<Manager, Task>{

	// Internal state ---------------------------------------------------------

		@Autowired
		protected ManagerTaskRepository repository;
		

		// AbstractCreateService<Employer, Job> interface -------------------------


		@Override
		public boolean authorise(final Request<Task> request) {
			assert request != null;

			return true;
		}

		@Override
		public void validate(final Request<Task> request, final Task entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
			
			//validar fechas no en el pasado, start antes que end, workloud no puede ser mayo que execution period
			

		}

		@Override
		public void bind(final Request<Task> request, final Task entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			request.bind(entity, errors);
		}

		@Override
		public void unbind(final Request<Task> request, final Task entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "title","start","end","workload","description","isPublic","link");
		}

		@Override
		public Task instantiate(final Request<Task> request) {
			assert request != null;

			Task result;
			Manager manager;

			manager = this.repository.findOneManagerById(request.getPrincipal().getActiveRoleId());
			result = new Task();
			result.setManager(manager);

			return result;
		}

		@Override
		public void create(final Request<Task> request, final Task entity) {
			assert request != null;
			assert entity != null;

			this.repository.save(entity);
		}
}
