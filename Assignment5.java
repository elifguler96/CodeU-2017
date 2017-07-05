import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
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

        Set<Character> set = new HashSet<>();

        String s1 = words.get(0);
        // Performed so that all characters will be in the returned list even if topologicalSort doesn't contain some.
        addAllCharacters(s1, set);

        Map<Character, Vertex> graph = new HashMap<>();

        for (int i = 0; i < words.size() - 1; i++) {
            s1 = words.get(i);
            String s2 = words.get(i + 1);

            addAllCharacters(s2, set);

            try {
                int index = findFirstDifferingIndex(s1, s2);
                // s1.charAt(index) comes before s2.charAt(index).
                createEdgeBetweenVertices(s1.charAt(index), s2.charAt(index), graph);
            } catch (NoDifferenceException ignored) {
                // Just continue if two strings are identical.
            }
        }

        Set<Character> alphabet = topologicalSort(graph);

        alphabet.addAll(set);

        return new ArrayList<>(alphabet);
    }

    /**
     * Adds all characters of s to set
     *
     * @param s   word whose characters are going to be added to set
     * @param set holds the characters of s
     */
    private static void addAllCharacters(String s, Set<Character> set) {
        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
        }
    }

    private static class NoDifferenceException extends Exception {

    }

    /**
     * Finds the index at which s1 and s2 start to differ.
     *
     * @param s1 string number one.
     * @param s2 string number two.
     * @return index s1 and s2 start to differ.
     * @throws NoDifferenceException if s1 and s2 are not different in an index of the shorter one.
     */
    private static int findFirstDifferingIndex(String s1, String s2) throws NoDifferenceException {
        int bound = Math.min(s1.length(), s2.length());
        int index = 0;

        while (index < bound && s1.charAt(index) == s2.charAt(index)) {
            index++;
        }

        if (index == bound) {
            throw new NoDifferenceException();
        }

        return index;
    }

    /**
     * Creates vertices with values from and to, and an edge between those in graph.
     *
     * @param from  value of the vertex the edge will be coming from.
     * @param to    value of the vertex the edge will be coming to.
     * @param graph graph containing the vertices.
     */
    private static void createEdgeBetweenVertices(char from, char to, Map<Character, Vertex> graph) {
        if (!graph.containsKey(from)) {
            graph.put(from, new Vertex(from));
        }

        if (!graph.containsKey(to)) {
            graph.put(to, new Vertex(to));
        }

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

        Vertex[] vertices = new Vertex[graph.size()];

        int index = 0;

        for (Map.Entry<Character, Vertex> entry : graph.entrySet()) {
            vertices[index] = entry.getValue();
            index++;
        }

        Queue<Vertex> queue = new LinkedList<>();

        while (true) {
            for (Vertex v : vertices) {
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
}

class Vertex {
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
