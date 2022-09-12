# Introduction
This project contains multi tools based on antlr4,such as calculator, csv loader, sql parser. From these simple project
,we can learn how to use antlr4 to develop our tool.

# Modules
* A simple calculator
* A csv file loader
* A sql parser

# Calculator
More detail, see: https://zhuanlan.zhihu.com/p/546679086


# SQL Parser
Use antlr develop some SQL tool,such as:
* Table name and column name extractor
* Add table name prefix to column

A simple like this:
```sql
SELECT A,B FROM T1 WHERE C='pioneeryi'
```
SQLUtil.getTables(sql), return:
```sql
T1
```
SQLUtil.getColumns(sql), return:
```sql
A,B,C
```

SQLUtil.appendTablePrefixToColumn(sql), return:
```sql
SELECT T1.A,T1.B FROM T1 WHERE T1.C='pioneeryi'
```
