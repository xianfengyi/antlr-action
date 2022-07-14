package com.github.pioneeryi;

import com.github.pioneeryi.codegen.CSVBaseListener;
import com.github.pioneeryi.codegen.CSVParser;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * 通过Listener 加载 CSV 数据.
 *
 * @author pioneeryi
 * @since 2022/7/14 21:22
 */
public class CSVLoaderListener extends CSVBaseListener {

    // 存储表头字段
    private List<String> header;

    // 当前行的字段值
    private List<String> row;

    // 存储每一行数据，Key 为字段名，Value 为字段值
    private List<Map<String, String>> rows = new ArrayList();

    @Override
    public void exitHeader(CSVParser.HeaderContext ctx) {
        header = row;
    }

    @Override
    public void enterRow(CSVParser.RowContext ctx) {
        row = new ArrayList<>();
    }

    @Override
    public void exitRow(CSVParser.RowContext ctx) {
    }

    @Override
    public void enterText(CSVParser.TextContext ctx) {
    }

    @Override
    public void exitText(CSVParser.TextContext ctx) {
    }

    @Override
    public void enterString(CSVParser.StringContext ctx) {
    }

    @Override
    public void exitString(CSVParser.StringContext ctx) {
    }

    @Override
    public void enterEmpty(CSVParser.EmptyContext ctx) {
    }

    @Override
    public void exitEmpty(CSVParser.EmptyContext ctx) {
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
    }

    @Override
    public void visitTerminal(TerminalNode node) {
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
    }

}
