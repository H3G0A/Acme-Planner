package acme.testing.management.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkPlansTest;

public class ManagementWorkPlanCreateTest extends AcmeWorkPlansTest{
	
	
	//Test that checks a workplan creates correctly
	@ParameterizedTest
	@CsvFileSource(resources = "/management/workplan/create-positive.csv",encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String title, final String description, final String startDate, final String endDate) {
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Create a New WorkPlan");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("start", startDate);
		super.fillInputBoxIn("end", endDate);
		
		super.clickOnSubmitButton("Create");
		
		super.checkSimplePath("/master/welcome");
		
		super.clickOnMenu("Manager", "Workplans");
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("start", startDate);
		super.checkInputBoxHasValue("end", endDate);
		
		super.signOut();
	}
	
	//Test that checks a error message will be thrown when a user tries to create a workplan 
	//without fill the information correctly like empty boxes, incorrect information...
	@ParameterizedTest
	@CsvFileSource(resources = "/management/workplan/create-negative.csv",encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createnegative(final int recordIndex, final String title, final String description, final String startDate, final String endDate) {
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Create a New WorkPlan");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("start", startDate);
		super.fillInputBoxIn("end", endDate);
		
		super.clickOnSubmitButton("Create");
		
		super.checkErrorsExist();	
		
		super.signOut();
	}

}
