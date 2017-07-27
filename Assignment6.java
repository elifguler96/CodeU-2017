package assignment6;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashSet;

public class Assignment6 {
    /**
     * Rearranges given to make it the same as desired.
     * O(n) time complexity, O(n) space complexity.
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
                listOfMoves.add(move(given[i], given, indexMap));
            }

            // Move the desired number to i.
            listOfMoves.add(move(desired[i], given, indexMap));
        }

        return listOfMoves;
    }
    
    /**
     * Rearranges given to make it the same as desired.
     * Greedy algorithm to find the rearrangement with the least number of moves.
     * O(n^2) worst case time complexity, O(n) space complexity.
     *
     * @param given   initial version of the array of numbers.
     * @param desired final version of the array of numbers.
     * @return list of moves made during arrangement.
     */
    public static List<Move> rearrangeGreedy(int[] given, int[] desired) {
        List<Move> listOfMoves = new LinkedList<>();
        
        // Keys are values in given, values are their indexes in given.
        Map<Integer, Integer> indexMap = populateIndexMap(given);

        // All cars are initially candidates
        HashSet<Integer> misplacedCars = populateMisplacedCars(given, desired);

        for (int car = nextCarToMove(given, desired, indexMap, misplacedCars); car > 0;
                car = nextCarToMove(given, desired, indexMap, misplacedCars)) {
            listOfMoves.add(move(car, given, indexMap));
        }
        
        return listOfMoves;
    }
    
    /**
     * Computes (greedily) the best choice of car to be moved next.
     * O(n) worst case time complexity.
     * 
     * @param given    initial version of the array of numbers.
     * @param desired  final version of the array of numbers.
     * @param indexMap the map from cars to slots
     * @return next car to be moved (0 in case of rearranged array)
     */
    private static int nextCarToMove(int[] given, int[] desired, Map<Integer, Integer> indexMap, HashSet<Integer> misplacedCars) {
        //arrays of length 0
        if (given.length == 0) {
            return 0;
        }
        
        //car belonging to the current empty slot
        if (desired[indexMap.get(0)] != 0) {
            // remove car from set of misplaced cars
            int nextCarToMove = desired[indexMap.get(0)];
            misplacedCars.remove(nextCarToMove);
            return nextCarToMove;

        }

        //empty lot in its desired place => deblock the algorithm
        // pick a random car from set of misplaced cars
        if (misplacedCars.size() > 0) {
            return misplacedCars.iterator().next();
        }

        //given array is rearranged
        return 0;
    }

    /**
     * Executes a move both on the parking lot array and on the indexMap of the
     * cars.
     *
     * @param car the car to be moved
     * @param map the array (map) of the parking lot
     * @param indexMap the map from cars to slots
     */
    private static Move move(int car, int[] map, Map<Integer, Integer> indexMap) {
        Move m = new Move(car, indexMap.get(car), indexMap.get(0));
        indexMap.put(0, m.getFrom());
        indexMap.put(m.getCar(), m.getTo());

        map[m.getFrom()] = 0;
        map[m.getTo()] = m.getCar();
        return m;
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
     * Creates a hash set of misplaced cars comparing input and desired output.
     *
     * @param given the input array
     * @param desired the output array
     * @return the set of cars that are misplaced in the input array compared to the desired output.
     */
    private static HashSet<Integer> populateMisplacedCars(int[] given, int[] desired) {
        HashSet<Integer> misplacedCars = new HashSet<>();
        for (int i = 0; i < given.length; i++) {
            if (given[i] != desired[i] && given[i] != 0) {
                misplacedCars.add(given[i]);
            }
        }
        return misplacedCars;
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
        System.out.println(m.toString());
    }
    
}