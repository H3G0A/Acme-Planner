package acme.features.administrator.spamWord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spamWord.SpamWord;
import acme.features.spamFilter.AdministratorSpamWordRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;
@Service
public class AdministratorSpamWordShowService implements AbstractShowService<Administrator, SpamWord> {

	@Autowired
	protected AdministratorSpamWordRepository repository;
	
	@Override
	public boolean authorise(final Request<SpamWord> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<SpamWord> request, final SpamWord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("id", entity.getId());
		request.unbind(entity, model, "id","word","threshold");

		
	}

	@Override
	public SpamWord findOne(final Request<SpamWord> request) {
		assert request != null;
		SpamWord result;
		int id;
		id= request.getModel().getInteger("id");
		result = this.repository.findOneSpamWordById(id);
		return result;
	}

}
