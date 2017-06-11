import java.util.*;

public class Assignment3 {
    private Set<String> setOfWords = new HashSet<>();

    public List<String> findAllWords(int rowNumber, int columnNumber, char[][] grid, MyDictionary dictionary) {
        boolean[][] visited = new boolean[rowNumber][columnNumber];
        String s = "";

        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                findWordsStartingWithSpecificChar(grid, i, j, visited, s, dictionary);
                visited = new boolean[rowNumber][columnNumber];
            }
        }

        List<String> words = new ArrayList<>();
        words.addAll(setOfWords);

        return words;
    }

    private void findWordsStartingWithSpecificChar(char[][] grid, int row, int column, boolean[][] visited, String s,
                                                   MyDictionary dictionary) {
        if (!dictionary.isPrefix(s + grid[row][column])) {
            return;
        }

        visited[row][column] = true;
        s += grid[row][column];

        if (dictionary.isWord(s)) {
            setOfWords.add(s);
        }

        for (int i = row - 1; i <= row + 1 && i < grid.length; i++) {
            for (int j = column - 1; j <= column + 1 && j < grid[0].length; j++) {
                if (i >= 0 && j >= 0 && !visited[i][j]) {
                    findWordsStartingWithSpecificChar(grid, i, j, visited, s, dictionary);
                    visited[i][j] = false;
                }
            }
        }
    }
}
