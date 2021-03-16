import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {
    private Calculator calculator;
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void testAdd() {
        assertEquals(5, calculator.add(2, 3));
        assertEquals(9, calculator.add(4, 5));
    }

    @Test
    void testSubtract() {
        assertEquals(3, calculator.subtract(7, 4));
        assertEquals(12, calculator.subtract(20, 8));
    }

    @Test
    void testPower_posBasePosExponent() {
        assertEquals(16, calculator.power(4, 2));
        assertEquals(27, calculator.power(3, 3));
    }

    @Test
    void testPower_posBaseZeroExponent() {
        assertEquals(1, calculator.power(3, 0));
    }

    @Test
    void testPower_negBaseZeroExponent() {
        assertEquals(1, calculator.power(3, 0));
    }

    @Test
    void testPower_posBaseNegExponent() {
        assertEquals(0.04, calculator.power(5, -2), 0.001);
        assertEquals(0.0625, calculator.power(4, -2), 0.001);
    }

    @Test
    void testPower_negBasePosExponent() {
        assertEquals(9, calculator.power(-3, 2));
        assertEquals(-27, calculator.power(-3, 3));
    }

    @Test
    void testPower_negBaseNegExponent() {
        assertEquals(0.04, calculator.power(-5, -2), 0.001);
    }

    @Test
    void testPower_zeroBasePosExponent() {
        assertEquals(0, calculator.power(0, 5));
    }

    @Test
    void testPower_zeroBaseZeroExponent() {
        assertThrows(ArithmeticException.class, () -> calculator.power(0, 0));
    }

    @Test
    void testPower_zeroBaseNegExponent() {
        assertThrows(ArithmeticException.class, () -> calculator.power(0, -5));
    }
}
