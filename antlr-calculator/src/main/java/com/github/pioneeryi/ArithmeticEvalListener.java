package com.github.pioneeryi;

import com.github.pioneeryi.codegen.CalculatorBaseListener;
import com.github.pioneeryi.codegen.CalculatorParser;
import java.util.Deque;
import java.util.LinkedList;
import org.apache.velocity.tools.generic.MathTool;

/**
 * Implement calculator via listener model.
 *
 * @author pioneeryi
 * @since 2021/7/15 9:22 上午
 */
public class ArithmeticEvalListener extends CalculatorBaseListener {

    private final Deque<Number> stack = new LinkedList<>();

    private final MathTool mathTool = new MathTool();

    /**
     * The last value on the stack is the result of all applied calculations.
     *
     * @return Integer
     */
    public Number getResult() {
        return this.stack.peek();
    }

    @Override
    public void exitMulDiv(CalculatorParser.MulDivContext ctx) {
        Object right = this.stack.pop();
        Object left = this.stack.pop();
        if (ctx.op.getType() == CalculatorParser.MUL) {
            this.stack.push(mathTool.mul(left, right));
        } else {
            this.stack.push(mathTool.div(left, right));
        }
    }

    @Override
    public void exitAddSub(CalculatorParser.AddSubContext ctx) {
        Object right = this.stack.pop();
        Object left = this.stack.pop();
        if (ctx.op.getType() == CalculatorParser.Add) {
            this.stack.push(mathTool.add(left, right));
        } else {
            this.stack.push(mathTool.sub(left, right));
        }
    }

    @Override
    public void exitNum(CalculatorParser.NumContext ctx) {
        String numberValue = ctx.number().getText();
        if (numberValue.contains(".")) {
            this.stack.push(Float.parseFloat(numberValue));
        } else {
            this.stack.push(Integer.parseInt(numberValue));
        }
    }
}
