package acme.testing.management.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;


public class ManagementTaskDeleteTest extends AcmePlannerTest{
	// Este test comprueba el correcto funcionamiento del borrado de las tasks
	@ParameterizedTest
	@CsvFileSource(resources="/management/task/delete-task-positive.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(20)
	public void deletePositive(final int recordIndex, final String title, final String description, final String link, final String start,
		final String end, final String workload,final String isPublic) {	
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Tasks");
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		
		super.checkInputBoxHasValue("description", description);

		super.checkInputBoxHasValue("link", link);
		
		super.checkInputBoxHasValue("start", start);
		
		super.checkInputBoxHasValue("end", end);
		
		super.checkInputBoxHasValue("workload", workload);
		
		super.checkInputBoxHasValue("isPublic", isPublic);
		
		final String[] url = super.driver.getCurrentUrl().split("=");
		
		super.driver.get(super.baseUrl+"/management/task/delete?id"+"="+url[1]);	
		
		super.clickOnSubmitButton("Delete");
		
		super.clickOnMenu("Manager", "Tasks");
		
		super.driver.get(super.baseUrl+"/management/task/delete?id"+"="+url[1]);
		
		super.checkPanicExists();
		
		super.signOut();
	}
	
	//Test que comprueba que una task no pueda ser borrada por otro usuario no autorizado
	@ParameterizedTest
	@CsvFileSource(resources = "/management/task/delete-task-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void deleteNegative(final int recordIndex) {		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Tasks");
				
		super.clickOnListingRecord(recordIndex);
		
		final String[] url = super.driver.getCurrentUrl().split("=");
		
		super.signOut();
		
		super.signIn("manager2", "manager2");
		
		super.clickOnMenu("Manager", "Tasks");
		
		super.driver.get(super.baseUrl+"/management/task/delete?id"+"="+url[1]);
		
		super.checkPanicExists();
		
		super.signOut();
	}	
}
