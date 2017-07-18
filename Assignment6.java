import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Assignment6 {
    /**
     * Rearranges given to make it the same as desired.
     *
     * @param given   initial version of the array of numbers.
     * @param desired final version of the array of numbers.
     * @return list of moves made during arrangement.
     */
    public static List<List<Integer>> rearrange(int[] given, int[] desired) {
        // Keys are values in given, values are their indexes in given.
        Map<Integer, Integer> indexMap = new HashMap<>();
        // Generated for testing purposes.
        List<List<Integer>> listOfMoves = new LinkedList<>();

        for (int i = 0; i < given.length; i++) {
            indexMap.put(given[i], i);
        }

        for (int i = 0; i < desired.length; i++) {
            // Already in its desired place.
            if (indexMap.get(desired[i]) == i) {
                continue;
            }

            int indexOf0 = indexMap.get(0);

            // If i isn't already empty.
            if (indexOf0 != i) {
                // Move the number in i to the empty slot so that i becomes empty.
                indexMap.put(0, i);
                indexMap.put(given[i], indexOf0);

                given[indexOf0] = given[i];
                given[i] = 0;

                printMove(i, indexOf0);

                List<Integer> moves = Arrays.asList(i, indexOf0);
                listOfMoves.add(moves);
            }

            int indexOfDesiredInGiven = indexMap.get(desired[i]);

            // Move the desired number to i.
            indexMap.put(0, indexOfDesiredInGiven);
            indexMap.put(desired[i], i);

            given[indexOfDesiredInGiven] = 0;
            given[i] = desired[i];

            printMove(indexOfDesiredInGiven, i);

            List<Integer> moves = Arrays.asList(indexOfDesiredInGiven, i);
            listOfMoves.add(moves);
        }

        return listOfMoves;
    }

    /**
     * Prints the move.
     * @param from index the move is made from.
     * @param to index the move is made to.
     */
    private static void printMove(int from, int to) {
        System.out.printf("move from %d to %d", from, to);
        System.out.println();
    }
}
