package acme.testing.management.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagementTaskCreateTest extends AcmePlannerTest{


	// Test cases -------------------------------------------------------------
	// This test case checks the correct creation of a task. After creating the task, 
	// it is expected to return to the initial view of the application
	@ParameterizedTest
	@CsvFileSource(resources = "/management/task/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String title, final String description, final String link, final String start,
		final String end, final String workload,final String isPublic) {
		
		super.signIn("manager1", "manager1");

		super.clickOnMenu("Manager", "Create New Task");

		super.fillInputBoxIn("title", title);
		
		super.fillInputBoxIn("description", description);
		
		super.fillInputBoxIn("link", link);
		
		super.fillInputBoxIn("start", start);
		
		super.fillInputBoxIn("end", end);
		
		super.fillInputBoxIn("workload", workload);
		
		super.fillInputBoxIn("isPublic", isPublic);
		
		super.clickOnSubmitButton("New");

		super.clickOnMenu("Manager", "Tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title);

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("title", title);

		super.signOut();
	}
	
	// This test case checks the incorrect creation of a task. After creating the task, 
	// it is expected to return a error message
	@ParameterizedTest
	@CsvFileSource(resources = "/management/task/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegative(final int recordIndex,final String title, final String description, final String link, final String start,
		final String end, final String workload,final String isPublic) {
		
		super.signIn("manager1", "manager1");

		super.clickOnMenu("Manager", "Create New Task");

		super.fillInputBoxIn("title", title);
		
		super.fillInputBoxIn("description", description);
		
		super.fillInputBoxIn("link", link);
		
		super.fillInputBoxIn("start", start);
		
		super.fillInputBoxIn("end", end);
		
		super.fillInputBoxIn("workload", workload);
		
		super.fillInputBoxIn("isPublic", isPublic);
		
		super.clickOnSubmitButton("New");
		
		super.checkErrorsExist();

		super.signOut();
	}

}
