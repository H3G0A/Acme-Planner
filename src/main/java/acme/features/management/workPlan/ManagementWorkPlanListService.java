package acme.features.management.workPlan;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Management;
import acme.entities.workPlan.WorkPlan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class ManagementWorkPlanListService implements AbstractListService<Management, WorkPlan>{
	
	@Autowired
	protected ManagementWorkPlanRepository managementWorkPlanRepository;

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
		
		request.unbind(entity, model,"title","start","end","workload");
		
	}

	@Override
	public Collection<WorkPlan> findMany(final Request<WorkPlan> request) {
		assert request != null;	
	    Collection<WorkPlan> result;
	    result = this.managementWorkPlanRepository.findManyByManagementId(request.getPrincipal().getActiveRoleId());
		
		return result;
	}

}
