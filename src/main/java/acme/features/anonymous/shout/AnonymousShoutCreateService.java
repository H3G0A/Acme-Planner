/*
 * AnonymousShoutCreateService.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.anonymous.shout;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.Shout;
import acme.entities.xxx.XXX;
import acme.features.anonymous.xxx.AnonymousXXXRepository;
import acme.features.spamFilter.AnonymousSpamDetectorService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousShoutCreateService implements AbstractCreateService<Anonymous, Shout> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnonymousShoutRepository repository;
	
	@Autowired
	protected AnonymousXXXRepository xrepository;

	@Autowired
	protected AnonymousSpamDetectorService spamDetector;
	// AbstractCreateService<Administrator, Shout> interface --------------

	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "info","xxx.amount","xxx.flag","xxx.dating");
	}

	@Override
	public Shout instantiate(final Request<Shout> request) {
		assert request != null;

		Shout result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new Shout();
		result.setAuthor("John Doe");
		result.setText("Lorem ipsum!");
		result.setMoment(moment);
		result.setInfo("http://example.org");

		return result;
	}

	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final String text = request.getModel().getAttribute("text").toString();
		final String author = request.getModel().getAttribute("author").toString();
		final String amount = request.getModel().getAttribute("xxx.amount").toString();
		
		if(amount.matches("[€,$][0-9]+[.,]+([0-9]{0,3})?")){
			errors.state(request, !amount.matches("[€,$][0-9]+[.,]+([0-9]{0,3})?)"), "xxx.amount", "anonymous.shout.form.error.amount");
		}
//		if(this.spamDetector.detectSpam(text)) {
//			errors.state(request, !this.spamDetector.detectSpam(text), "text", "anonymous.shout.form.error.spam");
//		}
//		if(this.spamDetector.detectSpam(author)) {
//			errors.state(request, !this.spamDetector.detectSpam(author), "author", "anonymous.shout.form.error.spam");
//		}

	}
	
	public String splitAmount(final String amount) {
		String amount_splitted = null;
		if(amount.contains("$")) {
			amount_splitted=amount.split("$")[1];
		}
		if(amount.contains("€")) {
			amount_splitted=amount.split("€")[1];
		}
		return amount_splitted;
	}

	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		
		XXX xx;
		
		xx= new XXX();

		moment = new Date(System.currentTimeMillis() - 1);
		xx.setMoment(moment);
		xx.setAmount(Double.valueOf(this.splitAmount(entity.getXxx().getAmount().toString())));
		xx.setFlag(entity.getXxx().getFlag());
		xx.setDating(entity.getXxx().getDating());
		this.xrepository.save(xx);
		entity.setMoment(moment);
		entity.setXxx(xx);
		this.repository.save(entity);
	}

}
