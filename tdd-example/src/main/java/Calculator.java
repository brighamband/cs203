public class Calculator {
    public int add(int x, int y) {
        return x + y;
    }

    public int subtract(int x, int y) {
        return x - y;
    }

    public double power(double base, int exponent) {
        if (base == 0 && exponent <= 0) {
            throw new ArithmeticException("Not a number");
        }

        if (exponent < 0) {
            base = 1.0 / base;
            exponent = exponent * -1;
        }

        double result = 1.0;

        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }
}
