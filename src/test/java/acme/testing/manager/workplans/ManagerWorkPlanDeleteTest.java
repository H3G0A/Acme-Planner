package acme.testing.manager.workplans;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkPlanDeleteTest extends AcmePlannerTest{
	
	@ParameterizedTest
	@CsvFileSource(resources="/manager/workplan/delete-workplan-positive.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(10)
	public void deletePositive(final int recordIndex, final String title, final String workload, final String executionPeriod, final String description, final String start, final String end) {
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Workplans");
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("executionPeriod", executionPeriod);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("start", start);
		super.checkInputBoxHasValue("end", end);

		final String[] url = super.driver.getCurrentUrl().split("=");
		
		super.driver.get(super.baseUrl+"/management/work-plan/delete?id"+"="+url[1]);	
		
		super.clickOnSubmitButton("Delete");
		
		super.clickOnMenu("Manager", "Workplans");
		
		super.driver.get(super.baseUrl+"/management/work-plan/delete?id"+"="+url[1]);
		
		super.checkPanicExists();
		
		super.signOut();
	}

}
