package assignment6;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static assignment6.Assignment6.rearrange;
import static assignment6.Assignment6.rearrangeGreedy;
import java.util.Collection;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
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
    
    /**
     * Creates a random permutation of [0..length]
     * 
     * @param length length of the array
     * @return random array of length length
     */
    public static Object[] createRandomArray(int length) {
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            list.add(i);
        }
        java.util.Collections.shuffle(list);
        return list.stream().map(i -> i).toArray();
    }
    
    /**
     * Creates increasing array of integers from 0 to length-1.
     * 
     * @param length length of the array
     * @return identity array
     */
    public static Object[] createIdentityArray(int length) {
        Object[] array = new Object[length];
        for (int i = 0; i < length; i++) {
            array[i] = i;
        }
        return array;
    }
    
    /**
     * Shuffles 4 elements of an identity array starting at offset.
     * 
     * @param length length of the array
     * @param offs index of the element where the shuffle starts
     * @return identity array with 4 shuffled elements
     */
    public static Object[] shuffleIdentityArray(int length, int offs) {
        Object[] array = createIdentityArray(length);
        array[offs] = offs + 3;
        array[offs + 1] = offs;
        array[offs + 2] = offs + 1;
        array[offs + 3] = offs + 2;
        return array;
    }
    
    /**
     * Creates an array where the elements are misplaced pair by pair.
     * Only works for arrays of odd lengths.
     * 
     * @param length length of the array
     * @return pair by pair shuffled array
     */
    public static Object[] shuffleInPairs(int length) {
        Object[] array = new Object[length];
        array[0] = 0;
        for (int i = 1; i < length; i++) {
            if (i % 2 == 0) {
                array[i] = i - 1;
            } else {
                array[i] = i + 1;
            }
        }
        return array;
    }
    
    @Parameters(name = "{4}")
    public static Collection<Object[][]> data() {
        return Arrays.asList(new Object[][][]{
            { //Example
                {1, 2, 0, 3}, //given
                {3, 1, 2, 0}, //desired
                { //straightforward alg
                    new Move(1, 0, 2),
                    new Move(3, 3, 0),
                    new Move(2, 1, 3),
                    new Move(1, 2, 1),
                    new Move(2, 3, 2)
                },
                { //greedy alg
                    new Move(2, 1, 2),
                    new Move(1, 0, 1),
                    new Move(3, 3, 0)
                },
                {"Example"}
            },
            { //Move Empty Slot
                {1, 2, 0, 3}, //given
                {0, 1, 3, 2}, //desired
                { //straightforward alg
                    new Move(2, 1, 2),
                    new Move(1, 0, 1),
                    new Move(2, 2, 0),
                    new Move(3, 3, 2),
                    new Move(2, 0, 3)
                },
                { //greedy alg
                    new Move(3, 3, 2),
                    new Move(2, 1, 3),
                    new Move(1, 0, 1)
                },
                {"Move Empty Slot"}
            },
            { //Arranged
                {1, 2, 0, 3}, //given
                {1, 2, 0, 3}, //desired
                {}, //straightforward alg
                {}, //greedy alg
                {"Arranged"}
            },
            { //Empty
                {}, //given
                {}, //desired
                {}, //straightforward alg
                {}, //greedy alg
                {"Empty"}
            },
            { //Random
                createRandomArray(1000000), //given
                createRandomArray(1000000), //desired
                null, //straightforward alg - not known
                null, //greedy alg - not known
                {"Random"}
            },
            { //Almost Arranged
                createIdentityArray(1000000), //given
                shuffleIdentityArray(1000000, 900000), //desired
                null, //straightforward alg - not known
                null, //greedy alg - not known
                {"Almost Arranged"}
            },
            { //Shuffled
                createIdentityArray(1000001), //given
                shuffleInPairs(1000001), //desired
                null, //straightforward alg - not known
                null, //greedy alg - not known
                {"Shuffled"}
            }
        });
    }

    @Parameter
    public Object[] given;
    private int[] givenArr;

    @Parameter(1)
    public Object[] desired;
    private int[] desiredArr;

    @Parameter(2)
    public Object[] moves;
    private ArrayList<Move> movesList;
    
    @Parameter(3)
    public Object[] movesGreedy;
    private ArrayList<Move> movesListGreedy;
    
    @Parameter(4)
    public Object[] name;
    private String nameString;

    @Before
    public void setUp() {
        this.givenArr = Arrays.stream(this.given).mapToInt(t -> (int) t).toArray();
        this.desiredArr = Arrays.stream(this.desired).mapToInt(t -> (int) t).toArray();
        if (this.moves != null && this.movesGreedy != null) {
            this.movesList = new ArrayList<>();
            for (Object m : this.moves) {
                this.movesList.add((Move) m);
            }
            this.movesListGreedy = new ArrayList<>();
            for (Object m : this.movesGreedy) {
                this.movesListGreedy.add((Move) m);
            }
        }
        this.nameString = this.name[0].toString();
        System.out.print(this.nameString + " ");
        Assignment6.alg = Assignment6.GREEDY_ALG_TYPE.UNBLOCK_USING_SET;
    }
    
    @Test
    public void testRearrange() {
        System.out.println("rearrange");
        if (this.movesList != null) {
            assertEquals(this.movesList, rearrange(this.givenArr, this.desiredArr));
        }
    }
    
    @Test
    public void testRearrangeGreedy() {
        System.out.println("rearrangeGreedy");
        if (this.movesListGreedy != null) {
            assertEquals(this.movesListGreedy, rearrangeGreedy(this.givenArr, this.desiredArr));
        }
    }
    
    @Test
    public void testExecuteRearrange() {
        System.out.println("executeRearrange");
        int[] givenCopy = this.givenArr.clone();
        executeMoves(givenCopy, rearrange(this.givenArr, this.desiredArr));
        assertArrayEquals(givenCopy, this.desiredArr);
    }
    
    @Test
    public void testExecuteRearrangeGreedy() {
        System.out.println("executeRearrangeGreedy");
        int[] givenCopy = this.givenArr.clone();
        executeMoves(givenCopy, rearrangeGreedy(this.givenArr, this.desiredArr));
        assertArrayEquals(givenCopy, this.desiredArr);
    }
    
    @Test
    public void compareNoOfSteps() {
        System.out.println("compareNoOfSteps");
        int[] givenCopy = this.givenArr.clone();
        List<Move> listGreedy = rearrangeGreedy(this.givenArr, this.desiredArr);
        List<Move> list = rearrange(givenCopy, this.desiredArr);
        assert(list.size() >= listGreedy.size());
    }

    @Test
    public void testCompareTime() {
        System.out.println("compareTime");
        if (this.movesList == null && this.movesListGreedy == null) {
            int[] givenCopy = this.givenArr.clone();

            //measure
            long start = System.nanoTime();
            Assignment6.alg = Assignment6.GREEDY_ALG_TYPE.UNBLOCK_USING_TRAVERSAL;
            rearrangeGreedy(this.givenArr, this.desiredArr);
            long end = System.nanoTime();
            long timeTraversalNano = end - start;

            start = System.nanoTime();
            Assignment6.alg = Assignment6.GREEDY_ALG_TYPE.UNBLOCK_USING_SET;
            rearrangeGreedy(givenCopy, this.desiredArr);
            end = System.nanoTime();
            long timeSetNano = end - start;

            assert(timeSetNano <= timeTraversalNano);
        }
    }
}