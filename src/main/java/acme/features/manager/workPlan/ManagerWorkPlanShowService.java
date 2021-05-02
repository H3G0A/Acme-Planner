package acme.features.manager.workPlan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
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
		final Boolean canPublish= workplan.getTasks().stream().filter(x-> x.getIsPublic().equals(false)).count() == 0 && !workplan.getIsPublic();
		//You can publish a workplan if you have created it and all tasks inside are public
		
		List<Task>taskList = this.repository.findTasksAvailable(manager.getId(), workplanId).stream()
				.filter(x->!workplan.getTasks().contains(x))
				.collect(Collectors.toList());
		
		if(workplan.getIsPublic())//If workplan is public, only public tasks can be added
			taskList= taskList.stream().filter(x->x.getIsPublic()).collect(Collectors.toList());
		
		if(!workplan.getTasks().isEmpty()) {
			final List<Date> period = this.suggestedPeriod(workplan.getTasks());
			model.setAttribute("suggestedStart", period.get(0));
			model.setAttribute("suggestedEnd", period.get(1));
		}
		model.setAttribute("canPublish", canPublish);	
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
	
	private List<Date> suggestedPeriod(final Collection<Task> tasksC){
		final List<Task> tasks = new ArrayList<>(tasksC);
		final List<Date> period = new ArrayList<>();
		final Date first = this.getFirstTask(tasks);
		final Date last = this.getLastTask(tasks);
		final Calendar c = Calendar.getInstance();
		
		c.setTime(first);
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR_OF_DAY, 8);
		c.set(Calendar.MINUTE, 30);
		period.add(c.getTime());
		
		c.setTime(last);
		c.add(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 17);
		c.set(Calendar.MINUTE, 0);
		period.add(c.getTime());
		
		return period;
	}
	
	private Date getFirstTask(final List<Task> tasks) {
		Date res = tasks.get(0).getStart();
		for(int i = 1; i<tasks.size(); i++) {
			final Date d = tasks.get(i).getStart();
			if(d.before(res))
				res = d;
		}
		return res;
	}
	
	private Date getLastTask(final List<Task> tasks) {
		Date res = tasks.get(0).getEnd();
		for(int i = 1; i<tasks.size(); i++) {
			final Date d = tasks.get(i).getEnd();
			if(d.after(res))
				res = d;
		}
		return res;
	}

}
