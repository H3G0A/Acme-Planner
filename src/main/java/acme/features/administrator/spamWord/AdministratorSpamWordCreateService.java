package acme.features.administrator.spamWord;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spamWord.SpamWord;
import acme.features.spamFilter.AdministratorSpamWordRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;
@Service
public class AdministratorSpamWordCreateService implements AbstractCreateService<Administrator, SpamWord> {

	@Autowired
	protected AdministratorSpamWordRepository repository;
	
	@Override
	public boolean authorise(final Request<SpamWord> request) {
		// TODO Auto-generated method stub
		assert request != null;
		
		return true;
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
	}

	@Override
	public SpamWord instantiate(final Request<SpamWord> request) {
		// TODO Auto-generated method stub
		assert request != null;
		SpamWord result;
		
		result= new SpamWord();
		
		return result;
	}

	@Override
	public void validate(final Request<SpamWord> request, final SpamWord entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Collection<SpamWord> listAll= this.repository.findMany();
		final List<String> list = new ArrayList<>();
		for(final SpamWord s: listAll) {
			list.add(s.getWord());
		}
		final Boolean repeatedSpamWord =list.contains(entity.getWord());
		errors.state(request, !repeatedSpamWord, "word", "administrator.spamWord.form.error.duplicatedName");

		
		
	}

	@Override
	public void create(final Request<SpamWord> request, final SpamWord entity) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

}
