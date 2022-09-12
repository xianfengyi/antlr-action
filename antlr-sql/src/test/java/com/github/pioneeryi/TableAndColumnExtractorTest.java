package com.github.pioneeryi;

import com.github.pioneeryi.codegen.SqlBaseLexer;
import com.github.pioneeryi.codegen.SqlBaseParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TableAndColumnExtractorTest {

    @Test
    public void testExtractTableName() {
        String sqlText = "SELECT A,B FROM T1 WHERE C='pioneeryi'";

        TableAndColumnExtractor extractor = new TableAndColumnExtractor();
        SqlBaseLexer lexer = new SqlBaseLexer(CharStreams.fromString(sqlText));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        SqlBaseParser parser = new SqlBaseParser(tokenStream);

        parser.singleStatement().accept(extractor);

        List<String> tableNames = extractor.getTableNames();
        Assert.assertEquals(1, tableNames.size());
        Assert.assertEquals("T1", tableNames.get(0));

        List<String> columnNames = extractor.getColumnNames();
        Assert.assertEquals(3, columnNames.size());
    }
}
