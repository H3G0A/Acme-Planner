package acme.testing.administrator.word;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorWordDeleteTest extends AcmePlannerTest {
	
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/word/delete-word-positive.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(10)
	public void deletePositive(final int recordIndex, final String word1, final String word2) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam Word List");
		
		super.checkColumnHasValue(recordIndex, 0, word1);
		
		super.clickOnListingRecord(recordIndex);
		
		super.clickOnSubmitButton("Delete");
		
		super.clickOnMenu("Administrator", "Spam Word List");
		
		super.checkColumnHasValue(recordIndex, 0, word2);
		
		super.signOut();
	}

}
