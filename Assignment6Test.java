package assignment6;

import static assignment6.Assignment6.rearrange;
import static assignment6.Assignment6.rearrangeGreedy;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Assignment6Test {

    /**
     * Executes a plan (list of movements) on an arrangements of cars.
     *
     * @param given current map of the parking lot
     * @param plan list of movements to be executed on the parking lot
     */
    public static void executeMoves(int[] given, List<Move> plan) {
        plan.forEach((m) -> {
            given[m.getTo()] = m.getCar();
            given[m.getFrom()] = 0;
        });
    }
    
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

        List<Move> list = rearrangeGreedy(given, desired, Assignment6.GREEDY_ALG_TYPE.UNBLOCK_USING_SET);
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
        
        List<Move> list = rearrangeGreedy(given, desired, Assignment6.GREEDY_ALG_TYPE.UNBLOCK_USING_SET);
        assertEquals(list, Arrays.asList(
                new Move(3, 3, 2),
                new Move(2, 1, 3),
                new Move(1, 0, 1)));
    }
    
    @Test
    public void rearrangeGreedyTestArranged() {
        int[] given = {1, 2, 0, 3};
        int[] desired = {1, 2, 0, 3};

        List<Move> list = rearrangeGreedy(given, desired, Assignment6.GREEDY_ALG_TYPE.UNBLOCK_USING_SET);
        assertEquals(list, Collections.emptyList());
    }

    @Test
    public void rearrangeGreedyTestEmpty() {
        int[] given = {};
        int[] desired = {};

        List<Move> list = rearrangeGreedy(given, desired, Assignment6.GREEDY_ALG_TYPE.UNBLOCK_USING_SET);
        assertEquals(list, Collections.emptyList());
    }

    @Test
    public void rearrangeGreedyTestShift() {
        int[] given = {0, 1, 2, 3};
        int[] desired = {1, 2, 3, 0};

        List<Move> list = rearrangeGreedy(given, desired, Assignment6.GREEDY_ALG_TYPE.UNBLOCK_USING_SET);
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

        List<Move> list = rearrangeGreedy(given, desired, Assignment6.GREEDY_ALG_TYPE.UNBLOCK_USING_SET);
        // check we perform 7 moves
        assertEquals(list.size(), 7);
    }
    
    @Test
    public void testRearrangeRandom() {
        ArrayList<Integer> givenList = new ArrayList<>();
        ArrayList<Integer> desiredList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            givenList.add(i);
            desiredList.add(i);
        }
        java.util.Collections.shuffle(givenList);
        java.util.Collections.shuffle(desiredList);
        int[] given = givenList.stream().mapToInt(i->i).toArray();
        int[] givenEx = given.clone();
        int[] desired = desiredList.stream().mapToInt(i->i).toArray();
        List<Move> list = rearrange(given, desired);
        executeMoves(givenEx, list);
        
        assertArrayEquals(given, desired);
    }
    
    
    @Test
    public void testRearrangeGreedyRandom() {
        ArrayList<Integer> givenList = new ArrayList<>();
        ArrayList<Integer> desiredList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            givenList.add(i);
            desiredList.add(i);
        }
        java.util.Collections.shuffle(givenList);
        java.util.Collections.shuffle(desiredList);
        int[] given = givenList.stream().mapToInt(i->i).toArray();
        int[] givenEx = given.clone();
        int[] desired = desiredList.stream().mapToInt(i->i).toArray();
        List<Move> list = rearrangeGreedy(given, desired, Assignment6.GREEDY_ALG_TYPE.UNBLOCK_USING_SET);
        executeMoves(givenEx, list);
        
        assertArrayEquals(givenEx, desired);
    }

    @Test
    public void testCompareNoOfMoves() {
        ArrayList<Integer> givenList = new ArrayList<>();
        ArrayList<Integer> desiredList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            givenList.add(i);
            desiredList.add(i);
        }
        java.util.Collections.shuffle(givenList);
        java.util.Collections.shuffle(desiredList);
        int[] given = givenList.stream().mapToInt(i -> i).toArray();
        int[] givenGreedy = given.clone();
        int[] desired = desiredList.stream().mapToInt(i -> i).toArray();
        List<Move> list = rearrange(given, desired);
        List<Move> listGreedy = rearrangeGreedy(givenGreedy, desired, Assignment6.GREEDY_ALG_TYPE.UNBLOCK_USING_SET);

        assert(list.size() >= listGreedy.size());
    }

    @Test
    public void testStressCompareTimeAlmostInPlace() {
        int[] given = new int[100000];
        int[] desired = new int[100000];
        for (int i = 0; i < 100000; i++) {
            given[i] = i;
            desired[i] = i;
        }
        desired[90000] = 90003;
        desired[90001] = 90000;
        desired[90002] = 90001;
        desired[90003] = 90002;

        // warm up cache to avoid one alg being handicapped by cache misses
        rearrangeGreedy(given, desired, Assignment6.GREEDY_ALG_TYPE.UNBLOCK_USING_TRAVERSAL);

        //measure
        long start = System.nanoTime();
        List<Move> movesCaseTraversal = rearrangeGreedy(given, desired, Assignment6.GREEDY_ALG_TYPE.UNBLOCK_USING_TRAVERSAL);
        long end = System.nanoTime();
        long timeTraversalNano = end - start;

        start = System.nanoTime();
        List<Move> movesCaseSet = rearrangeGreedy(given, desired, Assignment6.GREEDY_ALG_TYPE.UNBLOCK_USING_SET);
        end = System.nanoTime();
        long timeSetNano = end - start;

        System.out.println(timeSetNano);
        System.out.println(timeTraversalNano);

        assert(movesCaseSet.size() <= movesCaseTraversal.size());
        assert(timeSetNano <= timeTraversalNano);

    }
}