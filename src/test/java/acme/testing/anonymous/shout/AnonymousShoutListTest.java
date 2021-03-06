package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkPlansTest;

public class AnonymousShoutListTest extends AcmeWorkPlansTest{
	
	//Test that checks shouts list correctly
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shouts/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void list(final int recordIndex, final String moment ,final String author,final String text) {
		super.clickOnMenu("Anonymous", "Shouts");
		
		super.checkColumnHasValue(recordIndex, 0, moment);
		
		super.checkColumnHasValue(recordIndex, 1, author);
		
		super.checkColumnHasValue(recordIndex, 2, text);
		
		
	}
}
