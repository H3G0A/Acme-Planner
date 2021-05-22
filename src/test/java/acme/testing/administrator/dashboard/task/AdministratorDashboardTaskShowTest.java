package acme.testing.administrator.dashboard.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorDashboardTaskShowTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	// This test case checks the correct show of the value of the task dashboard. 
	// It is expected that when it is showed the value fits with the csv values 
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/dashboardTask/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void show() {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Task Dashboard");
		
		super.signOut();
	}

	
	// Ancillary methods ------------------------------------------------------


}
