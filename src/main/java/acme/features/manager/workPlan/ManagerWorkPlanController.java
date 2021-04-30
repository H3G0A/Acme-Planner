package acme.features.manager.workPlan;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Manager;
import acme.entities.workPlan.WorkPlan;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/manager/workPlan")
public class ManagerWorkPlanController extends AbstractController<Manager, WorkPlan> {
	

}
