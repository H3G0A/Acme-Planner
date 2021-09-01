package acme.testing.administrator.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkPlansTest;

public class AdministratorUserAccountListTest extends AcmeWorkPlansTest{
	
	// This test case checks administrator user account list correctly. 
	// It is expected that when an administrator try to enter the user accounts is listed
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/userAccount/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void list(final int recordIndex, final String username ,final String name,final String surname) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "User accounts");
		
		super.checkColumnHasValue(recordIndex, 0, username);
		
		super.checkColumnHasValue(recordIndex, 1, name);
		
		super.checkColumnHasValue(recordIndex, 2, surname);
		
		super.signOut();
		
	}

}
