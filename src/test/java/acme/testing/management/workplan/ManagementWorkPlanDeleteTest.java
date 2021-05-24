package acme.testing.management.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagementWorkPlanDeleteTest extends AcmePlannerTest{
	
	// Este test comprueba el correcto funcionamiento del borrado de los workplans
	@ParameterizedTest
	@CsvFileSource(resources="/management/workplan/delete-workplan-positive.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(20)
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
	
	//Test que comprueba que un workplan no pueda ser borrado por otro usuario no autorizado
	@ParameterizedTest
	@CsvFileSource(resources = "/management/workplan/delete-workplan-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void deleteNegative(final int recordIndex) {		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Workplans");
				
		super.clickOnListingRecord(recordIndex);
		
		final String[] url = super.driver.getCurrentUrl().split("=");
		
		super.signOut();
		
		super.signIn("manager2", "manager2");
		
		super.clickOnMenu("Manager", "Workplans");
		
		super.driver.get(super.baseUrl+"/management/work-plan/delete?id"+"="+url[1]);
		
		super.checkPanicExists();
		
		super.signOut();
	}

}