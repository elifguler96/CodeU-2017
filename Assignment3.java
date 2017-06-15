import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Assignment3 {
    /**
    * Finds all the words in a grid of characters
    *
    * @param  rowNumber    number of rows in the grid
    * @param  columnNumber number of columns in the grid
    * @param  grid         gird of characters
    * @param  dictionary   dictionary containing the words
    * @return              the list of words found in the grid, null if no words found
    */
    public static List<String> findAllWords(int rowNumber, int columnNumber, char[][] grid, MyDictionary dictionary) {
        Set<String> setOfWords = new HashSet<>();
        boolean[][] visited = new boolean[rowNumber][columnNumber];
        String s = "";

        // Sends each character's row and column numbers to formWords
        // and resets the visited array afterwards
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                formWords(grid, i, j, visited, s, dictionary, setOfWords);
                visited = new boolean[rowNumber][columnNumber];
            }
        }

        List<String> words = new ArrayList<>();
        words.addAll(setOfWords);

        return words;
    }

    /**
    * Forms the words in s using recursion
    *
    * @param  grid       gird of characters
    * @param  row        number of rows in the grid
    * @param  column     number of columns in the grid
    * @param  visited    holds whether a character is visited
    * @param  s          forms the word
    * @param  dictionary dictionary containing the words
    */
    private static void formWords(char[][] grid, int row, int column, boolean[][] visited, String s,
                           MyDictionary dictionary, Set<String> setOfWords) {
        // If the new s is not a prefix, we don't have to keep looking
        if (!dictionary.isPrefix(s + grid[row][column])) {
            return;
        }

        visited[row][column] = true;
        s += grid[row][column];

        if (dictionary.isWord(s)) {
            setOfWords.add(s);
        }

        // Checks all 8 directions and resets visited to previous case
        for (int i = row - 1; i <= row + 1 && i < grid.length; i++) {
            for (int j = column - 1; j <= column + 1 && j < grid[0].length; j++) {
                if (i >= 0 && j >= 0 && !visited[i][j]) {
                    formWords(grid, i, j, visited, s, dictionary, setOfWords);
                    visited[i][j] = false;
                }
            }
        }
    }
}
