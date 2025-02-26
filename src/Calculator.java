import java.util.Scanner;

public class Calculator {
    public static double squareRoot(double x) {
        if (x < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of a negative number.");
        }
        return Math.sqrt(x);
    }

    public static long factorial(int x) {
        if (x < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers.");
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
                        System.out.println("Square Root: " + squareRoot(num1));
                        break;
                    case 2:
                        System.out.print("Enter an integer: ");
                        int num2 = scanner.nextInt();
                        System.out.println("Factorial: " + factorial(num2));
                        break;
                    case 3:
                        System.out.print("Enter number: ");
                        double num3 = scanner.nextDouble();
                        System.out.println("Natural Logarithm: " + naturalLogarithm(num3));
                        break;
                    case 4:
                        System.out.print("Enter base number: ");
                        double base = scanner.nextDouble();
                        System.out.print("Enter exponent: ");
                        double exponent = scanner.nextDouble();
                        System.out.println("Power Result: " + power(base, exponent));
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
