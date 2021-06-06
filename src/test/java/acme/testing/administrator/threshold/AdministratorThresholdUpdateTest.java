package acme.testing.administrator.threshold;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorThresholdUpdateTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	// This test case checks the correct update of the spam threshold. After updating this value, 
	// it is expected to return to the initial view of the application
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/threshold/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void updatePositive(final int recordIndex, final String thresholdNumber) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Manage threshold");
			
		super.fillInputBoxIn("thresholdNumber", thresholdNumber);	

		super.clickOnSubmitButton("Save changes");
		
		super.checkSimplePath("/master/welcome");
				
		super.clickOnMenu("Administrator", "Manage threshold");
		
		super.checkInputBoxHasValue("thresholdNumber", thresholdNumber);
		
		super.signOut();
	}
	
	// This test case checks for errors after inserting wrong data as spam threshold, such as negative 
	// and out of range values, displaying the corresponding error message
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/threshold/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void updateNegative(final int recordIndex, final String thresholdNumber) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Manage threshold");
			
		super.fillInputBoxIn("thresholdNumber", thresholdNumber);	

		super.clickOnSubmitButton("Save changes");
				
		super.checkErrorsExist();
		
		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------


}
