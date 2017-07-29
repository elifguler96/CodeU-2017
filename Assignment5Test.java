import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Set<Character> alphabet = new HashSet<>(Arrays.asList('A', 'T', 'R'));
        List<Character> list = inferAlphabet(words);
        assertTrue(alphabet.containsAll(list) && list.size() == 3);
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