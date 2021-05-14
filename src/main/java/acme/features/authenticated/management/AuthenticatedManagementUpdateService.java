package acme.features.authenticated.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Management;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedManagementUpdateService implements AbstractUpdateService<Authenticated, Management>{

	// Internal state ---------------------------------------------------------

		@Autowired
		protected AuthenticatedManagementRepository repository;

		// AbstractUpdateService<Authenticated, Consumer> interface -----------------


		@Override
		public boolean authorise(final Request<Management> request) {
			assert request != null;

			return true;
		}

		@Override
		public void validate(final Request<Management> request, final Management entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
		}

		@Override
		public void bind(final Request<Management> request, final Management entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			request.bind(entity, errors);
		}

		@Override
		public void unbind(final Request<Management> request, final Management entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model);
		}

		@Override
		public Management findOne(final Request<Management> request) {
			assert request != null;

			Management result;
			Principal principal;
			int userAccountId;

			principal = request.getPrincipal();
			userAccountId = principal.getAccountId();

			result = this.repository.findOneManagementByUserAccountId(userAccountId);

			return result;
		}

		@Override
		public void update(final Request<Management> request, final Management entity) {
			assert request != null;
			assert entity != null;

			this.repository.save(entity);
		}

		@Override
		public void onSuccess(final Request<Management> request, final Response<Management> response) {
			assert request != null;
			assert response != null;

			if (request.isMethod(HttpMethod.POST)) {
				PrincipalHelper.handleUpdate();
			}
		}
}
