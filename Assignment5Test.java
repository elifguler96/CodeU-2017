import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.elifguler.Assignment5.inferAlphabet;
import static org.junit.jupiter.api.Assertions.*;

class Assignment5Test {
    @Test
    void inferAlphabetTestNormal() {
        List<String> words = Arrays.asList("ART", "RAT", "CAT", "CAR");
        List<Character> alphabet1 = Arrays.asList('A', 'T', 'R', 'C');
        List<Character> alphabet2 = Arrays.asList('T', 'A', 'R', 'C');
        assertTrue(inferAlphabet(words).equals(alphabet1) || inferAlphabet(words).equals(alphabet2));
    }

    @Test
    void inferAlphabetTestOneWord() {
        List<String> words = Arrays.asList("ART");
        List<Character> alphabet1 = Arrays.asList('A', 'T', 'R');
        List<Character> alphabet2 = Arrays.asList('T', 'A', 'R');
        List<Character> alphabet3 = Arrays.asList('A', 'R', 'T');
        List<Character> alphabet4 = Arrays.asList('R', 'A', 'T');
        List<Character> alphabet5 = Arrays.asList('R', 'T', 'A');
        List<Character> alphabet6 = Arrays.asList('T', 'R', 'A');
        assertTrue(inferAlphabet(words).equals(alphabet1) || inferAlphabet(words).equals(alphabet2) ||
                inferAlphabet(words).equals(alphabet3) || inferAlphabet(words).equals(alphabet4) ||
                inferAlphabet(words).equals(alphabet5) || inferAlphabet(words).equals(alphabet6));
    }

    @Test
    void inferAlphabetTestDifferentLength() {
        List<String> words = Arrays.asList("ART", "TAR", "TARD", "RAD", "DAT");
        List<Character> alphabet = Arrays.asList('A', 'T', 'R', 'D');
        assertEquals(alphabet, inferAlphabet(words));
    }

    @Test
    void inferAlphabetTestEmpty() {
        List<String> words = Collections.emptyList();
        List<Character> alphabet = Collections.emptyList();
        assertEquals(alphabet, inferAlphabet(words));
    }

    @Test
    void inferAlphabetTestDublicateWords() {
        List<String> words = Arrays.asList("ART", "ART", "RAT", "CAT", "CAT", "CAR");
        List<Character> alphabet1 = Arrays.asList('A', 'T', 'R', 'C');
        List<Character> alphabet2 = Arrays.asList('T', 'A', 'R', 'C');
        assertTrue(inferAlphabet(words).equals(alphabet1) || inferAlphabet(words).equals(alphabet2));
    }
}