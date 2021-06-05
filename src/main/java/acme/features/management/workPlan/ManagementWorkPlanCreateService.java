package acme.features.management.workPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Management;
import acme.entities.workPlan.WorkPlan;
import acme.features.spamFilter.AnonymousSpamDetectorService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagementWorkPlanCreateService implements AbstractCreateService<Management, WorkPlan>{

	@Autowired
	protected ManagementWorkPlanRepository managementWorkPlanRepository;
	
	@Autowired
	protected AnonymousSpamDetectorService spamDetector;
	
	
	@Override
	public boolean authorise(final Request<WorkPlan> request) {
		assert request != null;

		return true;
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
		model.setAttribute("ItsMine", true);

	}

	
	@Override
	public WorkPlan instantiate(final Request<WorkPlan> request) {
		assert request != null;
		
		WorkPlan result;
		Management management;

		management = this.managementWorkPlanRepository.findOneManagementById(request.getPrincipal().getActiveRoleId());
		result = new WorkPlan();
		result.setManagement(management);
		return result;
		
	}

	@Override
	public void validate(final Request<WorkPlan> request, final WorkPlan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("start") && !errors.hasErrors("end")) {
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
		request.getModel().setAttribute("ItsMine", true);
	}

	@Override
	public void create(final Request<WorkPlan> request, final WorkPlan entity) {
		assert request != null;
		assert entity != null;
		
		entity.setWorkPlanPeriod();
		this.managementWorkPlanRepository.save(entity);
				
	}

}
