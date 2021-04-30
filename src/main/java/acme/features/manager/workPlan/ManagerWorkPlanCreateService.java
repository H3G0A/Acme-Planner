package acme.features.manager.workPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.workPlan.WorkPlan;
import acme.features.spamFilter.AnonymousSpamDetectorService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerWorkPlanCreateService implements AbstractCreateService<Manager, WorkPlan>{

	@Autowired
	protected ManagerWorkPlanRepository managerWorkPlanRepository;
	
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
	}

	@Override
	public WorkPlan instantiate(final Request<WorkPlan> request) {
		assert request != null;
		
		WorkPlan result;
		Manager manager;

		manager = this.managerWorkPlanRepository.findOneManagerById(request.getPrincipal().getActiveRoleId());
		result = new WorkPlan();
		result.setManager(manager);
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
		
	}

	@Override
	public void create(final Request<WorkPlan> request, final WorkPlan entity) {
		assert request != null;
		assert entity != null;
		
		this.managerWorkPlanRepository.save(entity);
		
	}

}
