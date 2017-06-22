import org.junit.jupiter.api.Test;

import static com.elifguler.Assignment4.findNumberOfIslands;
import static org.junit.jupiter.api.Assertions.*;

class Assignment4Test {
    @Test
    void findNumberOfIslandsTestNormal() {
        boolean[][] map = {
            {false, true, false, true},
            {true, true, false, false},
            {false, false, true, false},
            {false, false, true, false}
        };

        assertEquals(3, findNumberOfIslands(4, 4, map));
    }

    @Test
    void findNumberOfIslandsTestAllTrue() {
        boolean[][] map = {
                {true, true, true, true},
                {true, true, true, true},
                {true, true, true, true},
                {true, true, true, true}
        };

        assertEquals(1, findNumberOfIslands(4, 4, map));
    }

    @Test
    void findNumberOfIslandsTestAllFalse() {
        boolean[][] map = {
                {false, false, false, false},
                {false, false, false, false},
                {false, false, false, false},
                {false, false, false, false}
        };

        assertEquals(0, findNumberOfIslands(4, 4, map));
    }

    @Test
    void findNumberOfIslandsTest1x1False() {
        boolean[][] map = {
                {false}
        };

        assertEquals(0, findNumberOfIslands(1, 1, map));
    }

    @Test
    void findNumberOfIslandsTest1x1True() {
        boolean[][] map = {
                {true}
        };

        assertEquals(1, findNumberOfIslands(1, 1, map));
    }

    @Test
    void findNumberOfIslandsTestNonSquare() {
        boolean[][] map = {
                {false, true, false, true},
                {true, true, true, false},
                {false, false, true, false}
        };

        assertEquals(2, findNumberOfIslands(3, 4, map));
    }

    @Test
    void findNumberOfIslandsTestBigMap() {
        boolean[][] map = {
                {false, true, false, true, false, false, true, true, true},
                {true, true, true, false, true, false, false, false, true},
                {false, false, true, false, false, true, true, true, false},
                {false, true, false, true, false, false, true, true, true},
                {true, true, true, false, true, false, false, false, true},
                {false, false, true, false, false, true, true, true, false},
                {false, true, false, true, false, false, true, true, true},
                {true, true, true, false, true, false, false, false, true},
                {false, false, true, false, false, true, true, true, false},
                {false, true, false, true, false, false, true, true, true},
                {true, true, true, false, true, false, false, false, true},
                {false, false, true, true, false, true, true, true, false},
                {false, true, false, true, false, false, true, true, false},
                {true, true, true, false, true, false, false, false, true},
                {false, false, true, false, false, true, true, true, false},
                {true, true, false, true, false, false, true, true, true},
                {true, true, true, false, true, false, false, false, true},
                {false, false, true, false, false, true, true, true, false},
                {false, true, false, true, false, false, true, true, true},
                {true, true, true, true, true, true, true, true, true},
                {false, false, false, false, false, false, false, false, false},
        };

        assertEquals(25, findNumberOfIslands(21, 9, map));
    }
}