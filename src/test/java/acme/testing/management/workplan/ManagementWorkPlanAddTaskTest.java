package acme.testing.management.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagementWorkPlanAddTaskTest extends AcmePlannerTest{
	
	//Test that checks a task can be added successfully to workplan
	@ParameterizedTest
	@CsvFileSource(resources = "/management/workplan/add-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void addTaskPositive(final int recordIndex, final String taskSelected, final String task, final String workload, final String workloadWorkPlan) {
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Workplans");
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("taskSelected", taskSelected);
		
		final String[] url = super.driver.getCurrentUrl().split("=");
		
		super.clickOnSubmitButton("Add Task");
		
		super.checkNotPanicExists();
				
		final double newWorkload = Double.valueOf(workload)+Double.valueOf(workloadWorkPlan);
		
		super.driver.get(super.baseUrl+"/management/work-plan/add-task?id"+"="+url[1]);
		
		final String[] newWorkloadSplitted = String.format("%.2f", newWorkload).split(",");
		
		super.checkInputBoxHasValue("workPlanWorkload", newWorkloadSplitted[0]+"."+newWorkloadSplitted[1]);
	}
	
	//Test that checks an other user can't add a task to his workplan changing the url's id
	@ParameterizedTest
	@CsvFileSource(resources = "/management/workplan/add-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void addNegative(final int recordIndex) {		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Workplans");
				
		super.clickOnListingRecord(recordIndex);
		
		final String[] url = super.driver.getCurrentUrl().split("=");
		
		super.signOut();
		
		super.signIn("manager2", "manager2");
		
		super.clickOnMenu("Manager", "Workplans");
		
		super.driver.get(super.baseUrl+"/management/work-plan/add_task?id"+"="+url[1]);
		
		super.checkPanicExists();
		
		super.signOut();
	}

}
