package acme.features.administrator.taskDashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.TaskDashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorTaskDashboardShowService implements AbstractShowService<Administrator, TaskDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorTaskDashboardRepository repository;

	// AbstractShowService<Administrator, Dashboard> interface ----------------


	@Override
	public boolean authorise(final Request<TaskDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<TaskDashboard> request, final TaskDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, //
			"numberOfPublicTask", "numberOfPrivateTask", // 
			"numberOfFinishedTask", "numberOfNotFinishedTask", //
			"averageNumberOfWorkload", "averageNumberOfExecutionPeriods",
			"minWorkload","maxWorkload",
			"minExecutionPeriod","maxExecutionPeriod",
			"deviationWorkload","deviationExecutionPeriod");
	}

	@Override
	public TaskDashboard findOne(final Request<TaskDashboard> request) {
		assert request != null;

		TaskDashboard result;
		final Integer						numberOfPublicTask;
		final Integer						numberOfPrivateTask;
		final Integer						numberOfFinishedTask;
		final Integer						numberOfNotFinishedTask;
		final Double						averageNumberOfWorkload;
		final Double						averageNumberOfExecutionPeriods;
		final Integer						minWorkload;
		final Integer						maxWorkload;
		final Double						minExecutionPeriod;
		final Double						maxExecutionPeriod;
		final Double						deviationWorkload;
		final Double						deviationExecutionPeriod;
		
		numberOfPublicTask = this.repository.numberOfPublicTask();
		numberOfPrivateTask = this.repository.numberOfPrivateTask();
		numberOfFinishedTask = this.repository.numberOfFinishedTask();
		numberOfNotFinishedTask = this.repository.numberOfNotFinishedTask();
		averageNumberOfWorkload = this.repository.averageNumberOfWorkload();
		averageNumberOfExecutionPeriods = this.repository.averageNumberOfExecutionPeriods();
		minWorkload = this.repository.minWorkload();
		maxWorkload = this.repository.maxWorkload();
		minExecutionPeriod = this.repository.minExecutionPeriod();
		maxExecutionPeriod = this.repository.maxExecutionPeriod();
		deviationWorkload = this.repository.deviationWorkload();
		deviationExecutionPeriod = this.repository.deviationExecutionPeriod();

			
		

		result = new TaskDashboard();
		result.setNumberOfPublicTask(numberOfPublicTask);
		result.setNumberOfPrivateTask(numberOfPrivateTask);
		result.setNumberOfFinishedTask(numberOfFinishedTask);
		result.setNumberOfNotFinishedTask(numberOfNotFinishedTask);
		result.setAverageNumberOfWorkload(averageNumberOfWorkload);
		result.setAverageNumberOfExecutionPeriods(averageNumberOfExecutionPeriods);
		result.setMinWorkload(minWorkload);
		result.setMinExecutionPeriod(minExecutionPeriod);
		result.setMaxWorkload(maxWorkload);
		result.setMaxExecutionPeriod(maxExecutionPeriod);
		result.setDeviationWorkload(deviationWorkload);
		result.setDeviationExecutionPeriod(deviationExecutionPeriod);
		

		return result;
	}

}