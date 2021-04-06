package edu.byu.cs.sonar;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testMainPass() {
        String[] args = {"readMe1.txt", "readMe2.txt", "readMe3.txt", "5"};

        assertDoesNotThrow(() -> Main.main(args), "Arguments are invalid.");
    }

    @Test
    void testMainFail() {
        String[] args = {"bad1.txt", "bad2.txt", "bad3.txt", "5"};

        // Fix this check
        assertDoesNotThrow(() -> Main.main(args), "Arguments are valid.");
    }
}