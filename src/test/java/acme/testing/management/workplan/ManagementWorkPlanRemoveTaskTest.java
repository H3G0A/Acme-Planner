package acme.testing.management.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagementWorkPlanRemoveTaskTest extends AcmePlannerTest{
	
	//Test that checks a workplan deletes correctly
	@ParameterizedTest
	@CsvFileSource(resources = "/management/workplan/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void removeTaskPositive(final int recordIndex, final String taskSelected, final String task, final String workload, final String workloadWorkPlan) {
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Workplans");
		
		super.clickOnListingRecord(recordIndex);
		
		final String[] url = super.driver.getCurrentUrl().split("=");
		
		super.clickOnSubmitButton("Delete Task");
		
		super.checkNotPanicExists();
				
		final double newWorkload = Double.valueOf(workloadWorkPlan) - Double.valueOf(workload);
		
		super.clickOnListingRecord(recordIndex);
		
		super.driver.get(super.baseUrl+"/management/work-plan/remove-task?id"+"="+url[1]);
		
		final String[] newWorkloadSplitted = String.format("%.2f", newWorkload).split(",");
		
		super.checkInputBoxHasValue("workPlanWorkload", newWorkloadSplitted[0]+"."+newWorkloadSplitted[1]);
	}
	
	//Test that checks other user can't delete a task of his workplan changing the url. 
	//Error message will be shown.
	@ParameterizedTest
	@CsvFileSource(resources = "/management/workplan/delete-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void removeTaskNegative(final int recordIndex) {		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Workplans");
				
		super.clickOnListingRecord(recordIndex);
		
		final String[] url = super.driver.getCurrentUrl().split("=");
		
		super.signOut();
		
		super.signIn("manager2", "manager2");
		
		super.clickOnMenu("Manager", "Workplans");
		
		super.driver.get(super.baseUrl+"/management/work-plan/remove_task?id"+"="+url[1]);
		
		super.checkPanicExists();
		
		super.signOut();
	}

}
