package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskUpdateTest extends AcmePlannerTest {

	// Test cases -------------------------------------------------------------
	// This test case checks the correct update of a task. It is checked that the updated values 
	// are correct
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(2)	
	public void updatePositive(final int recordIndex, final String title, final String description, final String link, final String start,
		final String end, final String workload,final String isPublic) {		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Tasks");
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("title", title);
		
		super.fillInputBoxIn("description", description);
		
		super.fillInputBoxIn("link", link);
		
		super.fillInputBoxIn("start", start);
		
		super.fillInputBoxIn("end", end);
		
		super.fillInputBoxIn("workload", workload);
		
		super.fillInputBoxIn("isPublic", isPublic);
		
		super.clickOnSubmitButton("Update");
		
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
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(2)	
	public void updateNegative(final int recordIndex, final String title, final String description, final String link, final String start,
		final String end, final String workload,final String isPublic) {		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Tasks");
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("title", title);
		
		super.fillInputBoxIn("description", description);
		
		super.fillInputBoxIn("link", link);
		
		super.fillInputBoxIn("start", start);
		
		super.fillInputBoxIn("end", end);
		
		super.fillInputBoxIn("workload", workload);
		
		super.fillInputBoxIn("isPublic", isPublic);
		
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		
		super.signOut();
		
	}
	
}
