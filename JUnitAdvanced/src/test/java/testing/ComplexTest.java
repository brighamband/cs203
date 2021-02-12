package testing;

import edu.byu.cs203.junit.advanced.Complex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComplexTest {
    Complex complex;

    @BeforeEach
    public void setup() {
        complex = new Complex();
    }

    @DisplayName("Factorial Test (Normal)")
    // Equivalence classes: 20 (max), 10 (normal), 0 (min)
    // (See next case for undefined case)
    @ParameterizedTest(name = "{0} factorial")
    @CsvSource({"20, 2432902008176640000", "10, 3628800", "0, 1"})
    public void testFactorial(long num, long expected) {
        assertEquals(expected, complex.factorial(num), "Incorrect factorial");
    }

    @DisplayName("Negative Factorial Test")
    // Equivalence classes: Small negative number, large negative number
    @ParameterizedTest(name = "{0} factorial")
    @ValueSource(ints = {-4, -240})
    public void testNegativeFactorial(long num) {
        assertThrows(ArithmeticException.class, ()-> complex.factorial(num), "Failed to throw ArithmeticException");
    }

    @DisplayName("Square Root Test")
    // Equivalence classes: Normal number, Negative number, NaN, positive infinity, 0
    @ParameterizedTest(name = "Square root of {0}")
    @CsvSource({"9, 3", "-6, NaN", "Infinity, Infinity", "0, 0"})
    public void testSquareRoot(double num, double expected) {
        assertEquals(expected, complex.squareRoot(num), "Incorrect square root");
    }

    @DisplayName("Natural Log Test")
    // Equivalence classes: Normal number, 0, negative number, coefficient is 1
    @ParameterizedTest(name = "Natural log of {0}")
    @CsvSource({"5, 1.6094366143332919", "0, -Infinity", "-6, NaN", "1000, 6.90775075384345"})
    public void testFactorial(double num, double expected) {
        assertEquals(expected, complex.ln(num), "Incorrect natural log");
    }
}
