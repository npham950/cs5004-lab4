import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

/**
 * JUnit tests for PostfixExpression.
 */
public class PostfixExpressionTest {
    private PostfixExpression pe1;
    private PostfixExpression pe2;
    private PostfixExpression pe3;
    private PostfixExpression pe4;
    private PostfixExpression pe5;
    private PostfixExpression pe6;
    private PostfixExpression pe7;
    private PostfixExpression pe8;

    /**
     * Creates PostfixExpression objects for testing.
     */
    @Before
    public void setUp() throws Exception {
        this.pe1 = new PostfixExpression("  3 2 + 10 *");
        this.pe2 = new PostfixExpression("3    2 / 10 * ");
        this.pe3 = new PostfixExpression("5 1 /  2 3 / *");
        this.pe4 = new PostfixExpression("2 0 - 30 * 10 +");
        this.pe5 = new PostfixExpression("abc xyz / 30 * 10 +");
        this.pe6 = new PostfixExpression("3 a - 20 * 15 +");
        this.pe7 = new PostfixExpression("a b * c - d +");
        this.pe8 = new PostfixExpression("1 2 + -3 -");
    }

    @Test
    public void testInitIllegalExp() throws Exception {
        try {
            new PostfixExpression("");
            Assert.fail("Exception was not thrown when exp is empty");
        } catch (IllegalArgumentException e) {
            // Pass test
        }

        try {
            new PostfixExpression("21 33 33 *");
            Assert.fail("Exception was not thrown when having more than 2 operands per 1 operator");
        } catch (IllegalArgumentException e) {
            // Pass test
        }

        try {
            new PostfixExpression("21 33 33 * + / -");
            Assert.fail("Exception was not thrown when having extra operators");
        } catch (IllegalArgumentException e) {
            // Pass test
        }

        try {
            new PostfixExpression("* 2 3 +");
            Assert.fail("Exception was not thrown when the expression starts with operator");
        } catch (IllegalArgumentException e) {
            // Pass test
        }
    }

    @Test
    public void testEvaluateArithmeticException() throws Exception {
        try {
            this.pe5.evaluate();
            Assert.fail("Exception was not thrown when evaluate() is called with" +
                    " operands being mixed of long strings and numbers");
        } catch (ArithmeticException e) {
            // Pass test
        }

        try {
            this.pe6.evaluate();
            Assert.fail("Exception was not thrown when evaluate() is called with" +
                    " operands are mixed of letters and numbers");
        } catch (ArithmeticException e) {
            // Pass test
        }

        try {
            this.pe7.evaluate();
            Assert.fail("Exception was not thrown when evaluate() is called with" +
                    " all operands are letters");
        } catch (ArithmeticException e) {
            // Pass test
        }
    }

    @Test
    public void testEvaluate() throws Exception {
        Assert.assertEquals(50, this.pe1.evaluate(), 0.1);
        Assert.assertEquals(15, this.pe2.evaluate(), 0.1);
        Assert.assertEquals(3.333, this.pe3.evaluate(), 0.1);
        Assert.assertEquals(70, this.pe4.evaluate(), 0.1);
        Assert.assertEquals(6, this.pe8.evaluate(), 0.1);
    }

    @Test
    public void testToString() throws Exception {
        Assert.assertEquals("3 2 + 10 *", this.pe1.toString());
        Assert.assertEquals("3 2 / 10 *", this.pe2.toString());
        Assert.assertEquals("5 1 / 2 3 / *", this.pe3.toString());
        Assert.assertEquals("2 0 - 30 * 10 +", this.pe4.toString());
    }

}