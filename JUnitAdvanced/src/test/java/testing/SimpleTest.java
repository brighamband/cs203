package testing;

import edu.byu.cs203.junit.advanced.Simple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for Simple.java")
public class SimpleTest {
    Simple simple;

    @BeforeEach
    public void setup() {
        simple = new Simple();
    }

    @DisplayName("Addition Test")
    // Edge cases: positive + positive, negative + negative, positive + negative, number + 0
    @ParameterizedTest(name = "{0} plus {1}")
    @CsvSource({"5, 10, 15", "-7, -8, -15", "5, -8, -3", "9, 0, 9"})
    public void testAdd(int num1, int num2, int expected) {
        assertEquals(expected, simple.add(num1, num2), "Incorrect sum");
    }

    @DisplayName("Subtraction Test")
    // Edge cases: positive - positive, negative - negative, positive - negative, negative - positive, number - 0
    @ParameterizedTest(name = "{0} minus {1}")
    @CsvSource({"10, 6, 4", "-7, -11, 4", "5, -12, 17", "-9, 1, -10", "2, 0, 2"})
    public void testSubstract(int num1, int num2, int expected) {
        assertEquals(expected, simple.subtract(num1, num2), "Incorrect difference");
    }

    @DisplayName("Multiplication Test")
    // Edge cases: positive * positive, negative * negative, positive * negative, negative * positive, number * 0
    @ParameterizedTest(name = "{0} minus {1}")
    @CsvSource({"6, 7, 42", "-3, -6, 18", "2, -7, -14", "-6, 8, -48", "3, 0, 0"})
    public void testMultiply(int num1, int num2, int expected) {
        assertEquals(expected, simple.multiply(num1, num2), "Incorrect product");
    }

    @DisplayName("Division Test (Normal)")
    // Edge cases: positive / positive, negative / negative, positive / negative, negative / positive, 0 / number
    // (See next case for undefined case)
    @ParameterizedTest(name = "{0} divided by {1}")
    @CsvSource({"8, 4, 2", "-12, -3, 4", "5, -1, -5", "-9, 3, -3", "0, 12, 0"})
    public void testDivide(int num1, int num2, int expected) {
        assertEquals(expected, simple.divide(num1, num2), "Incorrect quotient");
    }

    @DisplayName("Divide by Zero Test")
    // Edge cases: positive / 0, negative / 0
    @ParameterizedTest(name = "{0} divided by 0")
    @ValueSource(ints = {20, -13})
    public void testDivideByZero(int num1) {
        assertThrows(ArithmeticException.class, ()-> simple.divide(num1, 0), "Failed to throw ArithmeticException");
    }

    @DisplayName("Power Test")
    // Edge cases: positive ^ negative, negative ^ negative, number ^ 0
    @ParameterizedTest(name = "{0} to the {1}")
    @CsvSource({"5, -2, 1", "-6, -2, 1", "6, 0, 1"})
    public void testPower(int num1, int num2, int expected) {
        assertEquals(expected, simple.power(num1, num2), "Incorrect power");
    }
}
