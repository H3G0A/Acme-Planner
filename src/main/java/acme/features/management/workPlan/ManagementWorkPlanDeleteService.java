package acme.features.management.workPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Management;
import acme.entities.workPlan.WorkPlan;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class ManagementWorkPlanDeleteService implements AbstractDeleteService<Management, WorkPlan>{
	// Internal state ---------------------------------------------------------

			@Autowired
			protected ManagementWorkPlanRepository repository;

			// AbstractDeleteService<Employer, Job> interface -------------------------


			@Override
			public boolean authorise(final Request<WorkPlan> request) {
				boolean result;
				int workPlanId;
				WorkPlan workPlan;
				Management management;
				Principal principal;

				workPlanId = request.getModel().getInteger("id");
				workPlan = this.repository.findOneWorkPlanById(workPlanId);
				management = workPlan.getManagement();
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

				request.unbind(entity, model, "title","start","end","description","workPlanWorkload","workPlanPeriod","isPublic");
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
			}

			@Override
			public void delete(final Request<WorkPlan> request, final WorkPlan entity) {
				assert request != null;
				assert entity != null;

				this.repository.delete(entity);
			}

}
