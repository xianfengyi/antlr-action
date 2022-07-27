package com.github.pioneeryi;

import com.github.pioneeryi.codegen.CalculatorBaseVisitor;
import com.github.pioneeryi.codegen.CalculatorParser;
import com.github.pioneeryi.codegen.CalculatorParser.NumContext;
import org.apache.velocity.tools.generic.MathTool;

/**
 * Implement calculator via visitor model.
 *
 * @author pioneeryi
 * @since 2021/7/14 8:18 下午
 */
public class ArithmeticEvalVisitor extends CalculatorBaseVisitor<Number> {

    private final MathTool mathTool = new MathTool();

    @Override
    public Number visitMulDiv(CalculatorParser.MulDivContext ctx) {
        Object left = visit(ctx.expr(0));
        Object right = visit(ctx.expr(1));
        if (ctx.op.getType() == CalculatorParser.MUL) {
            return mathTool.mul(left, right);
        }
        return mathTool.div(left, right);
    }

    @Override
    public Number visitAddSub(CalculatorParser.AddSubContext ctx) {
        Object left = visit(ctx.expr(0));
        Object right = visit(ctx.expr(1));
        if (ctx.op.getType() == CalculatorParser.Add) {
            return mathTool.add(left, right);
        }
        return mathTool.sub(left, right);
    }

    @Override
    public Number visitNum(NumContext ctx) {
        String numberValue = ctx.number().getText();
        if (numberValue.contains(".")) {
            return Float.parseFloat(numberValue);
        }
        return Integer.parseInt(numberValue);
    }

    @Override
    public Number visitParens(CalculatorParser.ParensContext ctx) {
        return visit(ctx.expr());
    }
}
