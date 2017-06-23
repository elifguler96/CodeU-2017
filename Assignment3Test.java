import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static com.elifguler.Assignment3.findAllWords;
import static org.junit.jupiter.api.Assertions.assertEquals;


class Assignment3Test {

    @Test
    void findAllWordsTestNormal() {
        //Normal case
        char[][] grid = {{'a', 'a', 'r'}, {'t', 'c', 'd'}};
        assertEquals(new HashSet<>(findAllWords(2, 3, grid, new MyDictionary())),
                new HashSet<>(Arrays.asList("car", "card", "cat", "a")));
    }

    @Test
    void findAllWordsTestEmptyGrid() {
        //Empty grid case
        assertEquals(new HashSet<>(findAllWords(2, 3, new char[2][3], new MyDictionary())),
                new HashSet<>(Collections.emptyList()));
    }

    @Test
    void findAllWordsTestNoWordsFromDict() {
        //No words from dictionary case
        char[][] grid = {{'f', 'e', 'b'}, {'t', 'y', 'x'}};
        assertEquals(new HashSet<>(findAllWords(2, 3, grid, new MyDictionary())),
                new HashSet<>(Collections.emptyList()));
    }

    @Test
    void findAllWordsOneCharGrid() {
        //One character grid case
        char[][] grid = {{'a'}};
        assertEquals(new HashSet<>(findAllWords(1, 1, grid, new MyDictionary())),
                new HashSet<>(Collections.singletonList("a")));
    }

}