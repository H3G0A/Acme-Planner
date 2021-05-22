package acme.testing.administrator.word;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorWordDeleteTest extends AcmePlannerTest {
	
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/word/delete-word-positive.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(10)
	public void deletePositive(final int recordIndex, final String palabra1, final String palabra2) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam Word List");
				
		super.clickOnListingRecord(recordIndex);

		final String[] url = super.driver.getCurrentUrl().split("=");
		
		super.driver.get(super.baseUrl+"/administrator/word/delete?id"+"="+url[1]);	
		
		super.clickOnSubmitButton("Delete");
		
		super.clickOnMenu("Administrator", "Spam Word List");
		
		super.driver.get(super.baseUrl+"/administrator/word/delete?id"+"="+url[1]);
		
		super.checkPanicExists();
		
		super.signOut();
	}

}
