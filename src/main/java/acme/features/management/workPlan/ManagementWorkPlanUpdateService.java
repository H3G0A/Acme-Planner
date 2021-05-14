package acme.features.management.workPlan;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Management;
import acme.entities.tasks.Task;
import acme.entities.workPlan.WorkPlan;
import acme.features.spamFilter.AnonymousSpamDetectorService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagementWorkPlanUpdateService implements AbstractUpdateService<Management, WorkPlan>{
	
	@Autowired
	protected ManagementWorkPlanRepository repository;
	
	@Autowired
	protected AnonymousSpamDetectorService spamDetector;

	@Override
	public boolean authorise(final Request<WorkPlan> request) {
		final boolean result;
		final int workPlanId;
		final WorkPlan workPlan;
		final Management management;
		final Principal principal;

		workPlanId=request.getModel().getInteger("id");
		workPlan=this.repository.findOneWorkPlanById(workPlanId);
		management = workPlan.getManagement();
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
		final int workplanId = request.getModel().getInteger("id");
		final WorkPlan workplan = this.repository.findOneWorkPlanById(workplanId);
		final Management management = workplan.getManagement();
		final Boolean canPublish= workplan.getTasks().stream().filter(x-> x.getIsPublic().equals(false)).count() == 0 && !workplan.getIsPublic();
		
		List<Task>taskList = this.repository.findTasksAvailable(management.getId(), workplanId).stream().filter(x->!workplan.getTasks().contains(x)).collect(Collectors.toList());//cambiar publicas por todas
		if(workplan.getIsPublic())//If workplan is public, only public tasks can be added
			taskList= taskList.stream().filter(x->x.getIsPublic()).collect(Collectors.toList());
		model.setAttribute("canPublish", canPublish);
        model.setAttribute("tasks", workplan.getTasks());
        model.setAttribute("tasksEneabled", taskList);
		request.unbind(entity, model,  "isPublic", "start", "end", "tasks","title","executionPeriod","workload");		
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
		
		if(entity.getIsPublic()) {
			if(this.spamDetector.detectSpam(title)) {
				errors.state(request, !this.spamDetector.detectSpam(title), "title", "manager.workPlan.form.error.spam");
			}
			if(this.spamDetector.detectSpam(description)) {
				errors.state(request, !this.spamDetector.detectSpam(description), "description", "manager.workPlan.form.error.spam");
			}
			
		}
		for(final Task t: entity.getTasks()) {
			if(errors.hasErrors("tasks")) {
				errors.state(request, t.getIsPublic() == false && request.getModel().getBoolean("isPublic")== true, 
					"isPublic", "manager.workPlan.form.error.taskPublication");
			}
		}
		
		
	}

	@Override
	public void update(final Request<WorkPlan> request, final WorkPlan entity) {
		assert request != null;
		assert entity != null;
		
		final WorkPlan wp = this.repository.findOneWorkPlanById(entity.getId());
		wp.setEnd(entity.getEnd());
		wp.setStart(entity.getStart());
		wp.setTitle(entity.getTitle());
		wp.setIsPublic(entity.getIsPublic());
		wp.setDescription(entity.getDescription());
		wp.setExecutionPeriod();
		this.repository.save(wp);
		
	}

}
