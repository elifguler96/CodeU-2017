import org.junit.jupiter.api.Test;

import static com.elifguler.Assignment4.findNumberOfIslandsDisjointSet;
import static com.elifguler.Assignment4.findNumberOfIslandsRecursive;
import static org.junit.jupiter.api.Assertions.*;

class Assignment4Test {
    boolean[][] MAP_NORMAL = {
            {false, true, false, true},
            {true, true, false, false},
            {false, false, true, false},
            {false, false, true, false}
    };

    boolean[][] MAP_ALL_TRUE = {
            {true, true, true, true},
            {true, true, true, true},
            {true, true, true, true},
            {true, true, true, true}
    };

    boolean[][] MAP_ALL_FALSE = {
            {false, false, false, false},
            {false, false, false, false},
            {false, false, false, false},
            {false, false, false, false}
    };

    boolean[][] MAP_1x1_FALSE = {
            {false}
    };

    boolean[][] MAP_1x1_TRUE = {
            {true}
    };

    boolean[][] MAP_NON_SQUARE = {
            {false, true, false, true},
            {true, true, true, false},
            {false, false, true, false}
    };

    boolean[][] MAP_BIG = {
            {false, true,   false,  true,   false,  false,  true,   true,   true},
            {true,  true,   true,   false,  true,   false,  false,  false,  true},
            {false, false,  true,   false,  false,  true,   true,   true,   false},
            {false, true,   false,  true,   false,  false,  true,   true,   true},
            {true,  true,   true,   false,  true,   false,  false,  false,  true},
            {false, false,  true,   false,  false,  true,   true,   true,   false},
            {false, true,   false,  true,   false,  false,  true,   true,   true},
            {true,  true,   true,   false,  true,   false,  false,  false,  true},
            {false, false,  true,   false,  false,  true,   true,   true,   false},
            {false, true,   false,  true,   false,  false,  true,   true,   true},
            {true,  true,   true,   false,  true,   false,  false,  false,  true},
            {false, false,  true,   true,   false,  true,   true,   true,   false},
            {false, true,   false,  true,   false,  false,  true,   true,   false},
            {true,  true,   true,   false,  true,   false,  false,  false,  true},
            {false, false,  true,   false,  false,  true,   true,   true,   false},
            {true,  true,   false,  true,   false,  false,  true,   true,   true},
            {true,  true,   true,   false,  true,   false,  false,  false,  true},
            {false, false,  true,   false,  false,  true,   true,   true,   false},
            {false, true,   false,  true,   false,  false,  true,   true,   true},
            {true,  true,   true,   true,   true,   true,   true,   true,   true},
            {false, false,  false,  false,  false,  false,  false,  false,  false},
    };

    @Test
    void findNumberOfIslandsRecursiveTestNormal() {
        assertEquals(3, findNumberOfIslandsRecursive(4, 4, MAP_NORMAL));
    }

    @Test
    void findNumberOfIslandsDisjointSetTestNormal() {
        assertEquals(3, findNumberOfIslandsDisjointSet(4, 4, MAP_NORMAL));
    }

    @Test
    void findNumberOfIslandsRecursiveTestAllTrue() {
        assertEquals(1, findNumberOfIslandsRecursive(4, 4, MAP_ALL_TRUE));
    }

    @Test
    void findNumberOfIslandsDisjointSetTestAllTrue() {
        assertEquals(1, findNumberOfIslandsDisjointSet(4, 4, MAP_ALL_TRUE));
    }

    @Test
    void findNumberOfIslandsRecursiveTestAllFalse() {
        assertEquals(0, findNumberOfIslandsRecursive(4, 4, MAP_ALL_FALSE));
    }

    @Test
    void findNumberOfIslandsDisjointSetTestAllFalse() {
        assertEquals(0, findNumberOfIslandsDisjointSet(4, 4, MAP_ALL_FALSE));
    }

    @Test
    void findNumberOfIslandsRecursiveTest1x1False() {
        assertEquals(0, findNumberOfIslandsRecursive(1, 1, MAP_1x1_FALSE));
    }

    @Test
    void findNumberOfIslandsDisjointSetTest1x1False() {
        assertEquals(0, findNumberOfIslandsDisjointSet(1, 1, MAP_1x1_FALSE));
    }

    @Test
    void findNumberOfIslandsRecursiveTest1x1True() {
        assertEquals(1, findNumberOfIslandsRecursive(1, 1, MAP_1x1_TRUE));
    }

    @Test
    void findNumberOfIslandsDisjointSetTest1x1True() {
        assertEquals(1, findNumberOfIslandsDisjointSet(1, 1, MAP_1x1_TRUE));
    }

    @Test
    void findNumberOfIslandsRecursiveTestNonSquare() {
        assertEquals(2, findNumberOfIslandsRecursive(3, 4, MAP_NON_SQUARE));
    }

    @Test
    void findNumberOfIslandsDisjointSetTestNonSquare() {
        assertEquals(2, findNumberOfIslandsDisjointSet(3, 4, MAP_NON_SQUARE));
    }

    @Test
    void findNumberOfIslandsRecursiveTestBigMap() {
        assertEquals(25, findNumberOfIslandsRecursive(21, 9, MAP_BIG));
    }

    @Test
    void findNumberOfIslandsDisjointSetTestBigMap() {
        assertEquals(25, findNumberOfIslandsDisjointSet(21, 9, MAP_BIG));
    }
}