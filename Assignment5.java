import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Queue;
import java.util.LinkedList;
import java.util.LinkedHashSet;
import java.util.Collections;

public class Assignment5 {
    /**
     * Infers the alphabet of an unknown language from list of words of that language.
     *
     * @param words list of words in lexicographic order.
     * @return characters in lexicographic order.
     */
    public static List<Character> inferAlphabet(List<String> words) {
        if (words.isEmpty()) {
            return Collections.emptyList();
        }

        Map<Character, Vertex> graph = new HashMap<>();

        ListIterator<String> iterator = words.listIterator();

        String s1 = iterator.next();
        addAllCharacters(s1, graph);

        while (iterator.nextIndex() < words.size()) {
            String s2 = s1;
            s1 = iterator.next();

            addAllCharacters(s1, graph);

            int index = findFirstDifferingIndex(s1, s2);

            if (index != -1) {
                // s2.charAt(index) comes before s1.charAt(index).
                createEdgeBetweenVertices(s2.charAt(index), s1.charAt(index), graph);
            }
        }

        return new ArrayList<>(topologicalSort(graph));
    }

    /**
     * Adds all characters of s to set.
     *
     * @param s     word whose characters are going to be added to set.
     * @param graph graph containing the vertices.
     */
    private static void addAllCharacters(String s, Map<Character, Vertex> graph) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (!graph.containsKey(c)) {
                graph.put(c, new Vertex(c));
            }
        }
    }

    /**
     * Finds the index at which s1 and s2 start to differ.
     *
     * @param s1 string number one.
     * @param s2 string number two.
     * @return index s1 and s2 start to differ. -1 if no difference.
     */
    private static int findFirstDifferingIndex(String s1, String s2) {
        int bound = Math.min(s1.length(), s2.length());
        int index = 0;

        while (index < bound && s1.charAt(index) == s2.charAt(index)) {
            index++;
        }

        if (index == bound) {
            return -1;
        }

        return index;
    }

    /**
     * Creates an edge between vertices with values from and to in graph.
     *
     * @param from  value of the vertex the edge will be coming from.
     * @param to    value of the vertex the edge will be coming to.
     * @param graph graph containing the vertices.
     */
    private static void createEdgeBetweenVertices(char from, char to, Map<Character, Vertex> graph) {
        graph.get(from).addEdge(graph.get(to));
        graph.get(to).incrementindegree();
    }

    /**
     * Performs topological sort on graph.
     *
     * @param graph graph containing the vertices.
     * @return topologically sorted set of characters.
     */
    private static Set<Character> topologicalSort(Map<Character, Vertex> graph) {
        Set<Character> set = new LinkedHashSet<>();

        if (graph.isEmpty()) {
            return set;
        }

        Queue<Vertex> queue = new LinkedList<>();

        while (true) {
            for (Map.Entry<Character, Vertex> entry : graph.entrySet()) {
                Vertex v = entry.getValue();

                if (v.getindegree() == 0) {
                    queue.add(v);
                    v.decrementindegree();
                }
            }

            if (queue.isEmpty()) {
                break;
            }

            while (!queue.isEmpty()) {
                Vertex v = queue.poll();
                set.add(v.getValue());

                for (Vertex el : v.getEdges()) {
                    el.decrementindegree();
                }
            }
        }

        return set;
    }

    static class Vertex {
        private final char value;
        private List<Vertex> edges;
        private int indegree;

        public Vertex(char value) {
            this.value = value;
            edges = new LinkedList<>();
            indegree = 0;
        }

        public char getValue() {
            return value;
        }

        public List<Vertex> getEdges() {
            return Collections.unmodifiableList(edges);
        }

        public int getindegree() {
            return indegree;
        }

        public void addEdge(Vertex v) {
            edges.add(v);
        }

        public void incrementindegree() {
            indegree++;
        }

        public void decrementindegree() {
            indegree--;
        }
    }
}
