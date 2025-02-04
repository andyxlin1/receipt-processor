package com.assessment.receipt_processor.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.assessment.receipt_processor.models.request.Item;

public class ReceiptUtils {
    /**
     * @description - calculates the number of alphanumeric characters in the input.
     * @param input - the input of the string
     * @return - the amount of alphanumeric characters in the string
     */
    public static int countAlphaNumeric(String input) {
        char[] characters = input.toCharArray();
        int count = 0;
                    
        for (int i = 0; i < characters.length; i++) {
            if (Character.isLetterOrDigit(characters[i])) {
                count++;
            }
        }
        return count;
    }

    /**
     * @description - checks if the given total is a round dollar amount.
     * @param total - the total amount
     * @return - returns 50 if the amount is a round dollar amount, otherwise 0
     */
    public static int isRoundDollarAmount(BigDecimal total) {
        try {
            if (total.stripTrailingZeros().scale() == 0) {
                return 50;
            }
            return 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * @description - checks if the given total is a multiple of 0.25.
     * @param total - the total amount
     * @return - returns 25 if the total is a multiple of 0.25, otherwise 0
     */
    public static int isMultipleOfQuarter(BigDecimal total) {
        try {
            if (total.remainder(new BigDecimal("0.25")).compareTo(BigDecimal.ZERO) == 0) {
                return 25;
            }
            return 0;

        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * @description - calculates the total points based on the given list of items.
     * @param items - the list of items to calculate points for
     * @return - the calculated points based on the items
     */
    public static int countItemPoints(List<Item> items) {
        int points = 0;
        for (int i = 0; i < items.size(); i++) {
            Item currItem = items.get(i);
            if (currItem.getShortDescription().trim().length() % 3 == 0) {
                BigDecimal currPrice = currItem.getPrice();
                BigDecimal factor = new BigDecimal("0.2");

                points += currPrice.multiply(factor).setScale(0, RoundingMode.CEILING).intValue();
            }
        }
        points += (items.size() / 2) * 5;
        return points;
    }

    /**
     * @description - checks if the purchase date is odd.
     * @param purchaseDate - the purchase date to check
     * @return - returns 6 if the day of the month is odd, otherwise 0
     */
    public static int isPurchaseDateOdd(LocalDate purchaseDate) {
        int purchaseDay = purchaseDate.getDayOfMonth();
        if (purchaseDay % 2 != 0) {
            return 6;
        }
        return 0;
    }

    /**
     * @description - checks if the purchase time is between 2:00 PM and 4:00 PM.
     * @param purchaseTime - the time of the purchase
     * @return - returns 10 if the purchase time is between 2:00 PM and 4:00 PM,
     *         otherwise 0
     */
    public static int isBetween2And4PM(LocalTime purchaseTime) {
        LocalTime start = LocalTime.of(14, 0); // 2:00 PM
        LocalTime end = LocalTime.of(16, 0); // 4:00 PM
        if (purchaseTime.isBefore(end) && purchaseTime.isAfter(start)) {
            return 10;
        }
        return 0;
    }

}
