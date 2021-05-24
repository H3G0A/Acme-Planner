package acme.testing.administrator.workplanDashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AdministratorWorkPlanDashboardShowTest extends AcmePlannerTest{

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	//This test checks the correct show of the value of a workplan dashboard
	// It is expected that when it is showed the values fit with the csv values 
	@Test
	@Order(10)	
		public void showPositive() {		
			super.signIn("administrator", "administrator");
			
			super.clickOnMenu("Administrator", "Workplan Dashboard");
			
			super.checkExists(By.tagName("table"));
			
	        super.checkExists(By.tagName("td"));
	        
	        super.checkExists(By.tagName("canvas"));
			
			super.signOut();
		}
		
		// This test case checks the incorrect show of the workplan-dashboard. 
		// It is expected that when an anonymous user try to enter to the dashboard editing the url
		//	a panic alert is showed 
		@Test
		@Order(10)	
		public void showNegative() {
			
			super.driver.get(super.baseUrl + "/administrator/workplan-dashboard/show");
			
			super.checkPanicExists();
			
		}
		
}
