package com.github.pioneeryi;

import com.github.pioneeryi.codegen.CalculatorBaseVisitor;
import com.github.pioneeryi.codegen.CalculatorParser;

/**
 * 通过访问者模式，实现计算器.
 *
 * @author pioneeryi
 * @since 2021/7/14 8:18 下午
 */
public class ArithmeticEvalVisitor extends CalculatorBaseVisitor<Integer> {

    @Override
    public Integer visitProg(CalculatorParser.ProgContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Integer visitMulDiv(CalculatorParser.MulDivContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));
        if (ctx.op.getType() == CalculatorParser.MUL) {
            return left * right;
        }
        return left / right;
    }

    @Override
    public Integer visitAddSub(CalculatorParser.AddSubContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));
        if (ctx.op.getType() == CalculatorParser.Add) {
            return left + right;
        }
        return left - right;
    }

    @Override
    public Integer visitInt(CalculatorParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }

    @Override
    public Integer visitParens(CalculatorParser.ParensContext ctx) {
        return visit(ctx.expr());
    }
}
