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

package acme.testing.administrator.word;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorWordShowTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/word/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void showPositive(final int recordIndex, final String word) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam Word List");
		
		super.checkColumnHasValue(recordIndex, 0, word);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("word", word);
		
		super.signOut();
	}
//	
//	@ParameterizedTest
//	@CsvFileSource(resources = "/administrator/word/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
//	@Order(20)	
//	public void updateNegative(final int recordIndex, final String word) {		
//		super.signIn("administrator", "administrator");
//		
//		super.clickOnMenu("Administrator", "Spam Word List");
//		
//		super.checkColumnHasValue(recordIndex, 0, word);
//		
//		super.clickOnListingRecord(recordIndex);
//		
//		super.fillInputBoxIn("word", word);	
//		
//		super.clickOnSubmitButton("Update");
//		
//		super.checkErrorsExist();
//		
//		super.signOut();
//	}
	
	// Ancillary methods ------------------------------------------------------


}
