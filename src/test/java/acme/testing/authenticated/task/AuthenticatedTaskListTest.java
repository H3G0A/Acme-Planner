package acme.testing.authenticated.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedTaskListTest extends AcmePlannerTest {
	// Test cases -------------------------------------------------------------
	// This test case checks the correct listing of a task. It is checked that the list values 
	// are correct
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void list(final int recordIndex, final String title, final String description, final String link, final String start,
		final String end, final String workload,final String isPublic) {		
		super.signIn("manager2", "manager2");

		super.clickOnMenu("Authenticated", "Tasks");
		
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
}
