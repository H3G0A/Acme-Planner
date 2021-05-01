package acme.features.manager.workPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.workPlan.WorkPlan;
import acme.features.spamFilter.AnonymousSpamDetectorService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerWorkPlanUpdateService implements AbstractUpdateService<Manager, WorkPlan>{
	
	@Autowired
	protected ManagerWorkPlanRepository repository;
	
	@Autowired
	protected AnonymousSpamDetectorService spamDetector;

	@Override
	public boolean authorise(final Request<WorkPlan> request) {
		boolean result;
		int workPlanId;
		WorkPlan workPlan;
		Manager manager;
		Principal principal;

		workPlanId = request.getModel().getInteger("id");
		workPlan = this.repository.findOneWorkPlanById(workPlanId);
		manager = workPlan.getManager();
		principal = request.getPrincipal();
		result = manager.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<WorkPlan> request, final WorkPlan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
		
	}

	@Override
	public void unbind(final Request<WorkPlan> request, final WorkPlan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title","start","end","description","isPublic");
		
	}

	@Override
	public WorkPlan findOne(final Request<WorkPlan> request) {
		assert request != null;

		WorkPlan result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneWorkPlanById(id);

		return result;
	}

	@Override
	public void validate(final Request<WorkPlan> request, final WorkPlan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("start") && !errors.hasErrors("end")) {
			errors.state(request, entity.getStart().before(entity.getEnd()), "end", "manager.workPlan.form.error.endBeforeStart");
		}
		
		final String title = entity.getTitle();
		final String description = entity.getDescription();
		
		if(this.spamDetector.detectSpam(title)) {
			errors.state(request, !this.spamDetector.detectSpam(title), "title", "manager.workPlan.form.error.spam");
		}
		if(this.spamDetector.detectSpam(description)) {
			errors.state(request, !this.spamDetector.detectSpam(description), "description", "manager.workPlan.form.error.spam");
		}
		
	}

	@Override
	public void update(final Request<WorkPlan> request, final WorkPlan entity) {
		assert request != null;
		assert entity != null;
		
		entity.setExecutionPeriod();
		this.repository.save(entity);
		
	}

}
