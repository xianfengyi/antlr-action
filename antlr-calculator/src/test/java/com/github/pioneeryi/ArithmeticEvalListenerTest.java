package com.github.pioneeryi;

import com.github.pioneeryi.codegen.CalculatorLexer;
import com.github.pioneeryi.codegen.CalculatorParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Assert;
import org.junit.Test;

public class ArithmeticEvalListenerTest {

    @Test
    public void testSimpleCalculate() {
        final String expr = "1+1";
        Assert.assertEquals(2, calculate(expr));
    }

    @Test
    public void testComplexCalculate() {
        String expr = "6/(1+1)";
        Assert.assertEquals(3, calculate(expr));
    }

    private int calculate(String expr) {
        CalculatorLexer lexer = new CalculatorLexer(CharStreams.fromString(expr));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        CalculatorParser parser = new CalculatorParser(tokenStream);

        ParseTree tree = parser.prog();

        ParseTreeWalker walker = new ParseTreeWalker();
        ArithmeticEvalListener calculator = new ArithmeticEvalListener();
        walker.walk(calculator, tree);

        return calculator.getResult().intValue();
    }
}