package acme.testing.manager.workplans;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkPlanCreateTest extends AcmePlannerTest{
	
	
	//En este test se comprueba la correcta creación de un workplan
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/create-positive.csv",encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String title, final String description, final String startDate, final String endDate) {
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Create a New WorkPlan");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("start", startDate);
		super.fillInputBoxIn("end", endDate);
		
		super.clickOnSubmitButton("Create");
		
		super.checkSimplePath("/master/welcome");
		
		super.clickOnMenu("Manager", "Workplans");
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("start", startDate);
		super.checkInputBoxHasValue("end", endDate);
		
		super.signOut();
	}
	
	//En este test se comprueba que un workplan no se cree si su titulo es spma, su descripción esta vacia, 
	//la fecha de inicio es una cadena de texto y la final une fecha mal añadida
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/create-negative.csv",encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createnegative(final int recordIndex, final String title, final String description, final String startDate, final String endDate) {
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Create a New WorkPlan");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("start", startDate);
		super.fillInputBoxIn("end", endDate);
		
		super.clickOnSubmitButton("Create");
		
		super.checkErrorsExist();	
		
		super.signOut();
	}

}
