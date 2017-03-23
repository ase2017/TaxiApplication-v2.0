package main.utils;

import java.util.Random;

/**
 * Class that holds common functions
 * @author Jules
 */
public class Utils {

    private static Random random = new Random();

    public static int getIntBetween(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
