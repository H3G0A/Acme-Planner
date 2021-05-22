package acme.testing.administrator.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorUserAccountShowTest extends AcmePlannerTest {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/userAccount/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void show(final int recordIndex, final String username ,final String name,final String surname,
		final String email, final String role, final String status) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "User accounts");
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("username", username);
		
		super.checkInputBoxHasValue("identity.name", name);
		
		super.checkInputBoxHasValue("identity.surname", surname);
		
		super.checkInputBoxHasValue("identity.email", email);
		
		super.checkInputBoxHasValue("roleList", role);
		
		super.checkInputBoxHasValue("status", status);
		
		super.signOut();		
		
	}

}
