///*
// * AnonymousShoutCreateService.java
// *
// * Copyright (C) 2012-2021 Rafael Corchuelo.
// *
// * In keeping with the traditional purpose of furthering education and research, it is
// * the policy of the copyright owner to permit non-commercial use and redistribution of
// * this software. It has been tested carefully, but it is not guaranteed for any particular
// * purposes. The copyright owner does not offer any warranties or representations, nor do
// * they accept any liabilities with respect to them.
// */
//
//package acme.features.anonymous.xxx;
//
//import java.time.Instant;
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import acme.entities.xxx.XXX;
//import acme.features.spamFilter.AnonymousSpamDetectorService;
//import acme.framework.components.Errors;
//import acme.framework.components.Model;
//import acme.framework.components.Request;
//import acme.framework.entities.Anonymous;
//import acme.framework.services.AbstractCreateService;
//
//@Service
//public class AnonymousXXXCreateService implements AbstractCreateService<Anonymous, XXX> {
//
//	// Internal state ---------------------------------------------------------
//
//	@Autowired
//	protected AnonymousXXXRepository repository;
//
//	@Autowired
//	protected AnonymousSpamDetectorService spamDetector;
//	// AbstractCreateService<Administrator, Shout> interface --------------
//
//	@Override
//	public boolean authorise(final Request<XXX> request) {
//		assert request != null;
//
//		return true;
//	}
//
//	@Override
//	public void bind(final Request<XXX> request, final XXX entity, final Errors errors) {
//		assert request != null;
//		assert entity != null;
//		assert errors != null;
//
//		request.bind(entity, errors);
//	}
//
//	@Override
//	public void unbind(final Request<XXX> request, final XXX entity, final Model model) {
//		assert request != null;
//		assert entity != null;
//		assert model != null;
//
//		request.unbind(entity, model, "dating", "moment", "amount","flag");
//	}
//
//	@Override
//	public XXX instantiate(final Request<XXX> request) {
//		assert request != null;
//
//		XXX result;
//		Date moment;
//
//		moment = new Date(System.currentTimeMillis() - 1);
//
//		result = new XXX();
//		result.setDating(Date.from(Instant.now()));
//		result.setMoment(moment);
//		result.setFlag(true);
//
//		return result;
//	}
//
//	@Override
//	public void validate(final Request<XXX> request, final XXX entity, final Errors errors) {
//		assert request != null;
//		assert entity != null;
//		assert errors != null;
//		
////		final String text = request.getModel().getAttribute("text").toString();
////		final String author = request.getModel().getAttribute("author").toString();
////		
////		if(this.spamDetector.detectSpam(text)) {
////			errors.state(request, !this.spamDetector.detectSpam(text), "text", "anonymous.shout.form.error.spam");
////		}
////		if(this.spamDetector.detectSpam(author)) {
////			errors.state(request, !this.spamDetector.detectSpam(author), "author", "anonymous.shout.form.error.spam");
////		}
//
//	}
//
//	@Override
//	public void create(final Request<XXX> request, final XXX entity) {
//		assert request != null;
//		assert entity != null;
//
//		Date moment;
//
//		moment = new Date(System.currentTimeMillis() - 1);
//		entity.setMoment(moment);
//		this.repository.save(entity);
//	}
//
//}
