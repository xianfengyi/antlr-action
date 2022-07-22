package com.github.pioneeryi;

import com.github.pioneeryi.codegen.CalculatorBaseListener;
import com.github.pioneeryi.codegen.CalculatorParser;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 通过Listener 遍历语法树.
 *
 * @author pioneeryi
 * @since 2021/7/15 9:22 上午
 */
public class ArithmeticEvalListener extends CalculatorBaseListener {

    private final Deque<Integer> stack = new LinkedList<>();

    @Override
    public void enterMulDiv(CalculatorParser.MulDivContext ctx) {
        int right = this.stack.pop();
        int left = this.stack.pop();
        if (ctx.op.getType() == CalculatorParser.MUL) {
            this.stack.push(left * right);
        } else {
            this.stack.push(left / right);
        }
    }

    @Override
    public void exitAddSub(CalculatorParser.AddSubContext ctx) {
        int right = this.stack.pop();
        int left = this.stack.pop();
        if (ctx.op.getType() == CalculatorParser.Add) {
            this.stack.push(left + right);
        } else {
            this.stack.push(left - right);
        }
    }

    @Override
    public void exitInt(CalculatorParser.IntContext ctx) {
        Integer number = Integer.parseInt(ctx.INT().getText());
        this.stack.push(number);
    }

    /**
     * The last value on the stack is the result of all applied calculations.
     *
     * @return Integer
     */
    public Integer getResult() {
        return this.stack.pop();
    }
}
