package org.example;

public class Calculator {
    public static double squareRoot(double x) {
        if (x < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of a negative number.");
        }
        return Math.sqrt(x);
    }

    public static long factorial(int x) {
        if (x < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative number.");
        }
        long fact = 1;
        for (int i = 1; i <= x; i++) {
            fact *= i;
        }
        return fact;
    }

    public static double naturalLogarithm(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("Natural logarithm is not defined for zero or negative numbers.");
        }
        return Math.log(x);
    }

    public static double power(double x, double y) {
        return Math.pow(x, y);
    }
}
