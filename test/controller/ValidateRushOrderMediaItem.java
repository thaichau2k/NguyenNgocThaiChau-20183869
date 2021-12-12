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
class ValidateRushOrderMediaItem {

	private PlaceRushOrderController placeRushOrderController;
	
	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController = new PlaceRushOrderController();
	}

	@ParameterizedTest
    @CsvSource({
            "500,true",
            "600,true",
            "9001,false",
            "900,true"
    })
	void test(int mediaId, boolean expected) {
		boolean isValid = placeRushOrderController.validateItems(mediaId);
		assertEquals(expected, isValid);
	}

}
