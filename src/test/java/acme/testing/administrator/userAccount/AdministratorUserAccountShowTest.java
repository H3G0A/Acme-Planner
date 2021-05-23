package acme.testing.administrator.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorUserAccountShowTest extends AcmePlannerTest {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/userAccount/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void showPositive(final int recordIndex, final String username ,final String name,final String surname,
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
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/userAccount/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void showNegative(final int recordIndex, final String palabra) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "User accounts");
		
		super.clickOnListingRecord(0);
				
		final String[] url = super.driver.getCurrentUrl().split("=");
		
		super.signOut();
					
		super.driver.get(super.baseUrl + "/administrator/user-account/show?id="+url[1]);
		
		super.checkPanicExists();
	}

}
