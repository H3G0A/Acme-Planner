package acme.features.manager.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.features.spamFilter.AnonymousSpamDetectorService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerTaskUpdateService  implements AbstractUpdateService<Manager, Task>{

	// Internal state ---------------------------------------------------------

		@Autowired
		protected ManagerTaskRepository repository;
		
		@Autowired
		protected AnonymousSpamDetectorService spamDetector;

		// AbstractListService<manager, task> -------------------------------------


		@Override
		public boolean authorise(final Request<Task> request) {
			assert request != null;

			boolean result;
			int taskId;
			Task task;
			Manager manager;
			Principal principal;

			taskId = request.getModel().getInteger("id");
			task = this.repository.findOneTaskById(taskId);
			manager = task.getManager();
			principal = request.getPrincipal();
			result = manager.getUserAccount().getId() == principal.getAccountId();

			return result;
		}

		@Override
		public void validate(final Request<Task> request, final Task entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
			
			if (!errors.hasErrors("start") && !errors.hasErrors("end")) {
				
				errors.state(request, entity.getStart().before(entity.getEnd()), "end", "manager.task.form.error.endBeforeStart");
			}
			
			final String title = entity.getTitle();
			final String description = entity.getDescription();
			final String link = entity.getLink();
			
			if(this.spamDetector.detectSpam(title)) {
				errors.state(request, !this.spamDetector.detectSpam(title), "title", "manager.task.form.error.spam");
			}
			if(this.spamDetector.detectSpam(description)) {
				errors.state(request, !this.spamDetector.detectSpam(description), "description", "manager.task.form.error.spam");
			}
			if(this.spamDetector.detectSpam(link)) {
				errors.state(request, !this.spamDetector.detectSpam(link), "link", "manager.task.form.error.spam");
			}
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
		public Task findOne(final Request<Task> request) {
			assert request != null;

			Task result;
			int id;

			id = request.getModel().getInteger("id");
			result = this.repository.findOneTaskById(id);

			return result;
		}

		@Override
		public void update(final Request<Task> request, final Task entity) {
			assert request != null;
			assert entity != null;

			this.repository.save(entity);
		}
}
