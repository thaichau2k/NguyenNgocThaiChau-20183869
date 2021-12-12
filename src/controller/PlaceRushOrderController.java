package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class controls the flow of place rush order usecase in our AIMS project
 * 
 * 
 * @author Nguyen Ngoc Thai Chau - 20183869
 */
public class PlaceRushOrderController {

    /**
     * List of provinces support RushOrder
     */
    public static List<String> LIST_PROVINCES= List.of("Ha noi","Ho Chi Minh");
    		
    /**
     * Media id list 
     */
    public static List<Integer> LIST_MEDIA_IDS = List.of(500, 600, 900);

    /**
     * Just for logging purpose
     */
    private static final Logger LOGGER = utils.Utils.getLogger(PlaceRushOrderController.class.getName());
    		
    /**
     * String date format
     */
    public static final String DATE_FORMATER = "dd-MM-yyyy HH:mm";
    
    /**
     * Method check string(string no contain character special, not null, not empty)
     * @param string check
     * @return true/false
     */
    private boolean checkString(String info) {
        if (info == null || info.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
    	Matcher matcher = pattern.matcher(info.trim().replaceAll("\\s",""));
        if(matcher.find()) return false;

        return true;
    }
    /**
     * method check location not null and include in list support
     * @param province
     */
    public boolean validateLocation(String location) {
        if (location == null) {
            return false;
        }
        if (LIST_PROVINCES.contains(location)) {
            return true;
        }
        return false;
    }

    /**
     * method check mediaId not null and include in list support
     * @param mediaID
     */
    public boolean validateItems(int mediaID) {
        if (LIST_MEDIA_IDS.contains(mediaID)) {
            return true;
        }
        return false;
    }

    /**
     * Method validate time receive
     * @param time receive
     */
    public boolean validateReceiveTime(String time) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATER);
            LocalDateTime localDateTime = LocalDateTime.parse(time, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Method validate info user
     * @param info
     */
    public boolean validateRushOrderInfo(String info) {
        return checkString(info);
    }

    /**
     * Method validate instruction 
     * @param instruction 
     */
    public boolean validateRushOrderInstruction(String instruction) {
        return checkString(instruction);
    }
}