import java.util.HashSet;
import java.util.Set;

public class DisjointSet {
    private int[] parent;
    private int[] rank;

    public DisjointSet(int numberOfTiles) {
        // index 0 is left blank because ids of tiles start from 1
        parent = new int[numberOfTiles + 1];
        rank = new int[numberOfTiles + 1];
    }

    public void makeSet(int id) {
        parent[id] = id;
    }

    public int find(int id) {
        if (parent[id] != id) {
            return find(parent[id]);
        }

        return id;
    }

    public void union(int id1, int id2) {
        int rootOfId1 = find(id1);
        int rootOfId2 = find(id2);

        // they are already in the same tree
        if (rootOfId1 == rootOfId2) {
            return;
        }

        if (rank[rootOfId1] > rank[rootOfId2]) {
            parent[rootOfId2] = rootOfId1;
        } else if (rank[rootOfId1] < rank[rootOfId2]) {
            parent[rootOfId1] = rootOfId2;
        } else {
            parent[rootOfId1] = rootOfId2;
            rank[rootOfId2]++;
        }
    }

    public int getCount() {
        Set<Integer> set = new HashSet<Integer>();

        for (int id : parent) {
            // id = 0 is default case and it shouldn't be included, and
            // if id equals parent[id], it means that id is a root
            if (id != 0 && id == parent[id]) {
                set.add(id);
            }
        }

        return set.size();
    }

}
