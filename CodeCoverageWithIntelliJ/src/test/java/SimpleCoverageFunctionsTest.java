import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleCoverageFunctionsTest {
    SimpleCoverageFunctions testClass;

    @BeforeEach
    public void setUp() throws Exception {
        testClass = new SimpleCoverageFunctions();
    }

    @Test
    public void testAddTwoNum(){
        int sum = testClass.addTwoNum(1, 2);
        assertEquals(3, sum);
    }

    @Test
    void testReturnLargest() {
        assertEquals(7, testClass.returnLargest(7, 5));
        // You still need to write at least one more test for full branch coverage
        assertEquals(7, testClass.returnLargest(5, 7));
    }

    @Test
    void testDoWeirdStuff() {
        assertEquals(2, testClass.doWeirdStuff(1, 2, 3));
        assertEquals(3, testClass.doWeirdStuff(6, 4, 12));
        assertEquals(10, testClass.doWeirdStuff(12,10,7));
    }
}