package com.github.pioneeryi;

import com.github.pioneeryi.codegen.SqlBaseBaseVisitor;
import com.github.pioneeryi.codegen.SqlBaseParser;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

/**
 * SQL 中的列加上表名前缀.
 *
 * @Author yixianfeng
 * @Date 2022/9/12 9:17 PM
 */
public class ColumnAddTablePrefix extends SqlBaseBaseVisitor {

    private String tableName;

    private TokenStreamRewriter rewriter;

    public ColumnAddTablePrefix(String tableName, TokenStream tokens) {
        this.tableName = tableName;
        rewriter = new TokenStreamRewriter(tokens);
    }

    @Override
    public String visitColumnReference(SqlBaseParser.ColumnReferenceContext ctx) {
        String columnName = ctx.getChild(0).getText();
        rewriter.insertBefore(ctx.start, tableName + ".");
        return columnName;
    }

    public TokenStreamRewriter getRewriter() {
        return rewriter;
    }
}
