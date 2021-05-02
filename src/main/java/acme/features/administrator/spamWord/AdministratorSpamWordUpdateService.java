package acme.features.administrator.spamWord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spamWord.SpamWord;
import acme.features.spamFilter.AdministratorSpamWordRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;
@Service
public class AdministratorSpamWordUpdateService implements AbstractUpdateService<Administrator, SpamWord> {

	@Autowired
	protected AdministratorSpamWordRepository repository;
	
	@Override
	public boolean authorise(final Request<SpamWord> request) {
		// TODO Auto-generated method stub
		final boolean result;
		SpamWord spam_word;
		final int spam_word_Id;

		spam_word_Id=request.getModel().getInteger("id");
		spam_word=this.repository.findOneSpamWordById(spam_word_Id);
		result = spam_word !=null;
		return result;
		
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

		request.unbind(entity, model, "word","threshold");
		model.setAttribute("readonly", false);
		model.setAttribute("id", entity.getId());
		
	}

	@Override
	public SpamWord findOne(final Request<SpamWord> request) {
		// TODO Auto-generated method stub
		final SpamWord spam_word;
		int spamWordId;
		
		spamWordId=request.getModel().getInteger("id");
		spam_word=this.repository.findOneSpamWordById(spamWordId);
		
		return spam_word;
	}

	@Override
	public void validate(final Request<SpamWord> request, final SpamWord entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Collection<SpamWord> listAll= this.repository.findMany();
		errors.state(request, listAll.contains(entity.getWord()), "word", "administrator.spamWord.form.error.duplicatedName");

		
	}

	@Override
	public void update(final Request<SpamWord> request, final SpamWord entity) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

}
