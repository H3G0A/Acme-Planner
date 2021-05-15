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

public class AdministratorThresholdShowTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	// This test case checks the correct show of the value of the threshold. 
	// It is expected that when it is showed the default value is 10.00
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/threshold/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(2)	
	public void showPositive(final int recordIndex, final String thresholdNumber) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Threshold");
				
		super.checkInputBoxHasValue("thresholdNumber", thresholdNumber);
		
		super.signOut();
	}
//	
//	@ParameterizedTest
//	@CsvFileSource(resources = "/administrator/word/show-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
//	@Order(2)	
//	public void showNegative(final int recordIndex, final String thresholdNumber) {		
//		super.signIn("administrator", "administrator");
//		
//		super.clickOnMenu("Administrator", "Threshold");
//				
//		super.checkInputBoxHasValue("thresholdNumber", thresholdNumber);
//		
//		super.signOut();
//	}
	
	// Ancillary methods ------------------------------------------------------


}
