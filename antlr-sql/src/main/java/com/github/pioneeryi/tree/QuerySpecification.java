package com.github.pioneeryi.tree;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.google.common.base.MoreObjects.toStringHelper;

public class QuerySpecification extends QueryBody {

    private final Select select;
    private final Optional<Relation> from;

    public QuerySpecification(Optional<NodeLocation> location, Select select, Optional<Relation> from) {
        super(location);
        this.select = select;
        this.from = from;
    }

    @Override
    public List<? extends Node> getChildren() {
        ImmutableList.Builder<Node> nodes = ImmutableList.builder();
        nodes.add(select);
        from.ifPresent(nodes::add);
        return nodes.build();
    }

    @Override
    public int hashCode() {
        return Objects.hash(select, from);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        QuerySpecification o = (QuerySpecification) obj;
        return Objects.equals(select, o.select) && Objects.equals(from, o.from);

    }

    @Override
    public String toString() {
        return toStringHelper(this).add("select", select).add("from", from).toString();
    }
}
