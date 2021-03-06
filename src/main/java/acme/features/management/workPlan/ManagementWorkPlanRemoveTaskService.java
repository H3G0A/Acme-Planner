package acme.features.management.workPlan;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Management;
import acme.entities.tasks.Task;
import acme.entities.workPlan.WorkPlan;
import acme.features.anonymous.task.AnonymousTaskRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagementWorkPlanRemoveTaskService implements AbstractUpdateService<Management, WorkPlan>{
	@Autowired
	private ManagementWorkPlanRepository repository;
	
	@Autowired
	AnonymousTaskRepository taskRepository;
		
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
		result = management.getUserAccount().getId() == principal.getAccountId();
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
		
	    request.unbind(entity, model,  "isPublic", "start","description", "end", "tasks","title","workPlanPeriod","workPlanWorkload");
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
	
	}

	@Override
	public void update(final Request<WorkPlan> request, final WorkPlan entity) {
		final WorkPlan wp = this.repository.findOneWorkPlanById(entity.getId());
		final Integer idTask = request.getModel().getInteger("taskId");
		final Collection<Task> ls = wp.getTasks().stream().filter(x->x.getId()!=idTask).collect(Collectors.toList());
		wp.setTasks(ls);
		wp.setWorkPlanWorkload();
		
		this.repository.save(wp);
		
	}

}
