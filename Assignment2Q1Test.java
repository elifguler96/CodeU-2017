import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.elifguler.Assignment2Q1.printAncestors;
import static org.junit.jupiter.api.Assertions.*;

class Assignment2Q1Test {
    private BinaryTreeNode<Integer> n1, n2, n3, n4, n5, n6, n7, n8;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
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
    void printAncestorsTest() {
        // Normal case
        printAncestors(18, n8);
        assertEquals("16", outContent.toString().trim());
        outContent.reset();

        // Leaf case
        printAncestors(5, n8);
        assertEquals("3 9 16", outContent.toString().trim());
        outContent.reset();

        // Root case
        printAncestors(16, n8);
        assertEquals("", outContent.toString().trim());
        outContent.reset();

        // Not in tree case
        assertThrows(IllegalArgumentException.class, () -> { printAncestors(2555, n8); });

        // Null case
        assertThrows(IllegalArgumentException.class, () -> { printAncestors(null, n8); });
    }
}