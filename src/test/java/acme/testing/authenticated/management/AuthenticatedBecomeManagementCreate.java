package acme.testing.authenticated.management;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedBecomeManagementCreate extends AcmePlannerTest {
	
	// This test case checks the correct update in the role of the user, beign after that a manager. After updating this value, 
	// it is expected to return to the initial view of the application, with the extra option Manager in the menu.
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/dashboardTask/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void becomeAManagement() {
		
		super.signIn("administrator", "administrator");
				
		super.clickOnMenu("Account", "Become a manager");
		
		super.clickOnSubmitButton("Register");
		
		super.clickOnMenu("Manager", "Tasks");
		
		super.signOut();
	}

}
