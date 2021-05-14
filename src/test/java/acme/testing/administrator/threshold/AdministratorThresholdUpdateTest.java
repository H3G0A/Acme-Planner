/*
 * EmployerApplicationUpdateTest.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing.administrator.threshold;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorThresholdUpdateTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/threshold/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(2)	
	public void updatePositive(final int recordIndex, final String thresholdNumber) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Manage threshold");
			
		super.fillInputBoxIn("thresholdNumber", thresholdNumber);	

		super.clickOnSubmitButton("Save changes");
				
		super.clickOnMenu("Administrator", "Threshold");
		
		super.checkInputBoxHasValue("thresholdNumber", thresholdNumber);
//		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/threshold/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(2)	
	public void updateNegative(final int recordIndex, final String thresholdNumber) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Manage threshold");
			
		super.fillInputBoxIn("thresholdNumber", thresholdNumber);	

		super.clickOnSubmitButton("Save changes");
				
		super.checkErrorsExist();
//		
		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------


}
