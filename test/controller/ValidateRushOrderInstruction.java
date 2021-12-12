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
class ValidateRushOrderInstruction {

	private PlaceRushOrderController placeRushOrderController;
	
	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController = new PlaceRushOrderController();
	}

	@ParameterizedTest
    @CsvSource({
    	"Nha van hoa huyen Dong Anh, true",
        "Den xanh den do thu 2 re phai,true",
        "$#abcnha thu 3, false",
        ",false"
    })
	void test(String instruction, boolean expected) {
		//when
		boolean isValid = placeRushOrderController.validateRushOrderInstruction(instruction);
		//then
		assertEquals(expected, isValid);
	}

}
