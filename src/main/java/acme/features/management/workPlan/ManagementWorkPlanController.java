package acme.features.management.workPlan;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.roles.Management;
import acme.entities.workPlan.WorkPlan;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/management/work-plan/")
public class ManagementWorkPlanController extends AbstractController<Management, WorkPlan> {
	@Autowired
	protected ManagementWorkPlanShowService		showService;

	@Autowired
	protected ManagementWorkPlanCreateService		createService;

	@Autowired
	protected ManagementWorkPlanUpdateService		updateService;

	@Autowired
	protected ManagementWorkPlanDeleteService		deleteService;

	@Autowired
	protected ManagementWorkPlanListService		listAllService;
	
	@Autowired
	protected ManagementWorkPlanAddTaskService		addTaskService;
	
	@Autowired
	protected ManagementWorkPlanRemoveTaskService		removeTaskService;
	
	@Autowired
	protected ManagementWorkPlanPublishService		publishService;


	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.LIST, this.listAllService);
		super.addCustomCommand(CustomCommand.ADD_TASK, BasicCommand.UPDATE, this.addTaskService);
		super.addCustomCommand(CustomCommand.REMOVE_TASK, BasicCommand.UPDATE, this.removeTaskService);
		super.addCustomCommand(CustomCommand.PUBLISH, BasicCommand.UPDATE, this.publishService);

	}

}
