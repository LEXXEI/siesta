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

package com.cadenzauk.siesta.grammar.dml;

import com.cadenzauk.core.function.Function1;
import com.cadenzauk.core.function.FunctionOptional1;
import com.cadenzauk.siesta.Alias;
import com.cadenzauk.siesta.grammar.expression.BooleanExpression;
import com.cadenzauk.siesta.grammar.expression.ExpressionBuilder;
import com.cadenzauk.siesta.grammar.expression.ParenthesisedExpression;
import com.cadenzauk.siesta.grammar.expression.ResolvedColumn;
import com.cadenzauk.siesta.grammar.expression.TypedExpression;
import com.cadenzauk.siesta.grammar.expression.UnresolvedColumn;

public class InWhereExpectingAnd extends ExecutableStatementClause {
    private final ExecutableStatement statement;

    public InWhereExpectingAnd(ExecutableStatement statement) {
        super(statement);
        this.statement = statement;
    }

    public InWhereExpectingAnd and(BooleanExpression expression) {
        return andWhere(new ParenthesisedExpression(expression));
    }

    public <T> ExpressionBuilder<T,InWhereExpectingAnd> and(TypedExpression<T> lhs) {
        return ExpressionBuilder.of(lhs, this::andWhere);
    }

    public <T, R> ExpressionBuilder<T,InWhereExpectingAnd> and(Function1<R,T> lhs) {
        return ExpressionBuilder.of(UnresolvedColumn.of(lhs), this::andWhere);
    }

    public <T, R> ExpressionBuilder<T,InWhereExpectingAnd> and(FunctionOptional1<R,T> lhs) {
        return ExpressionBuilder.of(UnresolvedColumn.of(lhs), this::andWhere);
    }

    public <T, R> ExpressionBuilder<T,InWhereExpectingAnd> and(String alias, Function1<R,T> lhs) {
        return ExpressionBuilder.of(UnresolvedColumn.of(alias, lhs), this::andWhere);
    }

    public <T, R> ExpressionBuilder<T,InWhereExpectingAnd> and(String alias, FunctionOptional1<R,T> lhs) {
        return ExpressionBuilder.of(UnresolvedColumn.of(alias, lhs), this::andWhere);
    }

    public <T, R> ExpressionBuilder<T,InWhereExpectingAnd> and(Alias<R> alias, Function1<R,T> lhs) {
        return ExpressionBuilder.of(ResolvedColumn.of(alias, lhs), this::andWhere);
    }

    public <T, R> ExpressionBuilder<T,InWhereExpectingAnd> and(Alias<R> alias, FunctionOptional1<R,T> lhs) {
        return ExpressionBuilder.of(ResolvedColumn.of(alias, lhs), this::andWhere);
    }

    public InWhereExpectingAnd or(BooleanExpression expression) {
        return orWhere(new ParenthesisedExpression(expression));
    }

    public <T> ExpressionBuilder<T,InWhereExpectingAnd> or(TypedExpression<T> lhs) {
        return ExpressionBuilder.of(lhs, this::orWhere);
    }

    public <T, R> ExpressionBuilder<T,InWhereExpectingAnd> or(Function1<R,T> lhs) {
        return ExpressionBuilder.of(UnresolvedColumn.of(lhs), this::orWhere);
    }

    public <T, R> ExpressionBuilder<T,InWhereExpectingAnd> or(FunctionOptional1<R,T> lhs) {
        return ExpressionBuilder.of(UnresolvedColumn.of(lhs), this::orWhere);
    }

    public <T, R> ExpressionBuilder<T,InWhereExpectingAnd> or(String alias, Function1<R,T> lhs) {
        return ExpressionBuilder.of(UnresolvedColumn.of(alias, lhs), this::orWhere);
    }

    public <T, R> ExpressionBuilder<T,InWhereExpectingAnd> or(String alias, FunctionOptional1<R,T> lhs) {
        return ExpressionBuilder.of(UnresolvedColumn.of(alias, lhs), this::orWhere);
    }

    public <T, R> ExpressionBuilder<T,InWhereExpectingAnd> or(Alias<R> alias, Function1<R,T> lhs) {
        return ExpressionBuilder.of(ResolvedColumn.of(alias, lhs), this::orWhere);
    }

    public <T, R> ExpressionBuilder<T,InWhereExpectingAnd> or(Alias<R> alias, FunctionOptional1<R,T> lhs) {
        return ExpressionBuilder.of(ResolvedColumn.of(alias, lhs), this::orWhere);
    }

    private InWhereExpectingAnd andWhere(BooleanExpression newClause) {
        statement.andWhere(newClause);
        return this;
    }

    private InWhereExpectingAnd orWhere(BooleanExpression newClause) {
        statement.orWhere(newClause);
        return this;
    }
}
