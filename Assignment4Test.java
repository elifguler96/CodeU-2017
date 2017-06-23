import org.junit.jupiter.api.Test;

import static com.elifguler.Assignment4.findNumberOfIslandsDisjointSet;
import static com.elifguler.Assignment4.findNumberOfIslandsRecursive;
import static org.junit.jupiter.api.Assertions.*;

class Assignment4Test {
    @Test
    void findNumberOfIslandsRecursiveTestNormal() {
        boolean[][] map = {
            {false, true, false, true},
            {true, true, false, false},
            {false, false, true, false},
            {false, false, true, false}
        };

        assertEquals(3, findNumberOfIslandsRecursive(4, 4, map));
    }

    @Test
    void findNumberOfIslandsDisjointSetTestNormal() {
        boolean[][] map = {
                {false, true, false, true},
                {true, true, false, false},
                {false, false, true, false},
                {false, false, true, false}
        };

        assertEquals(3, findNumberOfIslandsDisjointSet(4, 4, map));
    }

    @Test
    void findNumberOfIslandsRecursiveTestAllTrue() {
        boolean[][] map = {
                {true, true, true, true},
                {true, true, true, true},
                {true, true, true, true},
                {true, true, true, true}
        };

        assertEquals(1, findNumberOfIslandsRecursive(4, 4, map));
    }

    @Test
    void findNumberOfIslandsDisjointSetTestAllTrue() {
        boolean[][] map = {
                {true, true, true, true},
                {true, true, true, true},
                {true, true, true, true},
                {true, true, true, true}
        };

        assertEquals(1, findNumberOfIslandsDisjointSet(4, 4, map));
    }

    @Test
    void findNumberOfIslandsRecursiveTestAllFalse() {
        boolean[][] map = {
                {false, false, false, false},
                {false, false, false, false},
                {false, false, false, false},
                {false, false, false, false}
        };

        assertEquals(0, findNumberOfIslandsRecursive(4, 4, map));
    }

    @Test
    void findNumberOfIslandsDisjointSetTestAllFalse() {
        boolean[][] map = {
                {false, false, false, false},
                {false, false, false, false},
                {false, false, false, false},
                {false, false, false, false}
        };

        assertEquals(0, findNumberOfIslandsDisjointSet(4, 4, map));
    }

    @Test
    void findNumberOfIslandsRecursiveTest1x1False() {
        boolean[][] map = {
                {false}
        };

        assertEquals(0, findNumberOfIslandsRecursive(1, 1, map));
    }

    @Test
    void findNumberOfIslandsDisjointSetTest1x1False() {
        boolean[][] map = {
                {false}
        };

        assertEquals(0, findNumberOfIslandsDisjointSet(1, 1, map));
    }

    @Test
    void findNumberOfIslandsRecursiveTest1x1True() {
        boolean[][] map = {
                {true}
        };

        assertEquals(1, findNumberOfIslandsRecursive(1, 1, map));
    }

    @Test
    void findNumberOfIslandsDisjointSetTest1x1True() {
        boolean[][] map = {
                {true}
        };

        assertEquals(1, findNumberOfIslandsDisjointSet(1, 1, map));
    }

    @Test
    void findNumberOfIslandsRecursiveTestNonSquare() {
        boolean[][] map = {
                {false, true, false, true},
                {true, true, true, false},
                {false, false, true, false}
        };

        assertEquals(2, findNumberOfIslandsRecursive(3, 4, map));
    }

    @Test
    void findNumberOfIslandsDisjointSetTestNonSquare() {
        boolean[][] map = {
                {false, true, false, true},
                {true, true, true, false},
                {false, false, true, false}
        };

        assertEquals(2, findNumberOfIslandsDisjointSet(3, 4, map));
    }

    @Test
    void findNumberOfIslandsRecursiveTestBigMap() {
        boolean[][] map = {
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

        assertEquals(25, findNumberOfIslandsRecursive(21, 9, map));
    }

    @Test
    void findNumberOfIslandsDisjointSetTestBigMap() {
        boolean[][] map = {
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

        assertEquals(25, findNumberOfIslandsDisjointSet(21, 9, map));
    }
}