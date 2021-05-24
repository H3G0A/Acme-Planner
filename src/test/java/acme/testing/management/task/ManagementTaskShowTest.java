package acme.testing.management.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagementTaskShowTest extends AcmePlannerTest{

	//This test checks the correct show of the value of a task
	// It is expected that when it is showed the values fit with the csv values 
	@ParameterizedTest
	@CsvFileSource(resources = "/management/task/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void showPositive(final int recordIndex, final String title, final String description, final String link, final String start,
		final String end, final String workload,final String isPublic) {		
		super.signIn("manager1", "manager1");

		super.clickOnMenu("Manager", "Tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		
		super.checkColumnHasValue(recordIndex, 1, start);
		
		super.checkColumnHasValue(recordIndex, 2, end);

		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		
		super.checkInputBoxHasValue("description", description);

		super.checkInputBoxHasValue("link", link);
		
		super.checkInputBoxHasValue("start", start);
		
		super.checkInputBoxHasValue("end", end);
		
		super.checkInputBoxHasValue("workload", workload);
		
		super.checkInputBoxHasValue("isPublic", isPublic);
		}
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/management/task/show-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void showNegative(final int recordIndex) {		
	super.signIn("manager1", "manager1");

		super.clickOnMenu("Manager", "Tasks");
	
		super.clickOnListingRecord(recordIndex);
		
		final String[] url = super.driver.getCurrentUrl().split("=");
		
		super.signOut();
		
		super.signIn("manager2", "manager2");
		
		super.clickOnMenu("Manager", "Tasks");
	
		super.driver.get(super.baseUrl+"/management/task/show?id"+"="+url[1]);
		
		super.checkPanicExists();
		
		super.signOut();
	}
}
