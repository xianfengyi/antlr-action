package com.github.pioneeryi.parser;

import com.github.pioneeryi.codegen.SqlBaseLexer;
import com.github.pioneeryi.codegen.SqlBaseParser;
import com.github.pioneeryi.tree.Node;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.sql.Statement;
import java.util.Arrays;
import java.util.function.Function;

/**
 * SqlParser.
 *
 * @Author yixianfeng
 * @Date 2022/9/13 11:06 PM
 */
public class SqlParser {

    public Statement createStatement(String sql) {
        return (Statement) invokeParser("statement", sql, SqlBaseParser::singleStatement);
    }

    private Node invokeParser(String name, String sql, Function<SqlBaseParser, ParserRuleContext> parseFunction) {
        try {
            SqlBaseLexer lexer = new SqlBaseLexer(CharStreams.fromString(sql));
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            SqlBaseParser parser = new SqlBaseParser(tokenStream);

            ParserRuleContext tree;
            try {
                // first, try parsing with potentially faster SLL mode
                parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
                tree = parseFunction.apply(parser);
            } catch (ParseCancellationException ex) {
                // if we fail, parse with LL mode
                tokenStream.reset(); // rewind input stream
                parser.reset();

                parser.getInterpreter().setPredictionMode(PredictionMode.LL);
                tree = parseFunction.apply(parser);
            }

            return new AstBuilder().visit(tree);
        } catch (StackOverflowError e) {
            throw new RuntimeException(name + " is too large (stack overflow while parsing)");
        }
    }
}
