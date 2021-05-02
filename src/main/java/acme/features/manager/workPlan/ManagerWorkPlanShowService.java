package acme.features.manager.workPlan;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.workPlan.WorkPlan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class ManagerWorkPlanShowService implements AbstractShowService<Manager, WorkPlan>{
	
	@Autowired
	protected ManagerWorkPlanRepository repository;

	@Override
	public boolean authorise(final Request<WorkPlan> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<WorkPlan> request, final WorkPlan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final int workplanId = request.getModel().getInteger("id");
		final WorkPlan workplan = this.repository.findOneWorkPlanById(workplanId);
		final Manager manager = workplan.getManager();
		//You can publish a workplan if you have created it and all tasks inside are public
		
		List<Task>taskList = this.repository.findTasksAvailable(manager.getId(), workplanId).stream()
				.filter(x->!workplan.getTasks().contains(x))
				.collect(Collectors.toList());
		
		if(workplan.getIsPublic())//If workplan is public, only public tasks can be added
			taskList= taskList.stream().filter(x->x.getIsPublic()).collect(Collectors.toList());
			
		model.setAttribute("tasksEneabled", taskList);
		request.unbind(entity, model, "title","start","end","description","isPublic","tasks","workload","executionPeriod");
		
	}

	@Override
	public WorkPlan findOne(final Request<WorkPlan> request) {
		assert request != null;
		WorkPlan result;
		int id;
		id= request.getModel().getInteger("id");
		result = this.repository.findOneWorkPlanById(id);
		return result;
	}

}
