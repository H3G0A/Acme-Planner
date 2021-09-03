package acme.testing.anonymous.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkPlansTest;

public class AnonymousTaskShowTest extends AcmeWorkPlansTest{

	
	
	//Test that checks the task's details show correctly 
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/task/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void showPositive(final int recordIndex, final String title, final String start, final String end, final String workload, final String description, final String status, final String link) {
		super.clickOnMenu("Anonymous", "Tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, start);
		super.checkColumnHasValue(recordIndex, 2, end);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("start", start);
		super.checkInputBoxHasValue("end", end);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("isPublic", status);
		super.checkInputBoxHasValue("link", link);
	}
	
	
	//Test that checks an anonymous user tries to access a private task. It throws the error message.
	@Test
	@Order(20)
	public void showNegative() {
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Tasks");
		
		super.clickOnListingRecord(4);
				
		final String[] url = super.driver.getCurrentUrl().split("=");
		
		super.signOut();
		
		super.clickOnMenu("Anonymous", "Tasks");
		
		super.driver.get(super.baseUrl + "/anonymous/task/show?id="+url[1]);
		
		super.checkPanicExists();
	}
}
