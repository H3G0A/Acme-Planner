package acme.features.management.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Management;
import acme.entities.tasks.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/management/task/")
public class ManagementTaskController extends AbstractController<Management, Task>{

	// Internal state ---------------------------------------------------------

		@Autowired
		protected ManagementTaskShowService		showService;

		@Autowired
		protected ManagementTaskCreateService		createService;

		@Autowired
		protected ManagementTaskUpdateService		updateService;

		@Autowired
		protected ManagementTaskDeleteService		deleteService;

		@Autowired
		protected ManagementTaskListService		listAllService;


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
