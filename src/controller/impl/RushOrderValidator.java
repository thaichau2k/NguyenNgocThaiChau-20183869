package controller.impl;

import controller.RushOrderInputValidator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RushOrderValidator implements RushOrderInputValidator {

    @Override
    public boolean isValidReceiveTime(String time, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime localDateTime = LocalDateTime.parse(time, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
