/**
 * Created by nypham on 6/24/17.
 */
import java.util.LinkedList;
import java.util.List;
public class Stack<T> {
    private List<T> list;
    private int size;
    public Stack() {
        this.list = new LinkedList<>();
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void push(T t) {
        this.list.add(t);
        this.size++;
    }

    public T top() {
        if (isEmpty()) {
            return null;
        }
        return list.get(this.size - 1);
    }

    public T pop() {
        T t = top();
        if (t == null) {
            throw new IllegalArgumentException("Empty Stack");
        }
        this.list.remove(this.size - 1);
        this.size--;
        return t;
    }
}

