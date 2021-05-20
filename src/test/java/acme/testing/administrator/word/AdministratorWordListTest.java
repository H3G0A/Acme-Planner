package acme.testing.administrator.word;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorWordListTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	// This test case checks the correct listing the spam word. It is checked that the list values, 
	// are correct
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/word/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void list(final int recordIndex, final String word) {		
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Spam Word List");
				
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("word", word);

		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------

}
