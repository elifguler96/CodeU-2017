import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.elifguler.Assignment6.rearrange;
import static com.elifguler.Assignment6.Move;
import static org.junit.jupiter.api.Assertions.*;

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
    public void rearrangeTestMoveEMpty() {
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
    
}
