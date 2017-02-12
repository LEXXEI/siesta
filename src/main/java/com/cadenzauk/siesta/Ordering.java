/*
 * Copyright (c) 2017 Cadenza United Kingdom Limited.
 *
 * All rights reserved.   May not be used without permission.
 */

package com.cadenzauk.siesta;

import com.cadenzauk.siesta.catalog.Column;

public class Ordering<T, R> {
    private final Alias<R> alias;
    private final Column<T, R> column;
    private final Order order;

    public Ordering(Alias<R> alias, Column<T,R> column, Order order) {
        this.alias = alias;
        this.column = column;
        this.order = order;
    }

    public String sql() {
        return alias.inExpression(column) + " " + order.sql();
    }
}
