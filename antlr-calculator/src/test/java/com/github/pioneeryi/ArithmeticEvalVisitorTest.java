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
        final String sqlText = "1+2*3+1";

        CalculatorLexer lexer = new CalculatorLexer(CharStreams.fromString(sqlText));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        CalculatorParser parser = new CalculatorParser(tokenStream);

        CalculatorParser.ProgContext tree = parser.prog();
        ArithmeticEvalVisitor eval = new ArithmeticEvalVisitor();
        int result = eval.visit(tree);
        Assert.assertEquals(8, result);
    }

    @Test
    public void testComplexCalculate() {
        final String sqlText = "6/(1+1)";

        CalculatorLexer lexer = new CalculatorLexer(CharStreams.fromString(sqlText));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        CalculatorParser parser = new CalculatorParser(tokenStream);

        CalculatorParser.ProgContext tree = parser.prog();
        ArithmeticEvalVisitor eval = new ArithmeticEvalVisitor();
        int result = eval.visit(tree);
        Assert.assertEquals(3, result);
    }
}