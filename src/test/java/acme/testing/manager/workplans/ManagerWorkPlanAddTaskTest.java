package acme.testing.manager.workplans;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkPlanAddTaskTest extends AcmePlannerTest{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/add-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void addTaskPositive(final int recordIndex, final String taskSelected, final String task) {
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Workplans");
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("taskSelected", taskSelected);
		
		super.clickOnSubmitButton("Add Task");
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkColumnHasValue(recordIndex, 0, taskSelected);
	}

}
