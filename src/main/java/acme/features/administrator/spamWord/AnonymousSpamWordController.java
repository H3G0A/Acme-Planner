package acme.features.administrator.spamWord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;
import acme.framework.entities.SpamWord;

@Controller
@RequestMapping("/administrator/spam-word/")
public class AnonymousSpamWordController extends AbstractController<Anonymous, SpamWord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnonymousSpamWordListService listService;
	
	@Autowired
	private AnonymousSpamWordCreateService createService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
