import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class Assignment3Test {
    char[][] grid = {{'a', 'a', 'r'}, {'t', 'c', 'd'}};

    @Test
    void findAllWordsTest() {
        assertEquals(new HashSet<>(new Assignment3().findAllWords(2, 3, grid, new MyDictionary())),
                new HashSet<>(Arrays.asList("car", "card", "cat")));
    }

}