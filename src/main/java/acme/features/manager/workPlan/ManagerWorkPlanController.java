package acme.features.manager.workPlan;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Manager;
import acme.entities.workPlan.WorkPlan;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/manager/work-plan/")
public class ManagerWorkPlanController extends AbstractController<Manager, WorkPlan> {
	@Autowired
	protected ManagerWorkPlanShowService		showService;

	@Autowired
	protected ManagerWorkPlanCreateService		createService;

	@Autowired
	protected ManagerWorkPlanUpdateService		updateService;

	@Autowired
	protected ManagerWorkPlanDeleteService		deleteService;

	@Autowired
	protected ManagerWorkPlanListService		listAllService;


	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.LIST, this.listAllService);
	}

}
