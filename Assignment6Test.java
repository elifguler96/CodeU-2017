package assignment6;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static assignment6.Assignment6.rearrange;
import static assignment6.Assignment6.rearrangeGreedy;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Assignment6Test {
    private int[] given;
    private int[] desired;
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

    @Before
    public void setUp() {
        given = new int[]{1, 2, 0, 3};
        desired = new int[]{0, 1, 3, 2};
    }
    @Test
    public void rearrangeTestNormal() {
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
        int[] desired = {3, 1, 2, 0};

        List<Move> list = rearrangeGreedy(given, desired);
        assertEquals(list, Arrays.asList(
                new Move(2, 1, 2),
                new Move(1, 0, 1),
                new Move(3, 3, 0)
        ));
    }

    @Test
    public void rearrangeGreedyTestMoveEmpty() {
        List<Move> list = rearrangeGreedy(given, desired);
        assertEquals(list, Arrays.asList(
                new Move(3, 3, 2),
                new Move(2, 1, 3),
                new Move(1, 0, 1)));
    }
    
    @Test
    public void rearrangeGreedyTestArranged() {
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
        List<Move> list = rearrangeGreedy(given, desired);
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
        List<Move> listGreedy = rearrangeGreedy(givenGreedy, desired);

        assert(list.size() >= listGreedy.size());
    }
<<<<<<< HEAD

    @Test
    public void testStressCompareTimeAlmostInPlace() {
        int[] givenSet = new int[100000];
        int[] desiredSet = new int[100000];
        for (int i = 0; i < 100000; i++) {
            givenSet[i] = i;
            desiredSet[i] = i;
        }
        desiredSet[90000] = 90003;
        desiredSet[90001] = 90000;
        desiredSet[90002] = 90001;
        desiredSet[90003] = 90002;

        int[] givenTraversal = givenSet.clone();
        int[] desiredTraversal = desiredSet.clone();

        //measure
        long start = System.nanoTime();
        List<Move> movesCaseTraversal = rearrangeGreedy(givenTraversal, desiredTraversal, Assignment6.GREEDY_ALG_TYPE.UNBLOCK_USING_TRAVERSAL);
        long end = System.nanoTime();
        long timeTraversalNano = end - start;

        start = System.nanoTime();
        List<Move> movesCaseSet = rearrangeGreedy(givenSet, desiredSet, Assignment6.GREEDY_ALG_TYPE.UNBLOCK_USING_SET);
        end = System.nanoTime();
        long timeSetNano = end - start;

        System.out.println(timeSetNano);
        System.out.println(timeTraversalNano);

        System.out.println(movesCaseSet.size());
        System.out.println(movesCaseTraversal.size());

        assert(movesCaseSet.size() <= movesCaseTraversal.size());
        assert(timeSetNano <= timeTraversalNano);

    }
}
