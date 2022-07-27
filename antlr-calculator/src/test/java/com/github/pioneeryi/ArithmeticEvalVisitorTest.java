package com.github.pioneeryi;

import com.github.pioneeryi.codegen.CalculatorLexer;
import com.github.pioneeryi.codegen.CalculatorParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Assert;
import org.junit.Test;

public class ArithmeticEvalVisitorTest {

    @Test
    public void testSimpleCalculate() {
        final String expr = "1+2*3+1";
        Number result = calculate(expr);
        Assert.assertEquals(8, result);
    }

    @Test
    public void testComplexCalculate() {
        final String expr = "6/(1+1)";
        Number result = calculate(expr);
        Assert.assertEquals(3, result);
    }

    @Test
    public void testFLoadCalculate(){
        final String expr = "1.2+2.1";
        Number result = calculate(expr);
        Assert.assertEquals(3.3, result.floatValue(),0.001);
    }

    private Number calculate(String expr) {
        CalculatorLexer lexer = new CalculatorLexer(CharStreams.fromString(expr));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        CalculatorParser parser = new CalculatorParser(tokenStream);

        CalculatorParser.ExprContext tree = parser.expr();
        ArithmeticEvalVisitor eval = new ArithmeticEvalVisitor();
        return eval.visit(tree);
    }
}