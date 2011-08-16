package example1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;

/**
 * Unit tests for TDD (Test-drive Development) article at:
 * http://thecleancoder.blogspot.com/2010/11/craftsman-63-specifics-and-generics.html
 *
 * @author Robert C. Martin
 * @see example1.Stack in "Source Packages"
 */
public class StackTest {

    private Stack stack;

    public StackTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        stack = new Stack(2);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of size method, of class Stack.
     */
    @Test
    public void newStackShouldBeEmpty() {
        assertThat(stack.size(), equalTo(0));
    }

    @Test
    public void sizeAfterPushShouldBeOne() {
        stack.push(0);
        assertThat(stack.size(), equalTo(1));

    }

    @Test
    public void sizeAfterPushAndPopShouldBeZero() {
        stack.push(0);
        stack.pop();
        assertThat(stack.size(), equalTo(0));
    }

    @Test(expected = Stack.Underflow.class)
    public void shouldThrowUnderflowWhenEmptyStackIsPopped() {
        stack.pop();
    }

    @Test(expected = Stack.Overflow.class)
    public void shouldThrowOverflowWhenFullStackIsPushed() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
    }

    @Test
    public void shouldPopZeroWhenZeroIsPushed() {
        stack.push(0);
        assertThat(stack.pop(), equalTo(0));
    }

    @Test
    public void shouldPopOneWhenOneIsPushed() {
        stack.push(1);
        assertThat(stack.pop(), equalTo(1));
    }

    @Test
    public void PushedElementsArePoppedInReverseOrder() {
        stack.push(1);
        stack.push(2);
        assertThat(stack.pop(), equalTo(2));
        assertThat(stack.pop(), equalTo(1));
    }
}
