package acme.testing.management.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkPlansTest;

public class ManagementWorkPlanShowTest extends AcmeWorkPlansTest{
	
	//Test that checks a workplan's details show correctly
	@ParameterizedTest
	@CsvFileSource(resources = "/management/workplan/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void showPositive(final int recordIndex, final String title, final String workPlanWorkload, final String workPlanPeriod, final String description, final String start, final String end) {		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Workplans");
				
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("workPlanWorkload", workPlanWorkload);
		super.checkInputBoxHasValue("workPlanPeriod", workPlanPeriod);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("start", start);
		super.checkInputBoxHasValue("end", end);
		
		super.signOut();
	}
	
	
	
	//Test that checks a workplan's details can't be shown by other manager through url's id changes.
	//Error message will be thrown
		@ParameterizedTest
		@CsvFileSource(resources = "/management/workplan/show-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(20)	
		public void showNegative(final int recordIndex) {		
			super.signIn("manager1", "manager1");
			
			super.clickOnMenu("Manager", "Workplans");
					
			super.clickOnListingRecord(recordIndex);
			
			final String[] url = super.driver.getCurrentUrl().split("=");
			
			super.signOut();
			
			super.signIn("manager2", "manager2");
			
			super.clickOnMenu("Manager", "Workplans");
			
			super.driver.get(super.baseUrl+"/management/work-plan/show?id"+"="+url[1]);
			
			super.checkPanicExists();
			
			super.signOut();
		}

}
