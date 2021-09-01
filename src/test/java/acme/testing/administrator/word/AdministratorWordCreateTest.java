package acme.testing.administrator.word;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkPlansTest;

public class AdministratorWordCreateTest extends AcmeWorkPlansTest {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	// This test case checks the correct creation of a spam word. After creating the spam word, 
	// it is expected to return to the initial view of the application
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/word/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String palabra) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Create a Spam Word");

		super.fillInputBoxIn("palabra", palabra);
		
		super.clickOnSubmitButton("Add word");
		
		super.checkSimplePath("/master/welcome");

		super.clickOnMenu("Administrator", "Spam Word List");
		
		super.checkColumnHasValue(recordIndex, 0, palabra);

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("palabra", palabra);

		super.signOut();
	}
	
	// This test case checks for errors after inserting wrong data as spam word, such as blank values 
	//displaying the corresponding error message
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/word/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegative(final String palabra) {
		
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Create a Spam Word");

		super.fillInputBoxIn("palabra", palabra);

		super.clickOnSubmitButton("Add word");

		super.checkErrorsExist();

		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------

}
