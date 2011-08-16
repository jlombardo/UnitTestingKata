package example1;

/**
 * A demonstration of a classic LIFO stack data structure used to
 * demonstrate TDD (Test-drive Development) article at:
 * http://thecleancoder.blogspot.com/2010/11/craftsman-63-specifics-and-generics.html
 *
 * @author Robert C. Martin
 * @see example1.StackTestJava in "Test Packages"
 */
public class Stack {

    private int size;
    private int capacity;
    private int elements[];

    public Stack(int capacity) {
        this.capacity = capacity;
        elements = new int[capacity];
    }

    public int size() {
        return size;
    }

    public void push(int element) {
        if (size == capacity) {
            throw new Overflow();
        }
        this.elements[size++] = element;
    }

    public int pop() {
        if (size == 0) {
            throw new Underflow();
        }
        return elements[--size];
    }

    // For simplicity we'll declare these here, but in production these
    // should be separate class files...

    public class Underflow extends RuntimeException {
    }

    public class Overflow extends RuntimeException {
    }
}
