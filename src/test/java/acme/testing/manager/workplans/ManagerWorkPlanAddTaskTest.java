package acme.testing.manager.workplans;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkPlanAddTaskTest extends AcmePlannerTest{
	
	//Test que comprueba que se añade una nueva task a un workplan
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/add-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void addTaskPositive(final int recordIndex, final String taskSelected, final String task, final String workload, final String workloadWorkPlan) {
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Workplans");
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("taskSelected", taskSelected);
		
		super.clickOnSubmitButton("Add Task");
		
		super.checkNotPanicExists();
				
		final double newWorkload = Double.valueOf(workload)+Double.valueOf(workloadWorkPlan);
		
		super.clickOnListingRecord(recordIndex);
		
		final String[] newWorkloadSplitted = String.format("%.2f", newWorkload).split(",");
		
		super.checkInputBoxHasValue("workload", newWorkloadSplitted[0]+"."+newWorkloadSplitted[1]);
	}
	
	//Test que comprueba que otro usuario no pueda añadir una task a tu workplan
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/add-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(2)	
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