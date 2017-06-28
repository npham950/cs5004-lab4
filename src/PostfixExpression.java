import java.lang.reflect.Array;
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
        if (!invalidInput(input)) {
            throw new IllegalArgumentException("Invalid input");
        }
        this.input = input;
    }

    public double evaluate() {
        Stack<String> expression = new Stack<>();
        Scanner scan = new Scanner(this.input);
        while (scan.hasNext()) {
            String token = scan.next();
            if (!isNumeric(token) && !isOperator(token)) {
                throw new ArithmeticException();
            }
            if (isNumeric(token)) {
                expression.push(token);
            }else {
                if(expression.size() < 2) {
                    throw new ArithmeticException();
                }else{
                    double operand2 = Double.parseDouble(expression.pop());
                    double operand1 = Double.parseDouble(expression.pop());
                    expression.push(newOperand(token, operand1, operand2));
                }
            }
        }
        return Double.parseDouble(expression.top());
    }

    public String toString() {
        return this.string;
    }

    private boolean isOperator(String operator) {
        String[] operatorArr = {"+", "-", "*", "/"};
        List<String> operatorList = Arrays.asList(operatorArr);
        return operatorList.contains(operator);
    }

    private String newOperand (String operator, double operand1, double operand2) {
        double result;
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

    private boolean invalidInput(String input) {
        Scanner scan = new Scanner(input);
        int numofOperators = 0;
        int numofOperands = 0;
        String firstWord = scan.next();
        if (input == "" || isOperator(firstWord)) {
            return false;
        }
        this.string += firstWord + " ";
        numofOperands++;
        while (scan.hasNext()) {
            String next = scan.next();
            this.string += next + " ";
            if(isOperator(next)){
                numofOperators++;
            }else {
                numofOperands++;
            }
        }
        this.string = this.string.substring(0, this.string.length() - 1);
        return numofOperands - numofOperators == 1;
    }

    public static void main(String[] args) {
        PostfixExpression postix = new PostfixExpression(" 99 9   + 6 *    ");
        System.out.println(postix.evaluate());
        System.out.println(postix.toString());
    }

    public boolean isNumeric(String operand) {
        return operand != null && operand.matches("[-+]?\\d*\\.?\\d+") ;
    }

}
