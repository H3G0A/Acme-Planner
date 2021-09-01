package acme.testing.administrator.word;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkPlansTest;

public class AdministratorWordShowTest extends AcmeWorkPlansTest {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	// This test case checks the correct show of the value of the a spam word. 
	// It is expected that when it is showed the value fits with the csv values 
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/word/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void showPositive(final int recordIndex, final String palabra) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam Word List");
				
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("palabra", palabra);
		
		super.signOut();
	}
	
		// This test case checks for errors after a anonymous user try to access to details of a spam word  
		//displaying the corresponding error message
		@Test
		@Order(10)	
		public void showNegative() {		
			super.signIn("administrator", "administrator");
			
			super.clickOnMenu("Administrator", "Spam Word List");
			
			super.clickOnListingRecord(0);
					
			final String[] url = super.driver.getCurrentUrl().split("=");
			
			super.signOut();
						
			super.driver.get(super.baseUrl + "/administrator/word/show?id="+url[1]);
			
			super.checkPanicExists();
		}
	
	// Ancillary methods ------------------------------------------------------


}
