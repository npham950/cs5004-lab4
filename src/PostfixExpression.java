import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.List;
/**
 * Created by nypham on 6/24/17.
 */
public class PostfixExpression {
    private String input;
    private String string;
    public PostfixExpression(String input) {
        this.string = "";
        Scanner scan = new Scanner(input);
        while (scan.hasNext()) {
            String next = scan.next();
            this.string += next + " ";
            if(!isNumeric(next) && !isOperator(next)){
                throw new IllegalArgumentException("The expression is not numeric");
            }

        }
        this.input = input;
    }

    public double evaluate() {
        Stack<String> expression = new Stack<>();
        Scanner scan = new Scanner(this.input);
        while (scan.hasNext()) {
            String token = scan.next();
            if (!isOperator(token)) {
                expression.push(token);
            }else {
                if (expression.size() >= 2) {
                    String operand2 = expression.pop();
                    String operand1 = expression.pop();
                    if (!isOperator(operand1) && !isOperator(operand2)) {
                        expression.push(newOperand(token, Integer.parseInt(operand1), Integer.parseInt(operand2)));
                    } else {
                        throw new ArithmeticException("The expression cannot be evaluated");
                    }
                } else {
                    throw new NoSuchElementException("The expression cannot be evaluated");
                }
            }
        }
        return Double.parseDouble(expression.top());
    }

    public String toString() {
        return this.string.trim();
    }

    private boolean isOperator(String operator) {
        String[] operatorArr = {"+", "-", "*", "/"};
        List<String> operatorList = Arrays.asList(operatorArr);
        return operatorList.contains(operator);
    }

    private String newOperand (String operator, int operand1, int operand2) {
        int result;
        if (operator.equals("+")){
            result = operand1 + operand2;
        }else if (operator.equals("-")) {
            result = operand1 - operand2;
        }else if (operator.equals("*")) {
            result = operand1 * operand2;
        }else {
            result = operand1 / operand2;
        }
        return String.valueOf(result);
    }

    public static void main(String[] args) {
        PostfixExpression postix = new PostfixExpression(" 99 9   + 6 *    ");
        //System.out.println(postix.evaluate());
        System.out.println(postix.toString());
    }

    public boolean isNumeric(String operand) {
        return operand != null && operand.matches("[-+]?\\d*\\.?\\d+") ;
    }
}
