import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by nypham on 6/24/17.
 */
public class PostfixExpression extends ExpressionCharacteristic {
    private String input;
    private String string;
    public PostfixExpression(String input) {
        this.string = "";
        if (!validInput(input)) {
            throw new IllegalArgumentException("Invalid input");
        }
        this.input = input;
    }

    public double evaluate() {
        Stack<String> expression = new Stack<>();
        Scanner scan = new Scanner(this.input);
        while (scan.hasNext()) {
            String token = scan.next();
            if (isOperator(token)) {
                expression.push(newOperand(token, expression.pop(), expression.pop()));
            } else {
                expression.push(token);
            }
        }
        return Double.parseDouble(expression.top());
    }

    public String toString() {
        return this.string.trim();
    }

    private boolean validInput(String input) {
        Scanner scan = new Scanner(input);
        int tracker = 0;
        while (scan.hasNext()) {
            String token = scan.next();
            this.string += token + " ";
            if (isOperator(token)) {
                tracker--;
            } else {
                tracker++;
            }
            if (tracker == 0) {
                return false;
            }
        }
        return tracker == 1;
    }

    public static void main(String[] args) {
        PostfixExpression postix = new PostfixExpression("3    2 / 10 * ");
        System.out.println(postix.evaluate());
        System.out.println(postix.toString());
    }

    public boolean isNumeric(String operand) {
        return operand != null && operand.matches("[-+]?\\d*\\.?\\d+") ;
    }

}
