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

package com.cadenzauk.siesta.grammar.expression;

import com.cadenzauk.core.function.Function1;
import com.cadenzauk.core.function.FunctionOptional1;
import com.cadenzauk.core.util.OptionalUtil;
import com.cadenzauk.siesta.Alias;
import com.cadenzauk.siesta.Condition;
import com.cadenzauk.siesta.grammar.expression.condition.InCondition;
import com.cadenzauk.siesta.grammar.expression.condition.IsNullCondition;
import com.cadenzauk.siesta.grammar.expression.condition.LikeCondition;
import com.cadenzauk.siesta.grammar.expression.condition.OperatorExpressionCondition;
import com.cadenzauk.siesta.grammar.expression.condition.OperatorValueCondition;

import java.util.Optional;
import java.util.function.Function;

public class ExpressionBuilder<T, N> {
    private final TypedExpression<T> lhs;
    private final Function<BooleanExpression,N> onComplete;
    private Optional<Double> selectivity = Optional.empty();

    private ExpressionBuilder(TypedExpression<T> lhs, Function<BooleanExpression,N> onComplete) {
        this.lhs = lhs;
        this.onComplete = onComplete;
    }

    //---
    public N isEqualTo(T value) {
        return complete(new OperatorValueCondition<>("=", value, selectivity));
    }

    public N isEqualTo(TypedExpression<T> expression) {
        return complete(new OperatorExpressionCondition<>("=", expression));
    }

