/*
 * Copyright (c) 2017 Cadenza United Kingdom Limited
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.cadenzauk.siesta;

import com.cadenzauk.core.function.Function1;
import com.cadenzauk.core.function.FunctionOptional1;
import com.cadenzauk.siesta.catalog.Table;
import com.cadenzauk.siesta.expression.AndExpression;
import com.cadenzauk.siesta.expression.Expression;
import com.cadenzauk.siesta.expression.ResolvedColumn;
import com.cadenzauk.siesta.expression.TypedExpression;
import com.cadenzauk.siesta.expression.UnresolvedColumn;
import com.cadenzauk.siesta.grammar.ExpressionBuilder;
import com.cadenzauk.siesta.grammar.update.SetClause;
import com.cadenzauk.siesta.grammar.update.SetExpressionBuilder;
import com.cadenzauk.siesta.grammar.update.UpdateStatement;
import com.cadenzauk.siesta.grammar.update.WhereClause;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class Update<U> {
    private final UpdateStatement<U> statement = new Statement();
    private final Scope scope;
    private final Alias<U> alias;
    private final List<Expression> sets = new ArrayList<>();
    private Expression whereClause;

    private Update(Database database, Alias<U> alias) {
        this.alias = alias;
        scope = new Scope(database, alias);
    }

    public <T> SetExpressionBuilder<T,SetClause<U>> set(Function1<U,T> lhs) {
        return SetExpressionBuilder.of(UnresolvedColumn.of(lhs), this::addSet);
    }

    public <T> SetExpressionBuilder<T,SetClause<U>> set(FunctionOptional1<U,T> lhs) {
        return SetExpressionBuilder.of(UnresolvedColumn.of(lhs), this::addSet);
    }

    public <T> ExpressionBuilder<T,WhereClause<U>> where(TypedExpression<T> lhs) {
        return ExpressionBuilder.of(lhs, this::setWhereClause);
    }

    public <T, R> ExpressionBuilder<T,WhereClause<U>> where(Function1<R,T> lhs) {
        return ExpressionBuilder.of(UnresolvedColumn.of(lhs), this::setWhereClause);
    }

    public <T, R> ExpressionBuilder<T,WhereClause<U>> where(FunctionOptional1<R,T> lhs) {
        return ExpressionBuilder.of(UnresolvedColumn.of(lhs), this::setWhereClause);
    }

    public <T, R> ExpressionBuilder<T,WhereClause<U>> where(String alias, Function1<R,T> lhs) {
        return ExpressionBuilder.of(UnresolvedColumn.of(alias, lhs), this::setWhereClause);
    }

    public <T, R> ExpressionBuilder<T,WhereClause<U>> where(String alias, FunctionOptional1<R,T> lhs) {
        return ExpressionBuilder.of(UnresolvedColumn.of(alias, lhs), this::setWhereClause);
    }

    public <T, R> ExpressionBuilder<T,WhereClause<U>> where(Alias<R> alias, Function1<R,T> lhs) {
        return ExpressionBuilder.of(ResolvedColumn.of(alias, lhs), this::setWhereClause);
    }

    public <T, R> ExpressionBuilder<T,WhereClause<U>> where(Alias<R> alias, FunctionOptional1<R,T> lhs) {
        return ExpressionBuilder.of(ResolvedColumn.of(alias, lhs), this::setWhereClause);
    }

    public String sql() {
        return String.format("update %s as %s set %s%s",
            alias.table().qualifiedName(),
            alias.aliasName(),
            sets.stream().map(e -> e.sql(scope)).collect(joining(", ")),
            whereClauseSql());
    }

    public int execute(SqlExecutor sqlExecutor) {
        Object[] args = args().toArray();
        String sql = sql();
        System.out.println(sql);
        return sqlExecutor.update(sql, args);
    }

    private SetClause<U> addSet(Expression expression) {
        sets.add(expression);
        return new SetClause<U>(statement);
    }

    private String whereClauseSql() {
        return whereClause == null ? "" : " where " + whereClause.sql(scope);
    }

    private Stream<Object> args() {
        return Stream.concat(
            sets.stream().flatMap(Expression::args),
            whereClause == null ? Stream.empty() : whereClause.args()
        );
    }

    private WhereClause<U> setWhereClause(Expression e) {
        whereClause = e;
        return new WhereClause<>(statement);
    }

    private void andWhere(Expression newClause) {
        whereClause = new AndExpression(whereClause, newClause);
    }

    static <U> Update<U> update(Database database, Table<U> table) {
        return new Update<>(database, table.as(table.tableName()));
    }

    private class Statement implements UpdateStatement<U> {
        @Override
        public int execute(SqlExecutor sqlExecutor) {
            return Update.this.execute(sqlExecutor);
        }

        @Override
        public WhereClause<U> setWhereClause(Expression e) {
            return Update.this.setWhereClause(e);
        }

        @Override
        public void andWhere(Expression newClause) {
            Update.this.andWhere(newClause);
        }

        @Override
        public SetClause<U> addSet(Expression expression) {
            return Update.this.addSet(expression);
        }

        @Override
        public String sql() {
            return Update.this.sql();
        }
    }

}
