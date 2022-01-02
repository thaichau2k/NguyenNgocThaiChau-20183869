package controller;

import java.util.List;
import java.util.logging.Logger;

/**
 * This class controls the flow of place rush order use case in our AIMS project
 * @author Nguyen Ngoc Thai Chau - 20183869
 */
public class PlaceRushOrderController {

    private RushOrderInputValidator rushOrderInputValidator;

    public PlaceRushOrderController(RushOrderInputValidator rushOrderInputValidator) {
        this.rushOrderInputValidator = rushOrderInputValidator;
    }

    /**
     * Specify provinces that support rush order
     */
    public static List<String> PROVINCES_SUPPORT_RUSH_ODER = List.of("Hà Nội");

    /**
     * Specify media id that support rush order
     * Only media id = 30 for testing
     */
    public static List<Integer> MEDIA_IDS_SUPPORT_RUSH_ORDER = List.of(30);

    /**
     * Just for logging purpose
     */
    private static final Logger LOGGER = utils.Utils.getLogger(PlaceRushOrderController.class.getName());

    public static final String RECEIVE_TIME_FORMATTER = "dd-MM-yyyy HH:mm";

    /**
     * Method checks user's location support rush order or not
     * @param location User's province
     */
    public boolean isLocationSupportRushOrder(String location) {
        if (location == null) {
            return false;
        }
        return PROVINCES_SUPPORT_RUSH_ODER.contains(location);
    }

    /**
     * Method checks user's media support rush order or not
     * @param mediaID Cart's media id
     */
    public boolean isItemsSupportRushOrder(int mediaID) {
        return MEDIA_IDS_SUPPORT_RUSH_ORDER.contains(mediaID);
    }

    /**
     * Method checks user's info support rush order or not
     * @param location User's province
     * @param mediaID Cart's media id
     */
    public boolean isSupportRushOrder(String location, int mediaID) {
        return isLocationSupportRushOrder(location) && isItemsSupportRushOrder(mediaID);
    }

    /**
     * Method validates user's receive time
     * @param time User's receive time
     */
    public boolean validateReceiveTime(String time) {
        return rushOrderInputValidator.isValidReceiveTime(time, RECEIVE_TIME_FORMATTER);
    }
}