    public <R> N isEqualTo(Function1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>("=", UnresolvedColumn.of(getter)));
    }

    public <R> N isEqualTo(FunctionOptional1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>("=", UnresolvedColumn.of(getter)));
    }

    public <R> N isEqualTo(String alias, Function1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>("=", UnresolvedColumn.of(alias, getter)));
    }

    public <R> N isEqualTo(String alias, FunctionOptional1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>("=", UnresolvedColumn.of(alias, getter)));
    }

    public <R> N isEqualTo(Alias<R> alias, Function1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>("=", ResolvedColumn.of(alias, getter)));
    }

    public <R> N isEqualTo(Alias<R> alias, FunctionOptional1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>("=", ResolvedColumn.of(alias, getter)));
    }

    //---
    public N isNotEqualTo(T value) {
        return complete(new OperatorValueCondition<>("<>", value, selectivity));
    }

    public N isNotEqualTo(TypedExpression<T> expression) {
        return complete(new OperatorExpressionCondition<>("<>", expression));
    }

    public <R> N isNotEqualTo(Function1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>("<>", UnresolvedColumn.of(getter)));
    }

    public <R> N isNotEqualTo(FunctionOptional1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>("<>", UnresolvedColumn.of(getter)));
    }

    public <R> N isNotEqualTo(String alias, Function1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>("<>", UnresolvedColumn.of(alias, getter)));
    }

    public <R> N isNotEqualTo(String alias, FunctionOptional1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>("<>", UnresolvedColumn.of(alias, getter)));
    }

    public <R> N isNotEqualTo(Alias<R> alias, Function1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>("<>", ResolvedColumn.of(alias, getter)));
    }

    public <R> N isNotEqualTo(Alias<R> alias, FunctionOptional1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>("<>", ResolvedColumn.of(alias, getter)));
    }

    //---
    public N isGreaterThan(T value) {
        return complete(new OperatorValueCondition<>(">", value, selectivity));
    }

    public N isGreaterThan(TypedExpression<T> expression) {
        return complete(new OperatorExpressionCondition<>(">", expression));
    }

    public <R> N isGreaterThan(Function1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>(">", UnresolvedColumn.of(getter)));
    }

    public <R> N isGreaterThan(FunctionOptional1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>(">", UnresolvedColumn.of(getter)));
    }

    public <R> N isGreaterThan(String alias, Function1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>(">", UnresolvedColumn.of(alias, getter)));
    }

    public <R> N isGreaterThan(String alias, FunctionOptional1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>(">", UnresolvedColumn.of(alias, getter)));
    }

    public <R> N isGreaterThan(Alias<R> alias, Function1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>(">", ResolvedColumn.of(alias, getter)));
    }

    public <R> N isGreaterThan(Alias<R> alias, FunctionOptional1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>(">", ResolvedColumn.of(alias, getter)));
    }

   //---
    public N isLessThan(T value) {
        return complete(new OperatorValueCondition<>("<", value, selectivity));
    }

    public N isLessThan(TypedExpression<T> expression) {
        return complete(new OperatorExpressionCondition<>("<", expression));
    }

    public <R> N isLessThan(Function1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>("<", UnresolvedColumn.of(getter)));
    }

    public <R> N isLessThan(FunctionOptional1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>("<", UnresolvedColumn.of(getter)));
    }

    public <R> N isLessThan(String alias, Function1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>("<", UnresolvedColumn.of(alias, getter)));
    }

    public <R> N isLessThan(String alias, FunctionOptional1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>("<", UnresolvedColumn.of(alias, getter)));
    }

    public <R> N isLessThan(Alias<R> alias, Function1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>("<", ResolvedColumn.of(alias, getter)));
    }

    public <R> N isLessThan(Alias<R> alias, FunctionOptional1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>("<", ResolvedColumn.of(alias, getter)));
    }

    //---
    public N isGreaterThanOrEqualTo(T value) {
        return complete(new OperatorValueCondition<>(">=", value, selectivity));
    }

    public N isGreaterThanOrEqualTo(TypedExpression<T> expression) {
        return complete(new OperatorExpressionCondition<>(">=", expression));
    }

    public <R> N isGreaterThanOrEqualTo(Function1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>(">=", UnresolvedColumn.of(getter)));
    }

    public <R> N isGreaterThanOrEqualTo(FunctionOptional1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>(">=", UnresolvedColumn.of(getter)));
    }

    public <R> N isGreaterThanOrEqualTo(String alias, Function1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>(">=", UnresolvedColumn.of(alias, getter)));
    }

    public <R> N isGreaterThanOrEqualTo(String alias, FunctionOptional1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>(">=", UnresolvedColumn.of(alias, getter)));
    }

    public <R> N isGreaterThanOrEqualTo(Alias<R> alias, Function1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>(">=", ResolvedColumn.of(alias, getter)));
    }

    public <R> N isGreaterThanOrEqualTo(Alias<R> alias, FunctionOptional1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>(">=", ResolvedColumn.of(alias, getter)));
    }

    //---
    public N isLessThanOrEqualTo(T value) {
        return complete(new OperatorValueCondition<>("<=", value, selectivity));
    }

    public N isLessThanOrEqualTo(TypedExpression<T> expression) {
        return complete(new OperatorExpressionCondition<>("<=", expression));
    }

    public <R> N isLessThanOrEqualTo(Function1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>("<=", UnresolvedColumn.of(getter)));
    }

    public <R> N isLessThanOrEqualTo(FunctionOptional1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>("<=", UnresolvedColumn.of(getter)));
    }

    public <R> N isLessThanOrEqualTo(String alias, Function1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>("<=", UnresolvedColumn.of(alias, getter)));
    }

    public <R> N isLessThanOrEqualTo(String alias, FunctionOptional1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>("<=", UnresolvedColumn.of(alias, getter)));
    }

    public <R> N isLessThanOrEqualTo(Alias<R> alias, Function1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>("<=", ResolvedColumn.of(alias, getter)));
    }

    public <R> N isLessThanOrEqualTo(Alias<R> alias, FunctionOptional1<R,T> getter) {
        return complete(new OperatorExpressionCondition<>("<=", ResolvedColumn.of(alias, getter)));
    }

    //---
    @SafeVarargs
    public final N isIn(T... values) {
        return isOpIn("in", values);
    }

    @SafeVarargs
    public final N isNotIn(T... values) {
        return isOpIn("not in", values);
    }

    private N isOpIn(String operator, T[] values) {
        if (values.length == 0) {
            throw new IllegalArgumentException("At least one value is required for an IN expression.");
        }
        return complete(new InCondition<>(operator, values));
    }

    //---
    public N isNull() {
        return complete(new IsNullCondition<>(""));
    }

    public N isNotNull() {
        return complete(new IsNullCondition<>("not "));
    }

    //---
    public N isLike(T value) {
        return complete(new LikeCondition<>("like", value, Optional.empty()));
    }

    public N isLike(T value, String escape) {
        return complete(new LikeCondition<>("like", value, OptionalUtil.ofBlankable(escape)));
    }

    public N isNotLike(T value) {
        return complete(new LikeCondition<>("not like", value, Optional.empty()));
    }

    public N isNotLike(T value, String escape) {
        return complete(new LikeCondition<>("not like", value, OptionalUtil.ofBlankable(escape)));
    }

    //---
    public ExpressionBuilder<T,N> selectivity(double v) {
        selectivity = Optional.of(v);
        return this;
    }

    private N complete(Condition<T> rhs) {
        return onComplete.apply(new FullExpression<>(lhs, rhs));
    }

    public static <T, N> ExpressionBuilder<T,N> of(TypedExpression<T> lhs, Function<BooleanExpression,N> onComplete) {
        return new ExpressionBuilder<>(lhs, onComplete);
    }
}
