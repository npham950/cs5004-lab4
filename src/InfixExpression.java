/**
 * Created by nypham on 6/27/17.
 */
import javax.lang.model.type.ArrayType;
import java.util.*;

public class InfixExpression extends ExpressionCharacteristic {
    private String input;
    private String string;
    public InfixExpression(String input) {
        this.string = "";
        if (!validInput(input)) {
            throw new IllegalArgumentException();
        }
        this.input = input;
    }

    public double evaluate() {
        return toPostfix().evaluate();
    }

    public PostfixExpression toPostfix() {
        Stack<String> expression = new Stack<>();
        String result = "";
        Scanner scan = new Scanner(this.input);
        while (scan.hasNext()) {
            String token = scan.next();
            if (token.equals("(")) {
                expression.push(token);
            } else if (token.equals(")")) {
                while (!expression.top().equals("(")) {
                    result += expression.pop() + " ";
                }
                expression.pop();
            }
            else if (!isOperator(token)) {
                result += token + " ";
            }
            else {
                if (expression.isEmpty()) {
                    expression.push(token);
                }
                else {
                    if (!expression.top().equals("(") && !expression.isEmpty() && operatorPrecedence(expression.top()) >= operatorPrecedence(token)) {
                        while (!expression.isEmpty() && operatorPrecedence(expression.top()) >= operatorPrecedence(token)) {
                            result += expression.pop() + " ";
                        }
                    }
                    expression.push(token);
                }
            }
        }
        while (!expression.isEmpty()) {
            result += expression.pop() + " ";
        }
        System.out.println(result);
        return new PostfixExpression(result);
    }

    public String toString() {
        return this.string.trim();
    }

    private int operatorPrecedence(String operator) {
        Map<String,Integer> operatorMap = new HashMap<>();
        operatorMap.put("+", 1);
        operatorMap.put("-", 1);
        operatorMap.put("*", 2);
        operatorMap.put("/", 2);
        return operatorMap.get(operator);
    }

    public boolean isNumeric(String operand) {
        return operand != null && operand.matches("[-+]?\\d*\\.?\\d+") ;
    }

    protected boolean validInput(String input) {
        Stack<String> exp = new Stack<>();
        Scanner scan = new Scanner (input);
        String previous = "";
        int numofOperand = 0;
        int numofOperator = 0;
        if (input == "") {
            return false;
        }
        while (scan.hasNext()) {
            String token = scan.next();
            this.string += token + " ";
            if (isOperator(token) && previous == "") {
                return false;
            } else if (token.equals("(")) {
                exp.push(token);
            } else if (token.equals(")")) {
                try {
                    exp.pop();
                } catch (NoSuchElementException e){
                    return false;
                }
            }
            else if (previous != "" && ((isOperand(token) && isOperand(previous)) || (isOperator(previous) && isOperator(token)))) {
                return false;
            } else if (isOperand(token)) {
                numofOperand++;
            } else {
                numofOperator++;
            }
            previous = token;
        }
        return exp.isEmpty() && numofOperand == numofOperator + 1;
    }

    private boolean par (String token, String previous) {
        String[] arr = {"(", ")"};
        HashSet<String> list = new HashSet<>(Arrays.asList(arr));
        return list.contains(token) || list.contains(previous);
    }

    private boolean isOperand (String token) {
        return token.matches("^[a-zA-Z0-9.]*");
    }

    public static void main(String[] args) {
        String str = "1 + 3 + 6 * 2 / 1";
        String str1 = "1.1 + 2.2 - 3";
        InfixExpression infix = new InfixExpression(str1);
        System.out.println(infix.toPostfix().toString());
    }
}
