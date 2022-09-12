package com.github.pioneeryi;

import com.github.pioneeryi.codegen.SqlBaseLexer;
import com.github.pioneeryi.codegen.SqlBaseParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.List;

public class SQLUtil {

    public static List<String> getColumns(String sql) {
        TableAndColumnExtractor extractor = getTableAndColumnExtractor(sql);
        return extractor.getColumnNames();
    }

    public static List<String> getTables(String sql) {
        TableAndColumnExtractor extractor = getTableAndColumnExtractor(sql);
        return extractor.getTableNames();
    }

    public static String appendTablePrefixToColumn(String sql) {
        ColumnAddTablePrefix columnAddTablePrefix = getColumnAddTablePrefix(sql);
        return columnAddTablePrefix.getRewriter().getText();
    }

    private static TableAndColumnExtractor getTableAndColumnExtractor(String sql) {
        TableAndColumnExtractor extractor = new TableAndColumnExtractor();
        SqlBaseLexer lexer = new SqlBaseLexer(CharStreams.fromString(sql));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        SqlBaseParser parser = new SqlBaseParser(tokenStream);

        parser.singleStatement().accept(extractor);
        return extractor;
    }

    private static ColumnAddTablePrefix getColumnAddTablePrefix(String sql) {
        SqlBaseLexer lexer = new SqlBaseLexer(CharStreams.fromString(sql));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        SqlBaseParser parser = new SqlBaseParser(tokenStream);

        List<String> tableNames = getTables(sql);
        if (tableNames.size() > 1) {
            throw new RuntimeException("not support more than one table");
        }
        String tableName = tableNames.get(0);
        ColumnAddTablePrefix visitor = new ColumnAddTablePrefix(tableName, tokenStream);

        parser.singleStatement().accept(visitor);
        return visitor;
    }
}
