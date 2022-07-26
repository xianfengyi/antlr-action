package com.github.pioneeryi;

import com.github.pioneeryi.codegen.CalculatorBaseVisitor;
import com.github.pioneeryi.codegen.CalculatorParser;
import com.github.pioneeryi.codegen.CalculatorParser.NumContext;

/**
 * Implement calculator via visitor model.
 *
 * @author pioneeryi
 * @since 2021/7/14 8:18 下午
 */
public class ArithmeticEvalVisitor extends CalculatorBaseVisitor<Number> {

    @Override
    public Number visitMulDiv(CalculatorParser.MulDivContext ctx) {
        Object left = visit(ctx.expr(0));
        Object right = visit(ctx.expr(1));
        if (ctx.op.getType() == CalculatorParser.MUL) {
            return left * right;
        }
        return left / right;
    }

    @Override
    public Number visitAddSub(CalculatorParser.AddSubContext ctx) {
        Object left = visit(ctx.expr(0));
        Object right = visit(ctx.expr(1));
        if (ctx.op.getType() == CalculatorParser.Add) {
            return left + right;
        }
        return left - right;
    }

    @Override
    public Number visitNum(NumContext ctx) {
        return super.visitNum(ctx);
    }

    @Override
    public Number visitParens(CalculatorParser.ParensContext ctx) {
        return visit(ctx.expr());
    }
}
