package acme.testing.manager.workplans;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkPlanPublishTest extends AcmePlannerTest{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/publish-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void publishWorkPlanPositive(final int recordIndex, final String isPublic) {
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Workplans");
		
		super.clickOnListingRecord(recordIndex);
		
		final String[] url = super.driver.getCurrentUrl().split("=");
		
		super.clickOnSubmitButton("Publish");
		
		super.driver.get(super.baseUrl+"/management/work-plan/publish?id"+"="+url[1]);
		
		super.checkInputBoxHasValue("isPublic", isPublic);
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/publish-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void publishWorkPlanNegative(final int recordIndex) {
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Workplans");
		
		super.clickOnListingRecord(recordIndex);
		
		super.clickOnSubmitButton("Publish");
		
		super.checkErrorsExist();
	}
	

}
