/**
 * Created by nypham on 6/24/17.
 */
import java.util.LinkedList;
import java.util.List;
public class Stack<T> {
    private LinkedList<T> list;
    public Stack() {
        this.list = new LinkedList<>();
    }

    public int size() {
        return this.list.size();
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public void push(T t) {
        this.list.add(t);
    }

    public T top() {
        return this.list.peekLast();
    }

    public T pop() {
        return this.list.removeLast();
    }
}

