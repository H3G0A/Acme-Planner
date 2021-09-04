package acme.features.administrator.workplanDashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.WorkplanDashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorWorkplanDashboardShowService implements AbstractShowService<Administrator, WorkplanDashboard> {

	@Autowired
	protected AdministratorWorkPlanDashboardRepository repository;
	
	@Override
	public boolean authorise(final Request<WorkplanDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<WorkplanDashboard> request, final WorkplanDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "numberOfWorkPlan", "numberOfPublicWorkPlan", "numberOfPrivateWorkPlan","numberOfFinishedWorkPlan",
			"numberOfNonFinishedWorkPlan","averageNumberOfWorkPlanPeriod","deviationOfWorkPlanPeriod","minWorkPlanPeriod",
			"maxWorkPlanPeriod","averageNumberOfWorkPlanWorkload","deviationOfWorkPlanWorkload","minWorkPlanWorkload",
			"maxWorkPlanWorkload");
		
	}

	@Override
	public WorkplanDashboard findOne(final Request<WorkplanDashboard> request) {
		assert request != null;
		
		WorkplanDashboard result;
		final Integer numberOfWorkPlan;
		final Integer numberOfPublicWorkPlan;
		final Integer numberOfPrivateWorkPlan;
		final Integer numberOfFinishedWorkPlan;
		final Integer numberOfNonFinishedWorkPlan;
		final Double averageNumberOfWorkPlanPeriod;
		final Double deviationOfWorkPlanPeriod;
		final Double minWorkPlanPeriod;
		final Double maxWorkPlanPeriod;
		final Double averageNumberOfWorkPlanWorkload;
		final Double deviationOfWorkPlanWorkload;
		final Double minWorkPlanWorkload;
		final Double maxWorkPlanWorkload;
		
		numberOfWorkPlan = this.repository.numberOfWorkPlan();
		numberOfPublicWorkPlan = this.repository.numberOfPublicWorkPlan();
		numberOfPrivateWorkPlan = this.repository.numberOfPrivateWorkPlan();
		numberOfFinishedWorkPlan = this.repository.numberOfFinishedWorkPlan();
		numberOfNonFinishedWorkPlan = this.repository.numberOfNonFinishedWorkPlan();
		averageNumberOfWorkPlanPeriod = this.repository.averageNumberOfWorkPlanPeriod();
		deviationOfWorkPlanPeriod = this.repository.deviationOfWorkPlanPeriod();
		minWorkPlanPeriod = this.repository.minWorkPlanPeriod();
		maxWorkPlanPeriod = this.repository.maxWorkPlanPeriod();
		averageNumberOfWorkPlanWorkload = this.repository.averageNumberOfWorkPlanWorkload();
		deviationOfWorkPlanWorkload = this.repository.deviationOfWorkPlanWorkload();
		minWorkPlanWorkload = this.repository.minWorkPlanWorkload();
		maxWorkPlanWorkload = this.repository.maxWorkPlanWorkload();
		
		result = new WorkplanDashboard();
		result.setNumberOfWorkPlan(numberOfWorkPlan);
		result.setNumberOfPublicWorkPlan(numberOfPublicWorkPlan);
		result.setNumberOfPrivateWorkPlan(numberOfPrivateWorkPlan);
		result.setNumberOfFinishedWorkPlan(numberOfFinishedWorkPlan);
		result.setNumberOfNonFinishedWorkPlan(numberOfNonFinishedWorkPlan);
		result.setAverageNumberOfWorkPlanPeriod(averageNumberOfWorkPlanPeriod);
		result.setDeviationOfWorkPlanPeriod(deviationOfWorkPlanPeriod);
		result.setMinWorkPlanPeriod(minWorkPlanPeriod);
		result.setMaxWorkPlanPeriod(maxWorkPlanPeriod);
		result.setAverageNumberOfWorkPlanWorkload(averageNumberOfWorkPlanWorkload);
		result.setDeviationOfWorkPlanWorkload(deviationOfWorkPlanWorkload);
		result.setMinWorkPlanWorkload(minWorkPlanWorkload);
		result.setMaxWorkPlanWorkload(maxWorkPlanWorkload);
		
		return result;
	}	

}
