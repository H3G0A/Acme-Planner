package acme.testing.anonymous.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousWorkPlanShowTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	// This test case checks the correct show of the values of the a workplan. 
	// It is expected that when it is showed the value fits with the csv values 
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/workplans/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void showPositive(final int recordIndex, final String title, final String dateInitial, 
		final String dateFinal, final String workload, final String status) {		
		
		super.clickOnMenu("Anonymous", "WorkPlans");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		
		super.checkColumnHasValue(recordIndex, 1, dateInitial);
		
		super.checkColumnHasValue(recordIndex, 2, dateFinal);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		
		super.checkInputBoxHasValue("start", dateInitial);
		
		super.checkInputBoxHasValue("end", dateFinal);
		
		super.checkInputBoxHasValue("workload", workload);
		
		super.checkInputBoxHasValue("isPublic", status);
	}
	
//	@ParameterizedTest
//	@CsvFileSource(resources = "/anonymous/workplans/show-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
//	@Order(10)	
//	public void showNegative(final int recordIndex, final String title, final String dateInitial, 
//		final String dateFinal, final String workload, final String status) {		
//		
//		super.clickOnMenu("Anonymous", "WorkPlans");
//		
//		super.checkColumnHasValue(recordIndex, 0, title);
//		
//		super.checkColumnHasValue(recordIndex, 1, dateInitial);
//		
//		super.checkColumnHasValue(recordIndex, 2, dateFinal);
//		
//		super.clickOnListingRecord(recordIndex);
//		
//		super.checkInputBoxHasValue("title", title);
//		
//		super.checkInputBoxHasValue("start", dateInitial);
//		
//		super.checkInputBoxHasValue("end", dateFinal);
//		
//		super.checkInputBoxHasValue("workload", workload);
//		
//		super.checkInputBoxHasValue("isPublic", status);
//	
//	}
	
	// Ancillary methods ------------------------------------------------------


}
