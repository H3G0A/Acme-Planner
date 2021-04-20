package acme.features.administrator.spamWord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.entities.SpamWord;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousSpamWordListService implements AbstractListService<Anonymous, SpamWord> {

		// Internal state ---------------------------------------------------------

		@Autowired
		protected AnonymousSpamWordRepository repository;

		// AbstractListService<Administrator, SpamWord> interface -----

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

			request.unbind(entity, model, "word");
		}

		@Override
		public Collection<SpamWord> findMany(final Request<SpamWord> request) {
			assert request != null;

			Collection<SpamWord> result;

			result = this.repository.findMany();

			return result;
		}

	

}
