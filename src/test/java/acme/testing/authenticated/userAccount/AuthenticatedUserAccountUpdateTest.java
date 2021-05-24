package acme.testing.authenticated.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedUserAccountUpdateTest extends AcmePlannerTest{
	
	// This test case checks the correct update the details of an account. After updating these values, 
	// it is expected to return to the initial view of the application
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/userAccount/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void updatePositive(final String username, final String password, final String name, final String surname, final String email) {	
		super.signUp(username, password, name, surname, email);

		super.clickOnMenu("Sign in", null);
		
		super.signIn(username, password);
		
		super.clickOnMenu("Account", "General data");
		

		super.fillInputBoxIn("identity.name", name+"X");
		super.fillInputBoxIn("identity.surname", surname+"X");
		super.fillInputBoxIn("identity.email", email+"X");	
		
		super.clickOnSubmitButton("Update");
		
		super.clickOnMenu("Account", "General data");
		
		super.checkInputBoxHasValue("identity.name", name+"X");
		super.checkInputBoxHasValue("identity.surname", surname+"X");
		super.checkInputBoxHasValue("identity.email", email+"X");
	
		super.signOut();
	}
	
	// This test case checks for errors after inserting wrong data of account details, such as blank value 
	// , displaying the corresponding error message
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/userAccount/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void updateNegative(final String username,final String password, final String name, final String surname, final String email) {		

		super.clickOnMenu("Sign in", null);
		
		super.signIn(username, password);
		
		super.clickOnMenu("Account", "General data");
		
		super.fillInputBoxIn("password", password);
		super.fillInputBoxIn("confirmation", password);
		super.fillInputBoxIn("identity.name", name);
		super.fillInputBoxIn("identity.surname", surname);
		super.fillInputBoxIn("identity.email", email);	
		
		super.clickOnSubmitButton("Update");
			
		super.checkErrorsExist();
		
		super.signOut();
	}
	

}
