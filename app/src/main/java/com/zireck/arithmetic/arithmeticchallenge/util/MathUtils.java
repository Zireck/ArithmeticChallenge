package com.zireck.arithmetic.arithmeticchallenge.util;

import java.util.Random;

public class MathUtils {

    public static int generateRandomNumber(int maxValue) {
        return generateRandomNumber(0, maxValue);
    }

    public static int generateRandomNumber(int minValue, int maxValue) {
        Random random = new Random();
        return random.nextInt(maxValue - minValue + 1) + minValue;
    }

    public static boolean coin() {
        return Math.random() > 0.5;
    }


    public static int gcd(int a, int b) {
        if (a > b) {
            return gcd(b, a);
        }

        while (a>0) {
            int m = b % a;
            b=a;
            a=m;
        }

        return b;
    }

}
