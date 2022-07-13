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
    public void testSimpleExpr() {
        final String sqlText = "1+2*3+1";

        CalculatorLexer lexer = new CalculatorLexer(CharStreams.fromString(sqlText));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        CalculatorParser parser = new CalculatorParser(tokenStream);

        ParseTree tree = parser.prog();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new ArithmeticEvalListener(), tree);

        String expected = "(prog (expr (expr (expr 1) + (expr (expr 2) * (expr 3))) + (expr 1)) <missing NEWLINE>)";
        Assert.assertEquals(expected, tree.toStringTree(parser));
    }

}