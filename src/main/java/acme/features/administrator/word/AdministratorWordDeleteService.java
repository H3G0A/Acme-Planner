
package acme.features.administrator.word;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.SpamWord;
import acme.entities.spam.Word;
import acme.features.spamFilter.AnonymousSpamDetectorService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractDeleteService;

@Service
public class AdministratorWordDeleteService implements AbstractDeleteService<Administrator, Word> {

	@Autowired
	protected AdministratorWordRepository repository;

	@Autowired
	protected AnonymousSpamDetectorService	spamService;

	@Override
	public boolean authorise(final Request<Word> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Word> request, final Word entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Word> request, final Word entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "palabra");
	}

	@Override
	public Word findOne(final Request<Word> request) {
		Word word;
		final int id = request.getModel().getInteger("id");
		word = this.repository.findOneWordById(id).orElse(null);
		return word;
	}

	@Override
	public void validate(final Request<Word> request, final Word entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Optional<Word> spam = this.repository.findOneWordById(entity.getId());
		errors.state(request, spam.isPresent(), "palabra", "administrator.word.form.error.notExists");

	}

	@Override
	public void delete(final Request<Word> request, final Word entity) {
		assert request != null;
		assert entity != null;
		final SpamWord spam = this.repository.findSpam();
		final Collection<Word> spamWords = spam.getSpamWords();
		spamWords.remove(entity);
		this.repository.deleteById(entity.getId());
	}

}
