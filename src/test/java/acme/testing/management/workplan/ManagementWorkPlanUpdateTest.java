package acme.testing.management.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkPlansTest;

public class ManagementWorkPlanUpdateTest extends AcmeWorkPlansTest{
	
	//Test that checks a workplan updates correctly. The test just fills the input boxes with common data.
	@ParameterizedTest
	@CsvFileSource(resources = "/management/workplan/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void updatePositive(final int recordIndex, final String title, final String description, final String start, final String end) {		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Workplans");
		
		super.clickOnListingRecord(recordIndex);
		
		final String[] url = super.driver.getCurrentUrl().split("=");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("start", start);
		super.fillInputBoxIn("end", end);
		
		super.clickOnSubmitButton("Update");
				
		super.checkSimplePath("/management/work-plan/list");
		
		super.driver.get(super.baseUrl+"/management/work-plan/update?id"+"="+url[1]);
				
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("start", start);
		super.checkInputBoxHasValue("end", end);
		
		super.signOut();
	}
	
	//Test that checks the error message throws correctly when it tries to fill the input boxes with 
	//information that is not allowed or the boxes are empty.
	@ParameterizedTest
	@CsvFileSource(resources = "/management/workplan/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void updateNegative(final int recordIndex, final String title, final String description, final String start, final String end) {		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Workplans");
		
		super.clickOnListingRecord(recordIndex);
				
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("start", start);
		super.fillInputBoxIn("end", end);
		
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		
		super.signOut();
	}

}
