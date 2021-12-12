package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
/**
 * 
 * @author Nguyen Ngoc Thai Chau 20183869
 *
 */
class ValidateNameTest {
	
	private PlaceOrderController placeOrderController;
	
	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}

	@ParameterizedTest
	@CsvSource({
		"Nguyen Ngoc Thai Chau,true",
		"chaunnt,true",
		"Nguyen123456789,false",
		"$#Chau,false",
		",false"
	})
	void test(String name, boolean expected) {
		boolean isValided = placeOrderController.validateName(name);
		assertEquals(expected, isValided);
	}

}
