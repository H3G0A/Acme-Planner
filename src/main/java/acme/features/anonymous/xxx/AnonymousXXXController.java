//package acme.features.anonymous.xxx;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import acme.entities.shouts.Shout;
//import acme.framework.components.BasicCommand;
//import acme.framework.controllers.AbstractController;
//import acme.framework.entities.Anonymous;
//
//@Controller
//@RequestMapping("/anonymous/xxx/")
//public class AnonymousXXXController extends AbstractController<Anonymous, Shout> {
//
//	// Internal state ---------------------------------------------------------
//
//	
//	@Autowired
//	protected AnonymousXXXCreateService createService;
//
//	// Constructors -----------------------------------------------------------
//
//	@PostConstruct
//	protected void initialise() {
//		super.addBasicCommand(BasicCommand.CREATE, this.createService);
//	}
//
//}
