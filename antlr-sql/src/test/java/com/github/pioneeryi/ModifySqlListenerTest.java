package com.github.pioneeryi;

import com.github.pioneeryi.codegen.SqlBaseLexer;
import com.github.pioneeryi.codegen.SqlBaseParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

public class ModifySqlListenerTest {

    @Test
    public void testModifySql() {

        final String sqlText = "select c1,c2 from T";

        SqlBaseLexer lexer = new SqlBaseLexer(CharStreams.fromString(sqlText));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        SqlBaseParser parser = new SqlBaseParser(tokenStream);

        ParseTree tree = parser.fileFormat();

        ModifySqlListener listener = new ModifySqlListener(parser.getTokenStream());

        System.out.println("Before Rewriting");
        System.out.println(listener.rewriter.getText());

        listener.enterQuery(parser.query());
        listener.enterQuery(parser.query());

        parser.addParseListener(listener);

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);

        System.out.println("After Rewriting");
        System.out.println(listener.rewriter.getText());
    }

}