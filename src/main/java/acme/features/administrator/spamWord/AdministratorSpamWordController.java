package acme.features.administrator.spamWord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.spamWord.SpamWord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/spam-word/")
public class AdministratorSpamWordController extends AbstractController<Administrator, SpamWord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorSpamWordListService listService;
	
	@Autowired
	protected AdministratorSpamWordShowService showService;
	
	@Autowired
	protected AdministratorSpamWordCreateService createService;
	
	@Autowired
	protected AdministratorSpamWordUpdateService updateService;
	
	@Autowired
	protected AdministratorSpamWordDeleteService deleteService;
	

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);

	}

}
