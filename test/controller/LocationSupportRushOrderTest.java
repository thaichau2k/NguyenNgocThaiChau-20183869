package controller;

import controller.impl.RushOrderValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author Nguyen Ngoc Thai Chau - 20183869
 */
public class LocationSupportRushOrderTest {

    private PlaceRushOrderController placeRushOrderController;

    @BeforeEach
    void setUp() throws Exception {
        placeRushOrderController = new PlaceRushOrderController(new RushOrderValidator());
    }

    @ParameterizedTest
    @CsvSource({
            "Hà Nội,true",
            "Bắc Ninh,false",
            "Ha 123,false",
            ",false"
    })
    void test(String province, boolean expected) {
        boolean isValid = placeRushOrderController.isLocationSupportRushOrder(province);
        Assertions.assertEquals(isValid, expected);
    }
}
