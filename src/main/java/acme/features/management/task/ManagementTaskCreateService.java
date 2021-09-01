package acme.features.management.task;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Management;
import acme.entities.tasks.Task;
import acme.features.spamFilter.AnonymousSpamDetectorService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagementTaskCreateService implements AbstractCreateService<Management, Task>{

	// Internal state ---------------------------------------------------------

		@Autowired
		protected ManagementTaskRepository repository;
		
		@Autowired
		protected AnonymousSpamDetectorService spamDetector;
		

		// AbstractCreateService<management, task> interface -------------------------


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
			
			if (!errors.hasErrors("start") && !errors.hasErrors("end")) {
				
				errors.state(request, entity.getStart().before(entity.getEnd()), "end", "manager.task.form.error.endBeforeStart");
				errors.state(request, entity.getStart().toInstant().isBefore(LocalDateTime.now().toInstant(ZoneOffset.UTC)), "Start", "manager.task.form.error.Future");
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
		public Task instantiate(final Request<Task> request) {
			assert request != null;

			Task result;
			Management management;

			management = this.repository.findOneManagementById(request.getPrincipal().getActiveRoleId());
			result = new Task();
			result.setManagement(management);

			return result;
		}

		@Override
		public void create(final Request<Task> request, final Task entity) {
			assert request != null;
			assert entity != null;
			
			entity.setExecutionPeriod();
			this.repository.save(entity);
		}
}
