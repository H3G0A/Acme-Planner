package acme.testing.anonymous.shout;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest{
	
	// En este test se comprueba la correcta creación de un Shout
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shouts/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String author, final String text, final String info) {
		super.clickOnMenu("Anonymous", "New Shout");	
		
		
		super.fillInputBoxIn("author", author);
		
		super.fillInputBoxIn("text", text);
		
		super.fillInputBoxIn("info", info);

		super.clickOnSubmitButton("Shout!");

		super.checkSimplePath("/master/welcome");

		super.clickOnMenu("Anonymous", "Shouts");
		
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        final String formatDateTime = LocalDateTime.now().format(formatter);
        
		super.checkColumnHasValue(recordIndex, 0, formatDateTime);
		
		super.checkColumnHasValue(recordIndex, 1, author);

		super.checkColumnHasValue(recordIndex, 2, text);
		
	}
	
	//En este test se comprueba que salten los errores correspondientes
	//En este test los autores son palabras spam, el texto esta vacio, y el valor info no es una url
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shouts/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegative(final int recordIndex, final String author,final String text, final String info) {
		super.clickOnMenu("Anonymous", "New Shout");
		
		super.fillInputBoxIn("author", author);
		
		super.fillInputBoxIn("text", text);
		
		super.fillInputBoxIn("info", info);
		
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();
		
		
	}

}
