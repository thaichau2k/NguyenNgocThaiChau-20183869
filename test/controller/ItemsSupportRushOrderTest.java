package controller;

import controller.impl.RushOrderValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author Nguyen Ngoc Thai Chau - 20183869
 */
public class ItemsSupportRushOrderTest {

    private PlaceRushOrderController placeRushOrderController;

    @BeforeEach
    void setUp() throws Exception {
        placeRushOrderController = new PlaceRushOrderController(new RushOrderValidator());
    }

    @ParameterizedTest
    @CsvSource({
            "39,false",
            "30,true",
            "32,false"
    })
    void test(int mediaID, boolean expected) {
        boolean isValid = placeRushOrderController.isItemsSupportRushOrder(mediaID);
        Assertions.assertEquals(isValid, expected);
    }
}
