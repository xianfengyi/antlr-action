package com.github.pioneeryi.tree;

import javax.annotation.Nullable;

public abstract class AstVisitor<R, C> {

    public R process(Node node) {
        return process(node, null);
    }

    public R process(Node node, @Nullable C context) {
        return node.accept(this, context);
    }

    protected R visitNode(Node node, C context) {
        return null;
    }

    protected R visitExpression(Expression node, C context) {
        return visitNode(node, context);
    }

    protected R visitSelect(Select node, C context) {
        return visitNode(node, context);
    }

    protected R visitRelation(Relation node, C context) {
        return visitNode(node, context);
    }

    protected R visitQueryBody(QueryBody node, C context) {
        return visitRelation(node, context);
    }

}
