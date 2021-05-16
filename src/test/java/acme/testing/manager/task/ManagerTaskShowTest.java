package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskShowTest extends AcmePlannerTest{

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
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
}
