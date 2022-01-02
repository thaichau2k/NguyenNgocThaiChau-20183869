package controller;

import controller.impl.ShippingFeeCalculatorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author Nguyen Ngoc Thai Chau - 20183869
 */
public class ValidateNameTest {

    private PlaceOrderController placeOrderController;

    @BeforeEach
    void setUp() throws Exception {
        placeOrderController = new PlaceOrderController(new ShippingFeeCalculatorImpl());
    }

    @ParameterizedTest
    @CsvSource({
            "Chau,true",
            "Thai Chau,false",
            "%Tam,false",
            "H62a,false",
            "C()hau,false",
            ",fasle"
    })
    void test(String name, boolean expected) {
        boolean isValid = placeOrderController.validateName(name);
        Assertions.assertEquals(isValid, expected);
    }
}
