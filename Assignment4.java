public class Assignment4 {
    /**
     * Finds the number of islands in the map.
     *
     * @param rowNumber    number of rows
     * @param columnNumber number of columns
     * @param map          map of the area
     * @return number of islands, 0 if map is null
     */
    public static int findNumberOfIslands(int rowNumber, int columnNumber, boolean[][] map) {
        if (map == null) {
            return 0;
        }

        int numberOfIslands = 0;
        boolean[][] visited = new boolean[rowNumber][columnNumber];
        boolean isLand;

        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                isLand = map[i][j];

                if (isLand && !visited[i][j]) {
                    discoverIsland(i, j, map, visited);
                    numberOfIslands++;
                }
            }
        }

        return numberOfIslands;
    }

    /**
     * Discovers an island when given a part of it.
     *
     * @param row     row number of current tile in map
     * @param column  column number of current tile n map
     * @param map     map of the area
     * @param visited holds whether a tile is visited or not
     */
    private static void discoverIsland(int row, int column, boolean[][] map, boolean[][] visited) {
        visited[row][column] = true;

        // check up tile
        if (row > 0 && map[row - 1][column] && !visited[row - 1][column]) {
            discoverIsland(row - 1, column, map, visited);
        }

        // check down tile
        if (row < map.length - 1 && map[row + 1][column] && !visited[row + 1][column]) {
            discoverIsland(row + 1, column, map, visited);
        }

        // check left tile
        if (column > 0 && map[row][column - 1] && !visited[row][column - 1]) {
            discoverIsland(row, column - 1, map, visited);
        }

        // check right tile
        if (column < map[0].length - 1 && map[row][column + 1] && !visited[row][column + 1]) {
            discoverIsland(row, column + 1, map, visited);
        }
    }
}
