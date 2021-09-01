package acme.testing.anonymous.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkPlansTest;

public class AnonymousWorkPlanShowTest extends AcmeWorkPlansTest {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	// This test case checks the correct show of the values of the a workplan. 
	// It is expected that when it is showed the value fits with the csv values 
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/workplan/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void showPositive(final int recordIndex, final String title, final String dateInitial, 
		final String dateFinal, final String workPlanWorkload, final String status) {		
		
		super.clickOnMenu("Anonymous", "WorkPlans");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		
		super.checkColumnHasValue(recordIndex, 1, dateInitial);
		
		super.checkColumnHasValue(recordIndex, 2, dateFinal);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		
		super.checkInputBoxHasValue("start", dateInitial);
		
		super.checkInputBoxHasValue("end", dateFinal);
		
		super.checkInputBoxHasValue("workPlanWorkload", workPlanWorkload);
		
		super.checkInputBoxHasValue("isPublic", status);
	}
	
	// This test case checks the values of the a workplan when an anonymous user try to access to it through a url. 
	// It is expected throws a error 
	@Test
	@Order(20)
	public void showNegative() {
		super.signIn("manager3", "manager3");
		
		super.clickOnMenu("Manager", "Workplans");
		
		super.clickOnListingRecord(0);
				
		final String[] url = super.driver.getCurrentUrl().split("=");
		
		super.signOut();
		
		super.clickOnMenu("Anonymous", "WorkPlans");
		
		super.driver.get(super.baseUrl + "/anonymous/work-plan/show?id="+url[1]);
		
		super.checkPanicExists();
	}
	
	// Ancillary methods ------------------------------------------------------


}
