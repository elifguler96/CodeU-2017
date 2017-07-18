import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.elifguler.Assignment6.rearrange;
import static org.junit.jupiter.api.Assertions.*;

class Assignment6Test {
    @Test
    void rearrangeTestNormal() {
        int[] given = {1,2,0,3};
        int[] desired = {3,1,2,0};

        List<List<Integer>> list = rearrange(given, desired);
        assertEquals(list, Arrays.asList(
                Arrays.asList(0,2),
                Arrays.asList(3,0),
                Arrays.asList(1,3),
                Arrays.asList(2,1),
                Arrays.asList(3,2)
        ));
    }

    @Test
    void rearrangeTestArranged() {
        int[] given = {1,2,0,3};
        int[] desired = {1,2,0,3};

        List<List<Integer>> list = rearrange(given, desired);
        assertEquals(list, Collections.emptyList());
    }

    @Test
    void rearrangeTestEmpty() {
        int[] given = {};
        int[] desired = {};

        List<List<Integer>> list = rearrange(given, desired);
        assertEquals(list, Collections.emptyList());
    }

    @Test
    void rearrangeTestShift() {
        int[] given = {0,1,2,3};
        int[] desired = {1,2,3,0};

        List<List<Integer>> list = rearrange(given, desired);
        assertEquals(list, Arrays.asList(
                Arrays.asList(1,0),
                Arrays.asList(2,1),
                Arrays.asList(3,2)
        ));
    }
}