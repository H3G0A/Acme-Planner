package acme.features.management.workPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Management;
import acme.entities.workPlan.WorkPlan;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagementWorkPlanPublishService implements AbstractUpdateService<Management, WorkPlan>{

	@Autowired
	private ManagementWorkPlanRepository repository;
	
	@Override
	public boolean authorise(final Request<WorkPlan> request) {
		assert request != null;
		final boolean result;
		WorkPlan workplan;
		int workplanId;
		Management management;
		Principal principal;
		
		workplanId=request.getModel().getInteger("id");
		workplan=this.repository.findOneWorkPlanById(workplanId);
		management = workplan.getManagement();
		principal = request.getPrincipal();
		
		result = (management.getUserAccount().getId() == principal.getAccountId());
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

        request.unbind(entity, model,  "isPublic", "start", "end", "tasks","title","executionPeriod","workload");	
	}

	@Override
	public WorkPlan findOne(final Request<WorkPlan> request) {
		final int id = request.getModel().getInteger("id");
		return this.repository.findOneWorkPlanById(id);

	}

	@Override
	public void validate(final Request<WorkPlan> request, final WorkPlan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final boolean allTaskArePublic = entity.getTasks().stream().filter(x->x.getIsPublic().equals(false)).count()==0; 
		errors.state(request, allTaskArePublic, "title", "manager.workplan.form.error.all-tasks-must-be-public");
		final boolean workPlanPublic = !entity.getIsPublic();
		errors.state(request, workPlanPublic, "title", "manager.workplan.form.error.workPlanPublic");
		
	}

	@Override
	public void update(final Request<WorkPlan> request, final WorkPlan entity) {
		final WorkPlan wp = this.repository.findOneWorkPlanById(entity.getId());
		wp.setIsPublic(true);
		this.repository.save(wp);
	}

}
