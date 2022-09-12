package com.github.pioneeryi;

import com.github.pioneeryi.codegen.SqlBaseBaseVisitor;
import com.github.pioneeryi.codegen.SqlBaseParser;

import java.util.ArrayList;
import java.util.List;

/**
 * 从 SQL 中提取表名和列名.
 *
 * @Author yixianfeng
 * @Date 2022/9/12 8:48 PM
 */
public class TableAndColumnExtractor extends SqlBaseBaseVisitor {

    private List<String> tableNames;

    private List<String> columnNames;

    public TableAndColumnExtractor() {
        tableNames = new ArrayList<>();
        columnNames = new ArrayList<>();
    }

    public List<String> getTableNames() {
        return tableNames;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    @Override
    public String visitTableIdentifier(SqlBaseParser.TableIdentifierContext ctx) {
        String tableName = ctx.getChild(0).getText();
        tableNames.add(tableName);
        return tableName;
    }

    @Override
    public String visitColumnReference(SqlBaseParser.ColumnReferenceContext ctx) {
        String columnName = ctx.getChild(0).getText();
        columnNames.add(columnName);
        return columnName;
    }
}
