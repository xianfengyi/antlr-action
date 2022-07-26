package com.github.pioneeryi;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Assert;
import org.junit.Test;

public class ArithmeticEvalListenerTest {

    @Test
    public void testSimpleAdd() {
        final String expr = "1+1";
        Assert.assertEquals(2, calculate(expr));
    }

    @Test
    public void testSimpleSub() {
        String expr = "6-1";
        Assert.assertEquals(5, calculate(expr));
    }

    @Test
    public void testSimpleMul(){
        String expr = "2*3";
        Assert.assertEquals(6, calculate(expr));
    }

    @Test
    public void testSimpleDiv(){
        String expr = "6/3";
        Assert.assertEquals(2, calculate(expr));
    }

    @Test
    public void testSubAdd(){
        String expr = "6-1+3";
        Assert.assertEquals(8, calculate(expr));
    }

    @Test
    public void testDivMul(){
        String expr = "6/2*3";
        Assert.assertEquals(9, calculate(expr));
    }

    @Test
    public void testComplex1(){
        String expr = "6/(1+1)*3";
        Assert.assertEquals(9, calculate(expr));
    }

    @Test
    public void testComplex2(){
        String expr = "6/2+4/2";
        Assert.assertEquals(5, calculate(expr));
    }

    private int calculate(String expr) {
        CalculatorLexer lexer = new CalculatorLexer(CharStreams.fromString(expr));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        CalculatorParser parser = new CalculatorParser(tokens);
        ParseTree tree = parser.expr();

        ParseTreeWalker walker = new ParseTreeWalker();
        ArithmeticEvalListener listener = new ArithmeticEvalListener();
        walker.walk(listener, tree);

        return listener.getResult().intValue();
    }
}