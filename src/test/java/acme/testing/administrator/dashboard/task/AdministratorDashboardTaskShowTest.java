package acme.testing.administrator.dashboard.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmeWorkPlansTest;

public class AdministratorDashboardTaskShowTest extends AcmeWorkPlansTest {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	// This test case checks the correct show of the task dashboard.  
	// It is expected that when an administrator try to enter the dashobard is showing the correct values
	// Prevoulsy, we have created some Task to make the values of the dashboard changes
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/dashboardTask/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void showPositive(final String title, final String description, final String link, final String start,
		final String end, final String workload,final String isPublic, final String numberOfPublicTask, final String numberOfPrivateTask, 
		final String numberOfFinishedTask, final String numberOfNotFinishedTask, final String averageNumberOfWorkload, 
		final String averageNumberOfExecutionPeriods) {
		
		super.signIn("manager1", "manager1");

		super.clickOnMenu("Manager", "Create New Task");

		super.fillInputBoxIn("title", title);
		
		super.fillInputBoxIn("description", description);
		
		super.fillInputBoxIn("link", link);
		
		super.fillInputBoxIn("start", start);
		
		super.fillInputBoxIn("end", end);
		
		super.fillInputBoxIn("workload", workload);
		
		super.fillInputBoxIn("isPublic", isPublic);
		
		super.clickOnSubmitButton("New");
		
		super.signOut();
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Task Dashboard");
		
		super.checkExists(By.tagName("table"));
		
        super.checkExists(By.tagName("td"));
        
		super.checkExists(By.xpath("/html/body/div[2]/div/table/tbody/tr[1]/td[normalize-space(text()) = '"+numberOfPublicTask+"']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table/tbody/tr[2]/td[normalize-space(text()) = '"+numberOfPrivateTask+"']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table/tbody/tr[3]/td[normalize-space(text()) = '"+numberOfFinishedTask+"']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table/tbody/tr[4]/td[normalize-space(text()) = '"+numberOfNotFinishedTask+"']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td[normalize-space(text()) = '"+averageNumberOfWorkload+"']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table/tbody/tr[6]/td[normalize-space(text()) = '"+averageNumberOfExecutionPeriods+"']"));
       
		super.signOut();
	}

	// This test case checks the incorrect show of the task-dashboard. 
	// It is expected that when an anonymous user try to enter to the dashboard editing the url
	//	a panic alert is showed 
	@Test
	@Order(10)	
	public void showNegative() {
		
		super.driver.get(super.baseUrl + "/administrator/task-dashboard/show");
		
		super.checkPanicExists();
		
	}
	// Ancillary methods ------------------------------------------------------

}
