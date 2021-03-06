public class Assignment4 {
    /**
     * Finds the number of islands in the map using recursion.
     *
     * @param row    number of rows
     * @param column number of columns
     * @param map    map of the area
     * @return number of islands, 0 if map is null
     */
    public static int findNumberOfIslandsRecursive(int row, int column, boolean[][] map) {
        if (map == null) {
            return 0;
        }

        int numberOfIslands = 0;
        boolean[][] visited = new boolean[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                boolean isLand = map[i][j];

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

    /**
     * Finds the number of islands in the map using disjoint set.
     *
     * @param row    number of rows
     * @param column number of columns
     * @param map    map of the area
     * @return number of islands, 0 if map is null
     */
    public static int findNumberOfIslandsDisjointSet(int row, int column, boolean[][] map) {
        if (map == null) {
            return 0;
        }

        DisjointSet disjointSet = new DisjointSet(row * column);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                boolean isLand = map[i][j];

                if (isLand) {
                    disjointSet.makeSet(findId(i, j, column));

                    // union with land above tile
                    if (i > 0 && map[i - 1][j]) {
                        disjointSet.union(findId(i, j, column), findId(i - 1, j, column));
                    }

                    // union with land to the left of tile
                    if (j > 0 && map[i][j - 1]) {
                        disjointSet.union(findId(i, j, column), findId(i, j - 1, column));
                    }
                }
            }
        }

        return disjointSet.getCount();

    }

    /**
     * Calculates the id of a tile in the map.
     * Ids of tiles start from 1 and go up by 1 accordingly.
     *
     * @param row       row number of the tile
     * @param column    column number of the tile
     * @param mapColumn column number of the map
     * @return id of tile
     */
    private static int findId(int row, int column, int mapColumn) {
        return row * mapColumn + column + 1;
    }
}
