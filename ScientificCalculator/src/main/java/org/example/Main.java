package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nScientific Calculator - Choose an Operation:");
            System.out.println("1. Square Root (√x)");
            System.out.println("2. Factorial (x!)");
            System.out.println("3. Natural Logarithm (ln(x))");
            System.out.println("4. Power Function (x^y)");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter number: ");
                        double num1 = scanner.nextDouble();
                        System.out.println("Square Root: " + Calculator.squareRoot(num1));
                        break;
                    case 2:
                        System.out.print("Enter an integer: ");
                        int num2 = scanner.nextInt();
                        System.out.println("Factorial: " + Calculator.factorial(num2));
                        break;
                    case 3:
                        System.out.print("Enter number: ");
                        double num3 = scanner.nextDouble();
                        System.out.println("Natural Logarithm: " + Calculator.naturalLogarithm(num3));
                        break;
                    case 4:
                        System.out.print("Enter base number: ");
                        double base = scanner.nextDouble();
                        System.out.print("Enter exponent: ");
                        double exponent = scanner.nextDouble();
                        System.out.println("Power Result: " + Calculator.power(base, exponent));
                        break;
                    case 5:
                        System.out.println("Exiting Calculator. Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice! Please select a valid option.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

}
