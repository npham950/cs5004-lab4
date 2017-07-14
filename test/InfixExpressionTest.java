import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;


/**
 * JUnit tests for InfixExpression.
 */
public class InfixExpressionTest {
    private InfixExpression ie1;
    private InfixExpression ie2;
    private InfixExpression ie3;
    private InfixExpression ie4;
    private InfixExpression ie5;
    private InfixExpression ie6;
    private InfixExpression ie7;
    private InfixExpression ie8;

    @Before
    public void setUp() throws Exception {
        this.ie1 = new InfixExpression("2 - 3");
        this.ie2 = new InfixExpression("1 + 2 + 3");
        this.ie3 = new InfixExpression("10 * 7 - 3");
        this.ie4 = new InfixExpression("4 * 5 / 5");
        this.ie5 = new InfixExpression("5 * 2 + 3 * 2 / 1");
        this.ie6 = new InfixExpression("1 + ( 2 + 3 )");
        this.ie7 = new InfixExpression("( 4 + 8 ) * 2 + 3 + ( 5 + 1 )");
        this.ie8 = new InfixExpression("( ( a + b ) * c )");
    }

    @Test
    public void toPostFix() throws Exception {
        Assert.assertEquals("2 3 -", this.ie1.toPostfix().toString());
        Assert.assertEquals("1 2 + 3 +", this.ie2.toPostfix().toString());
        Assert.assertEquals("10 7 * 3 -", this.ie3.toPostfix().toString());
        Assert.assertEquals("4 5 * 5 /", this.ie4.toPostfix().toString());
        Assert.assertEquals("5 2 * 3 2 * 1 / +", this.ie5.toPostfix().toString());
        Assert.assertEquals("1 2 3 + +", this.ie6.toPostfix().toString());
        Assert.assertEquals("4 8 + 2 * 3 + 5 1 + +", this.ie7.toPostfix().toString());
        Assert.assertEquals("a b + c *", this.ie8.toPostfix().toString());
    }

    @Test
    public void testLegalExpressions() {
        try {
            new InfixExpression("a");
            new InfixExpression("1.1 + 2.2 - 3");
            new InfixExpression("a * ( b + c ) * d");
        } catch (IllegalArgumentException e) {
            Assert.fail();
        }
    }

    @Test
    public void testIllegalExpressions() {

        try {
            new InfixExpression("");
            Assert.fail();
        } catch (IllegalArgumentException e) {
            // pass
            System.out.println(e);
        }

        try {
            new InfixExpression("a * * b");
            Assert.fail();
        } catch (IllegalArgumentException e) {
            // pass
            System.out.println(e);
        }

        try {
            new InfixExpression("( a + b ) ) ) ) * c");
            Assert.fail();
        } catch (IllegalArgumentException e) {
            // pass
            System.out.println(e);
        }

        try {
            // test empty string
            new InfixExpression("");
            Assert.fail();
        } catch (IllegalArgumentException e) {
            // pass
            System.out.println(e);
        }

        try {
            // test single operand, single operator
            new InfixExpression("ab + ");
            Assert.fail();
        } catch (IllegalArgumentException e) {
            // pass
            System.out.println(e);
        }

        try {
            // test single operator
            new InfixExpression("+");
            Assert.fail();
        } catch (IllegalArgumentException e) {
            // pass
            System.out.println(e);
        }

        try {
            // test invalid token
            new InfixExpression("$ + 2");
            Assert.fail();
        } catch (IllegalArgumentException e) {
            // pass
            System.out.println(e);
        }

        try {
            // test incorrect format
            new InfixExpression("ab + ");
            Assert.fail();
        } catch (IllegalArgumentException e) {
            // pass
            System.out.println(e);
        }

        try {
            // test incorrect format
            new InfixExpression("1.1 2.2 - 3 +");
            Assert.fail();
        } catch (IllegalArgumentException e) {
            // pass
            System.out.println(e);
        }

        try {
            // test incorrect format
            new InfixExpression("a b - c +");
            Assert.fail();
        } catch (IllegalArgumentException e) {
            // pass
            System.out.println(e);
        }

        try {
            // test incorrect format
            new InfixExpression("1 + 1 - 2 + 2 -");
            Assert.fail();
        } catch (IllegalArgumentException e) {
            // pass
            System.out.println(e);
        }
    }

    @Test
    public void testToString() {
        assertEquals("1.1 + 2.2 - 3", new InfixExpression("1.1 + 2.2 - 3").toString());
        assertEquals("a * ( b + c ) * d", new InfixExpression("a * ( b + c ) * d").toString());
    }

    @Test
    public void testToPostfix() {
        String exp;

        exp = "a * b + c * d";
        assertEquals("a b * c d * +", new InfixExpression(exp).toPostfix().toString());

        exp = "a + b + c";
        assertEquals("a b + c +", new InfixExpression(exp).toPostfix().toString());

        exp = "a * ( b + c ) * d";
        assertEquals("a b c + * d *", new InfixExpression(exp).toPostfix().toString());

        exp = "( 2 * 3 ) + ( 2 * 5 )";
        assertEquals("2 3 * 2 5 * +", new InfixExpression(exp).toPostfix().toString());

        exp = " ( ( a + b ) * c )";
        assertEquals("a b + c *", new InfixExpression(exp).toPostfix().toString());

        exp = "( a + b ) * ( ( c + d ) / e ) + f";
        assertEquals("a b + c d + e / * f +", new InfixExpression(exp).toPostfix().toString());


    }

    @Test
    public void testEvaluate() {
        String exp = "1.1 + 2.2 - 3";
        assertEquals(0.3, new InfixExpression(exp).evaluate(), 0.001);
    }
}