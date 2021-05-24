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
	@Order(10)	
	public void updatePositive(final int recordIndex, final String palabra) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam Word List");
				
		super.clickOnListingRecord(recordIndex);
		
		final String[] url = super.driver.getCurrentUrl().split("=");
		
		super.fillInputBoxIn("palabra", palabra);	
		
		super.clickOnSubmitButton("Update");
				
		super.checkSimplePath("/administrator/word/list");
		
		super.driver.get(super.baseUrl+"/administrator/word/update?id"+"="+url[1]);
				
		super.checkInputBoxHasValue("palabra", palabra);
		
		super.signOut();
	}
	
	// This test case checks for errors after inserting wrong data a spam word, such as blank value 
	// , displaying the corresponding error message
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/word/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void updateNegative(final int recordIndex, final String palabra) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam Word List");
				
		super.clickOnListingRecord(recordIndex);
				
		super.fillInputBoxIn("palabra", palabra);	
		
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------


}
