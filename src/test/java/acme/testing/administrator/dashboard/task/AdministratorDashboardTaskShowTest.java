package acme.testing.administrator.dashboard.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AdministratorDashboardTaskShowTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	// This test case checks the correct show of the task dashboard. 
	// It is expected that when an administrator try to enter the dashobard is showed
	@Test
	@Order(10)	
	public void showPositive() {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Task Dashboard");
		
		super.checkExists(By.tagName("table"));
		
        super.checkExists(By.tagName("td"));
       
		
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
