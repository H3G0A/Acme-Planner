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

public class AdministratorThresholdResetTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	// This test case checks the correct reset of the spam threshold after changing the initial default value. 
	// It is expected that after reseting the threshold it will have a value of 10.00
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/threshold/reset-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void resetPositive(final int recordIndex, final String thresholdNumber) {		
		super.signIn("administrator", "administrator");
			
		super.clickOnMenu("Administrator", "Manage threshold");
				
		super.fillInputBoxIn("thresholdNumber", thresholdNumber);	

		super.clickOnSubmitButton("Save changes");
		
		super.clickOnMenu("Administrator", "Manage threshold");
			
		super.clickOnSubmitButton("Reset");
				
		super.driver.get(super.baseUrl+"/administrator/threshold/reset");
		
		super.checkInputBoxHasValue("thresholdNumber", "10.00");
		
		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------


}
