package acme.testing.management.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagementWorkPlanListTest extends AcmePlannerTest{
	
	//Test that checks workplans list correctly
	@ParameterizedTest
	@CsvFileSource(resources = "/management/workplan/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void list(final int recordIndex, final String title, final String start, final String end, final String workload) {		
		super.signIn("manager1", "manager1");

		super.clickOnMenu("Manager", "Workplans");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, start);
		super.checkColumnHasValue(recordIndex, 2, end);
		super.checkColumnHasValue(recordIndex, 3, workload);

		super.signOut();
	}

}
