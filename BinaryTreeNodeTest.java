import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

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
    void findAncestorsTestNormal() {
        assertEquals(n8.findAncestors(18), Arrays.asList(16));
    }

    @Test
    void findAncestorsTestLeaf() {
        assertEquals(n8.findAncestors(5), Arrays.asList(3, 9, 16));
    }

    @Test
    void findAncestorsTestRoot() {
        assertEquals(n8.findAncestors(16), Collections.emptyList());
    }

    @Test
    void findAncestorsTestNotInTree() {
        assertEquals(n8.findAncestors(2555), null);
    }

    @Test
    void findAncestorsTestNull() {
        assertEquals(n8.findAncestors(null), null);
    }

    @Test
    void findCommonAncestorTestOneOnLeftOneOnRight() {
        assertEquals(n8.findCommonAncestor(n1, n2), n3);
    }

    @Test
    void findCommonAncestorTestOneParentOneLeftChild() {
        assertEquals(n8.findCommonAncestor(n5, n2), n5);
    }

    @Test
    void findCommonAncestorTestOneParentOneRightChild() {
        assertEquals(n8.findCommonAncestor(n7, n6), n7);
    }

    @Test
    void findCommonAncestorTestNode1NotInTree() {
        assertThrows(IllegalArgumentException.class, () -> {
            n8.findCommonAncestor(new BinaryTreeNode<Integer>(6), n2);
        });
    }

    @Test
    void findCommonAncestorTestNode2NotInTree() {
        assertThrows(IllegalArgumentException.class, () -> {
            n8.findCommonAncestor(n5, new BinaryTreeNode<Integer>(7));
        });
    }

    @Test
    void findCommonAncestorTestBothNodesNotInTree() {
        assertThrows(IllegalArgumentException.class, () -> {
            n8.findCommonAncestor(new BinaryTreeNode<Integer>(2), new BinaryTreeNode<Integer>(7));
        });
    }

    @Test
    void findCommonAncestorTestNullNode() {
        assertThrows(IllegalArgumentException.class, () -> {
            n8.findCommonAncestor(null, null);
        });
    }

}