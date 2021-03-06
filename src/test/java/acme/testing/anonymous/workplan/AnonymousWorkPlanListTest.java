package acme.testing.anonymous.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkPlansTest;

public class AnonymousWorkPlanListTest extends AcmeWorkPlansTest {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	// This test case checks the correct listing the workplans. It is checked that the list values, 
	// are correct
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/workplan/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void list(final int recordIndex, final String title, final String dateInitial, 
		final String dateFinal, final String workPlanWorkload, final String status) {		

		super.clickOnMenu("Anonymous", "WorkPlans");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		
		super.checkColumnHasValue(recordIndex, 1, dateInitial);
		
		super.checkColumnHasValue(recordIndex, 2, dateFinal);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		
		super.checkInputBoxHasValue("start", dateInitial);
		
		super.checkInputBoxHasValue("end", dateFinal);
		
		super.checkInputBoxHasValue("workPlanWorkload", workPlanWorkload);
		
		super.checkInputBoxHasValue("isPublic", status);
	
	}

	// Ancillary methods ------------------------------------------------------

}
