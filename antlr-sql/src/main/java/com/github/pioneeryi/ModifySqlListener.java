package com.github.pioneeryi;

import com.github.pioneeryi.codegen.SqlBaseBaseListener;
import com.github.pioneeryi.codegen.SqlBaseParser;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

/**
 * 通过Listener 修改 SQL.
 *
 * @author pioneeryi
 * @since 2021/7/15 9:22 上午
 */
public class ModifySqlListener extends SqlBaseBaseListener {

    public TokenStreamRewriter rewriter;

    public ModifySqlListener(TokenStream tokens) {
        rewriter = new TokenStreamRewriter(tokens);
    }

    @Override
    public void enterSingleExpression(SqlBaseParser.SingleExpressionContext ctx) {
        String field = "pioneeryi";
        rewriter.insertAfter(ctx.stop, field);
    }

    public void exitSingleExpression(SqlBaseParser.SingleExpressionContext ctx) {
        String field = "pioneeryi";
        rewriter.insertAfter(ctx.stop, field);
    }

    public void enterQuery(SqlBaseParser.QueryContext ctx) {
        String field = "pioneeryi";
        rewriter.insertAfter(ctx.stop, field);
    }

    public void exitQuery(SqlBaseParser.QueryContext ctx) {
        String field = "pioneeryi";
        rewriter.insertAfter(ctx.stop, field);
    }
}
