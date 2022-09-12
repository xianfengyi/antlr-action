package com.github.pioneeryi;

import com.github.pioneeryi.codegen.SqlBaseLexer;
import com.github.pioneeryi.codegen.SqlBaseParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Assert;
import org.junit.Test;

public class ColumnAddTablePrefixTest {

    @Test
    public void testModifySql() {

        String sqlText = "SELECT A,B FROM T1 WHERE C='pioneeryi'";

        SqlBaseLexer lexer = new SqlBaseLexer(CharStreams.fromString(sqlText));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        SqlBaseParser parser = new SqlBaseParser(tokenStream);

        ColumnAddTablePrefix visitor = new ColumnAddTablePrefix("T1", tokenStream);

        parser.singleStatement().accept(visitor);

        String actualSQl = visitor.getRewriter().getText();
        Assert.assertEquals("SELECT T1.A,T1.B FROM T1 WHERE T1.C='pioneeryi'", actualSQl);
    }
}