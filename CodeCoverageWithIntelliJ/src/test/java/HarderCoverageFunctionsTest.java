import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HarderCoverageFunctionsTest {
    HarderCoverageFunctions testClass;

    @BeforeEach
    public void setUp() throws Exception {
        testClass = new HarderCoverageFunctions();
    }

    @Test
    public void testCastSpells() {
        assertEquals("The spell fizzles in front of you.", testClass.castSpells(1, -1, 1, 1));
        assertEquals("The spell destroys the monster!", testClass.castSpells(0, 1, 1, 1));
        assertEquals("The spell zaps the monster!", testClass.castSpells(5, 1, 1, 1));
        assertEquals("You win!", testClass.castSpells(0,1, 1, 0));
        assertEquals("Monster is still alive", testClass.castSpells(5,1, 1, 0));
    }


    @Test
    public void testTruthFinder(){
        assertEquals("YOU FOUND THE TRUTH!", testClass.truthFinder(true, true, true));
        assertEquals("Well I guess that is true too", testClass.truthFinder(true, false, true));
        assertEquals("Well that might be true", testClass.truthFinder(true, false, false));
        assertEquals("Are you even trying to find the truth?", testClass.truthFinder(false, false, true));
    }
}