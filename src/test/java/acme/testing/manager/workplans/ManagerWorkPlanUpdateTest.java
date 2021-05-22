package acme.testing.manager.workplans;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkPlanUpdateTest extends AcmePlannerTest{
	
	//Esta función comprueba que un workplan se actualice correctamente, simplemente se rellena un workplan con datos comunes
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void updatePositive(final int recordIndex, final String title, final String description, final String start, final String end) {		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Workplans");
		
		super.clickOnListingRecord(recordIndex);
		
		final String[] url = super.driver.getCurrentUrl().split("=");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("start", start);
		super.fillInputBoxIn("end", end);
		
		super.clickOnSubmitButton("Update");
				
		super.checkSimplePath("/management/work-plan/list");
		
		super.driver.get(super.baseUrl+"/management/work-plan/update?id"+"="+url[1]);
				
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("start", start);
		super.checkInputBoxHasValue("end", end);
		
		super.signOut();
	}
	
	//Este test comprueba que salten las excepciones adecuadas, en este tets se comprueba que salte la excepcion de palabara spam
	//Que un campo de texte este vacio y que la fecha tenga un formato adecuado
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void updateNegative(final int recordIndex, final String title, final String description, final String start, final String end) {		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Workplans");
		
		super.clickOnListingRecord(recordIndex);
				
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("start", start);
		super.fillInputBoxIn("end", end);
		
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		
		super.signOut();
	}

}
