package com.github.pioneeryi;

import com.github.pioneeryi.codegen.CSVBaseListener;
import com.github.pioneeryi.codegen.CSVParser;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 通过Listener 加载 CSV 数据.
 *
 * @author pioneeryi
 * @since 2022/7/14 21:22
 */
public class CSVLoader extends CSVBaseListener {

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
        if (header == null) {
            return;
        }
        Map<String, String> oneRow = new LinkedHashMap<>();
        for (int i = 0; i < header.size(); i++) {
            oneRow.put(header.get(i), row.get(i));
        }
        rows.add(oneRow);
    }

    @Override
    public void exitText(CSVParser.TextContext ctx) {
        row.add(ctx.TEXT().getText());
    }

    @Override
    public void exitString(CSVParser.StringContext ctx) {
        row.add(ctx.STRING().getText());
    }

    @Override
    public void exitEmpty(CSVParser.EmptyContext ctx) {
        row.add("");
    }

    public List<Map<String, String>> getRows() {
        return rows;
    }
}
