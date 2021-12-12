package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
/**
 * 
 * @author Nguyen Ngoc Thai Chau - 20183869
 *
 */
class ValidateRushOrderInfoTest {

	private PlaceRushOrderController placeRushOrderController;
	
	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController = new PlaceRushOrderController();
	}

	@ParameterizedTest
    @CsvSource({
    	"Harry Potter,true",
        "A@# Harry Potter,false",
        ",false"
    })
	void test(String info, boolean expected) {
		//when
		boolean isValid = placeRushOrderController.validateRushOrderInfo(info);
		//then
		assertEquals(expected, isValid);
	}

}
