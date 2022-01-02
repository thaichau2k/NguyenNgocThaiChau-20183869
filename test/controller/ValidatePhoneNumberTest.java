package controller;

import controller.impl.ShippingFeeCalculatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Nguyen Ngoc Thai Chau - 20183869
 */
class ValidatePhoneNumberTest {

	private PlaceOrderController placeOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController(new ShippingFeeCalculatorImpl());
	}

	@ParameterizedTest
	@CsvSource({
			"012345678,false",
			"0123456789,true",
			"12345,false",
			"0123152416912,false",
			"abcd,false",
			"0abcd,false",
			"*1245a,false",
			",false"
	})

	void test(String phone, boolean expected) {
		boolean isValid = placeOrderController.validatePhoneNumber(phone);
		assertEquals(isValid, expected);
	}

}
