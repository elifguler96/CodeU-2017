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
    public static List<Move> rearrange(int[] given, int[] desired) {
        List<Move> listOfMoves = new LinkedList<>();
        
        // Keys are values in given, values are their indexes in given.
        Map<Integer, Integer> indexMap = populateIndexMap(given);

        for (int i = 0; i < desired.length; i++) {
            // Already in its desired place.
            if (indexMap.get(desired[i]) == i) {
                continue;
            }

            // i should be the empty slot
            if (desired[i] == 0) {
                continue;
            }
            
            // If i isn't already empty.
            if (indexMap.get(0) != i) {
                // Move the number in i to the empty slot so that i becomes empty.
                Move emptyCurrentSlot = new Move(given[i], i, indexMap.get(0));
                
                listOfMoves.add(emptyCurrentSlot);
                move(emptyCurrentSlot, given, indexMap);
            }

            // Move the desired number to i.
            Move moveToCurrentSlot = new Move(given[indexMap.get(desired[i])], indexMap.get(desired[i]), i);

            listOfMoves.add(moveToCurrentSlot);
            move(moveToCurrentSlot, given, indexMap);
        }

        return listOfMoves;
    }

    /**
     * Executes a move both on the parking lot array and on the indexMap of the
     * cars.
     *
     * @param m the move to be executed
     * @param map the array (map) of the parking lot
     * @param indexMap the map from cars to slots
     */
    private static void move(Move m, int[] map, Map<Integer, Integer> indexMap) {
        indexMap.put(0, m.getFrom());
        indexMap.put(m.getCar(), m.getTo());

        map[m.getFrom()] = 0;
        map[m.getTo()] = m.getCar();
    }
    
    /**
     * Creates a map from car to slot.
     * 
     * @param map the array (map) of the parking lot
     * @return the map from cars to slots
     */
    private static Map<Integer, Integer> populateIndexMap(int[] map) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < map.length; i++) {
            indexMap.put(map[i], i);
        }
        return indexMap;
    }
    
    /**
     * Prints a list of moves.
     *
     * @param plan list of moves
     */
    public static void printListOfMoves(List<Move> plan) {
        plan.forEach((m) -> {
            printMove(m);
        });
    }
    
    /**
     * Prints the move.
     * 
     * @param m the move
     */
    private static void printMove(Move m) {
        System.out.printf("car %d moves from %d to %d", m.getCar(), m.getFrom(), m.getTo());
        System.out.println();
    }
    
}