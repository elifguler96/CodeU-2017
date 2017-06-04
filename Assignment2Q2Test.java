import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.elifguler.Assignment2Q2.showCommonAncestor;
import static org.junit.jupiter.api.Assertions.*;

class Assignment2Q2Test {
    private BinaryTreeNode<Integer> n1, n2, n3, n4, n5, n6, n7, n8;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

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

        // Set up streams
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void tearDown() {
        // Clear the streams
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    void showCommonAncestorTest() {
        // One on left one on right case
        showCommonAncestor(n1, n2, n8);
        assertEquals("BinaryTreeNode{" +
                "data=" + 3 +
                '}', outContent.toString().trim());
        outContent.reset();

        //One parent one left child case
        showCommonAncestor(n5, n2, n8);
        assertEquals("BinaryTreeNode{" +
                "data=" + 9 +
                '}', outContent.toString().trim());
        outContent.reset();

        //One parent one right child case
        showCommonAncestor(n7, n6, n8);
        assertEquals("BinaryTreeNode{" +
                "data=" + 18 +
                '}', outContent.toString().trim());
        outContent.reset();

        // Node1 is not in tree case
        assertThrows(IllegalArgumentException.class, () -> {
            showCommonAncestor(new BinaryTreeNode<Integer>(6), n2, n8);
        });

        // Node2 is not in tree case
        assertThrows(IllegalArgumentException.class, () -> {
            showCommonAncestor(n5, new BinaryTreeNode<Integer>(7), n8);
        });

        // Both nodes are not in tree case
        assertThrows(IllegalArgumentException.class, () -> {
            showCommonAncestor(new BinaryTreeNode<Integer>(2), new BinaryTreeNode<Integer>(7), n8);
        });

        // Null root case
        assertThrows(IllegalArgumentException.class, () -> {
            showCommonAncestor(n1, n2, null);
        });

        //Null node case
        assertThrows(IllegalArgumentException.class, () -> {
            showCommonAncestor(null, null, n8);
        });
    }

}