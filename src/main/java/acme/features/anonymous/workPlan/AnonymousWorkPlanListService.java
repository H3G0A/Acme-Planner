package acme.features.anonymous.workPlan;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.workPlan.WorkPlan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;
@Service
public class AnonymousWorkPlanListService implements AbstractListService<Anonymous, WorkPlan> {
	
	@Autowired
	protected AnonymousWorkPlanRepository repository;

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
		
		
		if(entity.getIsPublic().equals(true)) {
			model.setAttribute("public", "Public");
			
		}else {
			model.setAttribute("public", "Private");
		}
		
		request.unbind(entity, model, "start","end","isPublic","tasks");
		
	}

	@Override
	public Collection<WorkPlan> findMany(final Request<WorkPlan> request) {
		assert request != null;	
	    final Collection<WorkPlan> result;
	    result = this.repository.findPublicWorkPlans().stream().filter(x->x.getEnd().after(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)))).collect(Collectors.toList());

	    
		return result;
	}
	
	

}
