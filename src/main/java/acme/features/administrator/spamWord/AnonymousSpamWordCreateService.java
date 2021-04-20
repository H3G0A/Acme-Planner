package acme.features.administrator.spamWord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.entities.SpamWord;
import acme.framework.services.AbstractCreateService;
@Service
public class AnonymousSpamWordCreateService implements AbstractCreateService<Anonymous, SpamWord> {

	@Autowired
	protected AnonymousSpamWordRepository repository;
	
	@Override
	public boolean authorise(final Request<SpamWord> request) {
		// TODO Auto-generated method stub
		assert request != null;
		
		return false;
	}

	@Override
	public void bind(final Request<SpamWord> request, final SpamWord entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);
		
	}

	@Override
	public void unbind(final Request<SpamWord> request, final SpamWord entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "word");
		
	}

	@Override
	public SpamWord instantiate(final Request<SpamWord> request) {
		// TODO Auto-generated method stub
		assert request != null;
		SpamWord result;
		
		result= new SpamWord();
		result.setWord("fuck");
		
		return result;
	}

	@Override
	public void validate(final Request<SpamWord> request, final SpamWord entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void create(final Request<SpamWord> request, final SpamWord entity) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		
		entity.setWord("nigga");
		this.repository.save(entity);
		
	}

}
