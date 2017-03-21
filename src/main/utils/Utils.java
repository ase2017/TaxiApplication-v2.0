package main.utils;

import java.util.Random;

/**
 * @author Jules
 */
public class Utils {

    private static Random random = new Random();

    public static int getIntBetween(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
