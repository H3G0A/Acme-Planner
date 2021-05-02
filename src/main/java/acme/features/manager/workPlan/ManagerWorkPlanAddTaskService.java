package acme.features.manager.workPlan;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.workPlan.WorkPlan;
import acme.features.anonymous.task.AnonymousTaskRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerWorkPlanAddTaskService implements AbstractUpdateService<Manager, WorkPlan> {

	@Autowired
	private ManagerWorkPlanRepository repository;
	
	@Autowired
	AnonymousTaskRepository taskRepository;
		
	@Override
	public boolean authorise(final Request<WorkPlan> request) {
		assert request != null;
		final boolean result;
		WorkPlan workplan;
		int workplanId;
		Manager manager;
		Principal principal;
		
		workplanId=request.getModel().getInteger("id");
		workplan=this.repository.findOneWorkPlanById(workplanId);
		manager = workplan.getManager();
		principal = request.getPrincipal();
		
		result = (manager.getUserAccount().getId() == principal.getAccountId());
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
		
	    request.unbind(entity, model,  "isPublic", "start","description", "end", "tasks","title","executionPeriod","workload");
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
		for( final Task t: entity.getTasks()) {
			if(errors.hasErrors("taskSelected")) {
				errors.state(request, t.getIsPublic() == false && request.getModel().getBoolean("isPublic")== true, 
					"isPublic", "manager.workPlan.form.error.taskPublic");
				
				
			}
			
		}
	
	}

	@Override
	public void update(final Request<WorkPlan> request, final WorkPlan entity) {
		final WorkPlan wp = this.repository.findOneWorkPlanById(entity.getId());
		final Task task = (Task) this.taskRepository.findById(request.getModel().getInteger("taskSelected")).orElse(null);
		final Collection<Task> ls = wp.getTasks();
		ls.add(task);
		wp.setTasks(ls);
		wp.setWorkload();
		
		this.repository.save(wp);
		
	}

}
