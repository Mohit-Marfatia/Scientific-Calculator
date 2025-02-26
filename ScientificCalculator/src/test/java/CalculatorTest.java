import static org.junit.Assert.*;
import org.junit.Test;
import org.example.Calculator;

public class CalculatorTest {

    @Test
    public void testSquareRoot() {
        assertEquals(4.0, Calculator.squareRoot(16), 0.001);
        assertEquals(5.0, Calculator.squareRoot(25), 0.001);
    }

    @Test
    public void testFactorial() {
        assertEquals(1, Calculator.factorial(0));
        assertEquals(120, Calculator.factorial(5));
        assertEquals(720, Calculator.factorial(6));
    }

    @Test
    public void testNaturalLogarithm() {
        assertEquals(0.0, Calculator.naturalLogarithm(1), 0.001);
        assertEquals(2.302, Calculator.naturalLogarithm(10), 0.001);
    }

    @Test
    public void testPower() {
        assertEquals(8.0, Calculator.power(2, 3), 0.001);
        assertEquals(1.0, Calculator.power(5, 0), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSquareRootNegative() {
        Calculator.squareRoot(-4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactorialNegative() {
        Calculator.factorial(-3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNaturalLogarithmNegative() {
        Calculator.naturalLogarithm(-5);
    }
}
