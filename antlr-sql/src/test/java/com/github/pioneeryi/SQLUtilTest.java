package com.github.pioneeryi;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SQLUtilTest {

    @Test
    public void testGetColumns() {
        List<String> columnNames = SQLUtil.getColumns("SELECT A,B FROM T1 WHERE C='pioneeryi'");
        Assert.assertEquals(3, columnNames.size());
    }

    @Test
    public void testGetTables() {
        List<String> tableNames = SQLUtil.getTables("SELECT A,B FROM T1 WHERE C='pioneeryi'");
        Assert.assertEquals(1, tableNames.size());
        Assert.assertEquals("T1", tableNames.get(0));
    }

    @Test
    public void testAppendTablePrefixToColumn() {
        String actualSQl = SQLUtil.appendTablePrefixToColumn("SELECT A,B FROM T1 WHERE C='pioneeryi'");
        Assert.assertEquals("SELECT T1.A,T1.B FROM T1 WHERE T1.C='pioneeryi'", actualSQl);
    }
}