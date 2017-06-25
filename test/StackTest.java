import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

/**
 * JUnit tests for MyStack.
 */
public class StackTest {
    private Stack<String> stringStack;
    private Stack<Integer> intStack;

    /**
     * Creates new instances of MyStack for testing.
     * Also serves as a test for the constructor.
     */
    @Before
    public void setUp() {
        this.stringStack = new Stack<>();
        this.intStack = new Stack<>();
    }

    /**
     * Tests correctness of size() method.
     */
    @Test
    public void testSize() {
        // Empty stack
        Assert.assertEquals(0, this.stringStack.size());
        Assert.assertEquals(0, this.intStack.size());

        // Stack of size one
        this.stringStack.push("1st");
        Assert.assertEquals(1, this.stringStack.size());

        // Stack of size two
        this.stringStack.push("2nd");
        Assert.assertEquals(2, this.stringStack.size());

        // Stack of size three
        this.stringStack.push("3nd");
        Assert.assertEquals(3, this.stringStack.size());
    }

    /**
     * Tests correctness of isEmpty() method.
     */
    @Test
    public void testIsEmpty() {
        // Empty stacks
        Assert.assertTrue(this.stringStack.isEmpty());
        Assert.assertTrue(this.intStack.isEmpty());

        // Non-empty stacks
        this.stringStack.push("string");
        this.intStack.push(3);
        Assert.assertFalse(this.stringStack.isEmpty());
        Assert.assertFalse(this.intStack.isEmpty());
    }

    /**
     * Tests correctness of push() method.
     * Tests correctness of push() method by asserting that an empty
     * stack is no longer empty after pushing an object to it. top()
     * checks that the correct object was added at the correct position.
     * Another object is added, then top checks that it was also added at
     * the correct position. pop() is then performed on the list and the
     * test asserts that the list is not empty. pop() is performed on the
     * list again and the test asserts that the list is empty.
     */
    @Test
    public void testPush() {

        Assert.assertTrue(this.stringStack.isEmpty());
        Assert.assertTrue(this.intStack.isEmpty());

        this.intStack.push(3);
        this.stringStack.push("string");

        Assert.assertFalse(this.stringStack.isEmpty());
        Assert.assertFalse(this.intStack.isEmpty());

        Assert.assertEquals("string", this.stringStack.top());
        Assert.assertEquals(3, (int) this.intStack.top());

        this.intStack.push(4);
        this.stringStack.push("thing");

        Assert.assertEquals("thing", this.stringStack.top());
        Assert.assertEquals(4, (int) this.intStack.top());

        this.intStack.pop();
        this.stringStack.pop();
        Assert.assertFalse(this.stringStack.isEmpty());
        Assert.assertFalse(this.intStack.isEmpty());

        this.intStack.pop();
        this.stringStack.pop();
        Assert.assertTrue(this.stringStack.isEmpty());
        Assert.assertTrue(this.intStack.isEmpty());
    }

    /**
     * Tests correctness of top() method.
     */
    @Test
    public void testTop() {
        Assert.assertEquals(null , this.stringStack.top());
        Assert.assertEquals(null, this.intStack.top());
        this.intStack.push(3);
        this.stringStack.push("string");
        Assert.assertEquals("string", this.stringStack.top());
        Assert.assertEquals(3, (int) this.intStack.top());
        Assert.assertFalse(this.intStack.isEmpty());
        Assert.assertFalse(this.stringStack.isEmpty());
    }

    /**
     * Tests correctness of pop() method.
     * Tests correctness of pop() method by pushing several objects to a
     * stack and asserting that the stack is only empty after all objects
     * are removed.
     */
    @Test
    public void testPop() {
        this.intStack.push(3);
        this.intStack.push(2);
        this.intStack.push(1);
        this.stringStack.push("string");
        this.stringStack.push("thing");
        this.stringStack.push("dingaling");

        Assert.assertEquals("dingaling", this.stringStack.pop());
        Assert.assertFalse(this.stringStack.isEmpty());
        Assert.assertEquals("thing", this.stringStack.pop());
        Assert.assertFalse(this.stringStack.isEmpty());
        Assert.assertEquals("string", this.stringStack.pop());
        Assert.assertTrue(this.stringStack.isEmpty());

        Assert.assertEquals(1, (int) this.intStack.pop());
        Assert.assertFalse(this.intStack.isEmpty());
        Assert.assertEquals(2, (int) this.intStack.pop());
        Assert.assertFalse(this.intStack.isEmpty());
        Assert.assertEquals(3, (int) this.intStack.pop());
        Assert.assertTrue(this.intStack.isEmpty());
    }
}