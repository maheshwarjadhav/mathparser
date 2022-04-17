package com.math.expr.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExpressEvalUtilTest {

    @Test
    public void testAddition() {
        String expression = "1+2";
        ExpressionEvalUtil expressionEvalUtil = new ExpressionEvalUtil(expression);
        Assertions.assertEquals(3, expressionEvalUtil.parse());
    }

    @Test
    public void testSubtraction() {
        String expression = "1-(1+2)";
        ExpressionEvalUtil expressionEvalUtil = new ExpressionEvalUtil(expression);
        Assertions.assertEquals(-2, expressionEvalUtil.parse());
    }

    @Test
    public void testMultiplication() {
        String expression = "3*(1+4)";
        ExpressionEvalUtil expressionEvalUtil = new ExpressionEvalUtil(expression);
        Assertions.assertEquals(15, expressionEvalUtil.parse());
    }

    @Test
    public void testDivision() {
        String expression = "1+1/3";
        ExpressionEvalUtil expressionEvalUtil = new ExpressionEvalUtil(expression);
        Assertions.assertEquals(1.3333333333333333, expressionEvalUtil.parse());
    }

    @Test
    public void testComplexDivision() {
        String expression = "3*2/3";
        ExpressionEvalUtil expressionEvalUtil = new ExpressionEvalUtil(expression);
        Assertions.assertEquals(2, expressionEvalUtil.parse());
    }

    @Test
    public void testComplexMultiplication() {
        String expression = "(1+1)*(3/2)";
        ExpressionEvalUtil expressionEvalUtil = new ExpressionEvalUtil(expression);
        Assertions.assertEquals(3, expressionEvalUtil.parse());
    }
}
