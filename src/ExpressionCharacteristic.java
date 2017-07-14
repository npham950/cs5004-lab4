import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by nypham on 7/3/17.
 */
abstract class ExpressionCharacteristic {
    protected boolean isOperator(String operator) {
        String[] operatorArr = {"+", "-", "*", "/"};
        HashSet<String> arrSet = new HashSet<>(Arrays.asList(operatorArr));
        return arrSet.contains(operator);
    }

    protected String newOperand (String operator, String str2, String str1) {
        double result;
        double operand1, operand2;
        try {
            operand1 = Double.parseDouble(str1);
            operand2 = Double.parseDouble(str2);
        } catch (NumberFormatException e) {
            throw new ArithmeticException("The operand is not numeric");
        }
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

    protected boolean isNumeric(String operand) {
        return operand != null && operand.matches("[-+]?\\d*\\.?\\d+");
    }
}
