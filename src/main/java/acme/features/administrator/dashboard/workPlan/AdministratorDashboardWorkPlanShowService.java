package acme.features.administrator.dashboard.workPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.WorkPlanDashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardWorkPlanShowService implements AbstractShowService<Administrator, WorkPlanDashboard> {

	@Autowired
	protected AdministratorDashboardWorkPlanRepository repository;
	
	@Override
	public boolean authorise(final Request<WorkPlanDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<WorkPlanDashboard> request, final WorkPlanDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "numberOfPublicWorkPlan", "numberOfPrivateWorkPlan","numberOfFinishedWorkPlan",
			"numberOfNonFinishedWorkPlan","averageNumberOfWorkPlanPeriod","deviationOfWorkPlanPeriod","minWorkPlanPeriod",
			"maxWorkPlanPeriod","averageNumberOfWorkPlanWorkload","deviationOfWorkPlanWorkload","minWorkPlanWorkload",
			"maxWorkPlanWorkload");
		
	}

	@Override
	public WorkPlanDashboard findOne(final Request<WorkPlanDashboard> request) {
		assert request != null;
		
		WorkPlanDashboard result;
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
		
		result = new WorkPlanDashboard();
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
