package acme.testing.administrator.word;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorWordUpdateTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	// This test case checks the correct update of a spam word threshold. After updating these values, 
	// it is expected to return to the initial view of the application
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/word/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(2)	
	public void updatePositive(final int recordIndex, final String word) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam Word List");
		
		super.checkColumnHasValue(recordIndex, 0, word);
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("word", word);	
		
		super.clickOnSubmitButton("Update");
		
		super.checkSimplePath("/administrator/word/list");
		
		super.checkColumnHasValue(recordIndex, 0, word);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("word", word);
		
		super.signOut();
	}
	
	// This test case checks for errors after inserting wrong data a spam word, such as blank value 
	// , displaying the corresponding error message
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/word/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(2)	
	public void updateNegative(final int recordIndex, final String word) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam Word List");
		
		super.checkColumnHasValue(recordIndex, 0, word);
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("word", word);	
		
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------


}
