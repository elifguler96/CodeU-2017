package assignment6;

import org.junit.jupiter.api.Test;

import static assignment6.Assignment6.printListOfMoves;
import static assignment6.Assignment6.rearrange;
import static assignment6.Assignment6.rearrangeGreedy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Assignment6Test {

    @Test
    public void rearrangeTestNormal() {
        int[] given = {1, 2, 0, 3};
        int[] desired = {3, 1, 2, 0};

        List<Move> list = rearrange(given, desired);
        assertEquals(list, Arrays.asList(
                new Move(1, 0, 2),
                new Move(3, 3, 0),
                new Move(2, 1, 3),
                new Move(1, 2, 1),
                new Move(2, 3, 2)
        ));
    }

    @Test
    public void rearrangeTestMoveEmpty() {
        int[] given = {1, 2, 0, 3};
        int[] desired = {0, 1, 3, 2};
        
        List<Move> list = rearrange(given, desired);
        assertEquals(list, Arrays.asList(
                new Move(2, 1, 2),
                new Move(1, 0, 1),
                new Move(2, 2, 0),
                new Move(3, 3, 2),
                new Move(2, 0, 3)));
    }
    
    @Test
    public void rearrangeTestArranged() {
        int[] given = {1, 2, 0, 3};
        int[] desired = {1, 2, 0, 3};

        List<Move> list = rearrange(given, desired);
        assertEquals(list, Collections.emptyList());
    }

    @Test
    public void rearrangeTestEmpty() {
        int[] given = {};
        int[] desired = {};

        List<Move> list = rearrange(given, desired);
        assertEquals(list, Collections.emptyList());
    }

    @Test
    public void rearrangeTestShift() {
        int[] given = {0, 1, 2, 3};
        int[] desired = {1, 2, 3, 0};

        List<Move> list = rearrange(given, desired);
        assertEquals(list, Arrays.asList(
                new Move(1, 1, 0),
                new Move(2, 2, 1),
                new Move(3, 3, 2)
        ));
    }
    
    @Test
    public void rearrangeGreedyTestNormal() {
        int[] given = {1, 2, 0, 3};
        int[] desired = {3, 1, 2, 0};

        List<Move> list = rearrangeGreedy(given, desired);

        printListOfMoves(list);

        assertEquals(list, Arrays.asList(
                new Move(2, 1, 2),
                new Move(1, 0, 1),
                new Move(3, 3, 0)
        ));
    }

    @Test
    public void rearrangeGreedyTestMoveEmpty() {
        int[] given = {1, 2, 0, 3};
        int[] desired = {0, 1, 3, 2};
        
        List<Move> list = rearrangeGreedy(given, desired);
        assertEquals(list, Arrays.asList(
                new Move(3, 3, 2),
                new Move(2, 1, 3),
                new Move(1, 0, 1)));
    }
    
    @Test
    public void rearrangeGreedyTestArranged() {
        int[] given = {1, 2, 0, 3};
        int[] desired = {1, 2, 0, 3};

        List<Move> list = rearrangeGreedy(given, desired);
        assertEquals(list, Collections.emptyList());
    }

    @Test
    public void rearrangeGreedyTestEmpty() {
        int[] given = {};
        int[] desired = {};

        List<Move> list = rearrangeGreedy(given, desired);
        assertEquals(list, Collections.emptyList());
    }

    @Test
    public void rearrangeGreedyTestShift() {
        int[] given = {0, 1, 2, 3};
        int[] desired = {1, 2, 3, 0};

        List<Move> list = rearrangeGreedy(given, desired);
        assertEquals(list, Arrays.asList(
                new Move(1, 1, 0),
                new Move(2, 2, 1),
                new Move(3, 3, 2)
        ));
    }

    @Test
    public void rearrangeGreedyLongArray() {
        int[] given = {1, 2, 3, 4, 5, 6, 0};
        int[] desired = {3, 1, 6, 2, 4, 5, 0};

        List<Move> list = rearrangeGreedy(given, desired);
        // check we perform 7 moves
        assertEquals(list.size(), 7);
    }
    
}
