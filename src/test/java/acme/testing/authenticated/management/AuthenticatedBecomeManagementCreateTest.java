package acme.testing.authenticated.management;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.AcmePlannerTest;

public class AuthenticatedBecomeManagementCreateTest extends AcmePlannerTest {
	
	// This test case checks the correct update in the role of the user, beign after that a manager. After updating this value, 
	// it is expected to return to the initial view of the application, with the extra option Manager in the menu.
	@Test
	@Order(10)	
	public void becomeAManagement() {
		
		super.signIn("administrator", "administrator");
				
		super.clickOnMenu("Account", "Become a manager");
		
		super.clickOnSubmitButton("Register");
		
		super.clickOnMenu("Manager", "Tasks");
		
		super.signOut();
	}

}
