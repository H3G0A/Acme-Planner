package acme.features.administrator.dashboard;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository repository;

	// AbstractShowService<Administrator, Dashboard> interface ----------------


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
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
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Dashboard result;
		final Integer						numberOfPublicTask;
		final Integer						numberOfPrivateTask;
		final Integer						numberOfFinishedTask;
		final Integer						numberOfNotFinishedTask;
		final Double						averageNumberOfWorkload;
		final Double						averageNumberOfExecutionPeriods;
		final Integer						minWorkload;
		final Integer						maxWorkload;
		final LocalDateTime						minExecutionPeriod;
		final LocalDateTime						maxExecutionPeriod;
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

			
		

		result = new Dashboard();
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