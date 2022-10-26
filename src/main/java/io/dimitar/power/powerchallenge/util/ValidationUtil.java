package io.dimitar.power.powerchallenge.util;

/**
 *
 */
public class ValidationUtil {

    public static void validatePostcode(int postcode) {
        if (postcode < 1000 || postcode > 9999) {
            throw new IllegalArgumentException("Postcode is invalid - " + postcode);
        }
    }

    public static void validateRange(int start, int end) {
        ValidationUtil.validatePostcode(start);
        ValidationUtil.validatePostcode(end);

        if (start > end) {
            throw new IllegalArgumentException("Range start (" + start +
                    ") must be lower than range end (" + end + ")");
        }
    }

    public static void validateCapacity(double capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity is invalid - " + capacity);
        }
    }

}
