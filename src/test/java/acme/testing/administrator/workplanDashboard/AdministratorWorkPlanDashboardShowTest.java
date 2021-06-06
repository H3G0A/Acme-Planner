package acme.testing.administrator.workplanDashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AdministratorWorkPlanDashboardShowTest extends AcmePlannerTest{

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	//This test checks the correct show of the value of a workplan dashboard
	// It is expected that when it is showed the values fit with the csv values 
	// Prevoulsy, we have created some WorkPlan to make the values of the dashboard changes
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/workPlanDashboard/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void showPositive(final int recordIndex, final String title, final String description, 
		final String startDate, final String endDate, final String taskSelected,
		final String numberOfPublicWorkPlan, final String numberOfPrivateWorkPlan, 
		final String numberOfFinishedWorkPlan, final String numberOfNonFinishedWorkPlan, 
		final String averageNumberOfWorkload) {	
		
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
		
		super.fillInputBoxIn("taskSelected", taskSelected);
		
		super.clickOnSubmitButton("Add Task");
		
		super.signOut();
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Workplan Dashboard");
		
		super.checkExists(By.tagName("table"));
		
        super.checkExists(By.tagName("td"));
        
        super.checkExists(By.tagName("canvas"));
        
        super.checkExists(By.xpath("/html/body/div[2]/div/table/tbody/tr[1]/td[normalize-space(text()) = '"+numberOfPublicWorkPlan+"']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table/tbody/tr[2]/td[normalize-space(text()) = '"+numberOfPrivateWorkPlan+"']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table/tbody/tr[3]/td[normalize-space(text()) = '"+numberOfFinishedWorkPlan+"']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table/tbody/tr[4]/td[normalize-space(text()) = '"+numberOfNonFinishedWorkPlan+"']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td[normalize-space(text()) = '"+averageNumberOfWorkload+"']"));

		
		super.signOut();
	}
		
//		// This test case checks the incorrect show of the workplan-dashboard. 
//		// It is expected that when an anonymous user try to enter to the dashboard editing the url
//		//	a panic alert is showed 
//		@Test
//		@Order(10)	
//		public void showNegative() {
//			
//			super.driver.get(super.baseUrl + "/administrator/workplan-dashboard/show");
//			
//			super.checkPanicExists();
//			
//		}
//		
}
