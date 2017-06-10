import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeNodeTest {
    private BinaryTreeNode<Integer> n1, n2, n3, n4, n5, n6, n7, n8;

    @BeforeEach
    void setUp() {
        // Set up the tree
        n1 = new BinaryTreeNode<>(1, null, null);
        n2 = new BinaryTreeNode<>(5, null, null);
        n3 = new BinaryTreeNode<>(3, n1, n2);
        n4 = new BinaryTreeNode<>(14, null, null);
        n5 = new BinaryTreeNode<>(9, n3, n4);
        n6 = new BinaryTreeNode<>(19, null, null);
        n7 = new BinaryTreeNode<>(18, null, n6);
        n8 = new BinaryTreeNode<>(16, n5, n7);
    }

    @Test
    void findAncestorsTest() {
        // Normal case
        assertEquals(n8.findAncestors(18), Collections.singletonList(16));

        // Leaf case
        assertEquals(n8.findAncestors(5), Arrays.asList(3, 9, 16));

        // Root case
        assertEquals(n8.findAncestors(16), Collections.emptyList());

        // Not in tree case
        assertEquals(n8.findAncestors(2555), null);

        // Null case
        assertEquals(n8.findAncestors(null), null);
    }

    @Test
    void findCommonAncestorTest() {
        // One on left one on right case
        assertEquals(n8.findCommonAncestor(n1, n2), n3);

        //One parent one left child case
        assertEquals(n8.findCommonAncestor(n5, n2), n5);

        //One parent one right child case
        assertEquals(n8.findCommonAncestor(n7, n6), n7);

        // Node1 is not in tree case
        assertThrows(IllegalArgumentException.class, () -> {
            n8.findCommonAncestor(new BinaryTreeNode<Integer>(6), n2);
        });

        // Node2 is not in tree case
        assertThrows(IllegalArgumentException.class, () -> {
            n8.findCommonAncestor(n5, new BinaryTreeNode<Integer>(7));
        });

        // Both nodes are not in tree case
        assertThrows(IllegalArgumentException.class, () -> {
            n8.findCommonAncestor(new BinaryTreeNode<Integer>(2), new BinaryTreeNode<Integer>(7));
        });

        //Null node case
        assertThrows(IllegalArgumentException.class, () -> {
            n8.findCommonAncestor(null, null);
        });
    }

}