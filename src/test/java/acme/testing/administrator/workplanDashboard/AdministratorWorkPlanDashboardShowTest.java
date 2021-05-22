package acme.testing.administrator.workplanDashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorWorkPlanDashboardShowTest extends AcmePlannerTest{

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	//This test checks the correct show of the value of a workplan dashboard
	// It is expected that when it is showed the values fit with the csv values 
		@ParameterizedTest
		@CsvFileSource(resources = "/administrator/WorkPlanDashboard/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(2)	
		public void ShowPositive(final int recordIndex) {		
			super.signIn("administrator", "administrator");
			
			super.clickOnMenu("Administrator", "Workplan Dashboard");
			
			super.signOut();
		}
		
}
