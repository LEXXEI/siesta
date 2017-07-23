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
import com.cadenzauk.core.util.UtilityClass;
import com.cadenzauk.siesta.Alias;
import com.cadenzauk.siesta.Dialect;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;

public final class DateFunctions extends UtilityClass {
    //--
    public static TypedExpression<LocalDate> currentDate() {
        return DialectFunction.of("current_date", Dialect::today, LocalDate.class);
    }

    //--
    public static TypedExpression<LocalDateTime> currentTimestampLocal() {
        return DialectFunction.of("current_timestamp_local", Dialect::currentTimestamp, LocalDateTime.class);
    }

    //--
    public static TypedExpression<ZonedDateTime> currentTimestamp() {
        return DialectFunction.of("current_timestamp", Dialect::currentTimestamp, ZonedDateTime.class);
    }

    //--
    public static TypedExpression<Integer> year(LocalDate value) {
        return DialectFunction.of("year", Dialect::year, ValueExpression.of(value), Integer.class);
    }

    public static TypedExpression<Integer> year(LocalDateTime value) {
        return DialectFunction.of("year", Dialect::year, ValueExpression.of(value), Integer.class);
    }

    public static TypedExpression<Integer> year(ZonedDateTime value) {
        return DialectFunction.of("year", Dialect::year, ValueExpression.of(value), Integer.class);
    }

    public static <T extends Temporal> TypedExpression<Integer> year(TypedExpression<T> expression) {
        return DialectFunction.of("year", Dialect::year, expression, Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> year(Function1<R,T> methodReference) {
        return DialectFunction.of("year", Dialect::year, UnresolvedColumn.of(methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> year(FunctionOptional1<R, T> methodReference) {
        return DialectFunction.of("year", Dialect::year, UnresolvedColumn.of(methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> year(String alias, Function1<R,T> methodReference) {
        return DialectFunction.of("year", Dialect::year, UnresolvedColumn.of(alias, methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> year(String alias, FunctionOptional1<R, T> methodReference) {
        return DialectFunction.of("year", Dialect::year, UnresolvedColumn.of(alias, methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> year(Alias<R> alias, Function1<R,T> methodReference) {
        return DialectFunction.of("year", Dialect::year, ResolvedColumn.of(alias, methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> year(Alias<R> alias, FunctionOptional1<R, T> methodReference) {
        return DialectFunction.of("year", Dialect::year, ResolvedColumn.of(alias, methodReference), Integer.class);
    }

    //--
    public static TypedExpression<Integer> month(LocalDate value) {
        return DialectFunction.of("month", Dialect::month, ValueExpression.of(value), Integer.class);
    }

    public static TypedExpression<Integer> month(LocalDateTime value) {
        return DialectFunction.of("month", Dialect::month, ValueExpression.of(value), Integer.class);
    }

    public static TypedExpression<Integer> month(ZonedDateTime value) {
        return DialectFunction.of("month", Dialect::month, ValueExpression.of(value), Integer.class);
    }

    public static <T extends Temporal> TypedExpression<Integer> month(TypedExpression<T> expression) {
        return DialectFunction.of("month", Dialect::month, expression, Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> month(Function1<R,T> methodReference) {
        return DialectFunction.of("month", Dialect::month, UnresolvedColumn.of(methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> month(FunctionOptional1<R, T> methodReference) {
        return DialectFunction.of("month", Dialect::month, UnresolvedColumn.of(methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> month(String alias, Function1<R,T> methodReference) {
        return DialectFunction.of("month", Dialect::month, UnresolvedColumn.of(alias, methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> month(String alias, FunctionOptional1<R, T> methodReference) {
        return DialectFunction.of("month", Dialect::month, UnresolvedColumn.of(alias, methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> month(Alias<R> alias, Function1<R,T> methodReference) {
        return DialectFunction.of("month", Dialect::month, ResolvedColumn.of(alias, methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> month(Alias<R> alias, FunctionOptional1<R, T> methodReference) {
        return DialectFunction.of("month", Dialect::month, ResolvedColumn.of(alias, methodReference), Integer.class);
    }

    //--
    public static TypedExpression<Integer> day(LocalDate value) {
        return DialectFunction.of("day", Dialect::day, ValueExpression.of(value), Integer.class);
    }

    public static TypedExpression<Integer> day(LocalDateTime value) {
        return DialectFunction.of("day", Dialect::day, ValueExpression.of(value), Integer.class);
    }

    public static TypedExpression<Integer> day(ZonedDateTime value) {
        return DialectFunction.of("day", Dialect::day, ValueExpression.of(value), Integer.class);
    }

    public static <T extends Temporal> TypedExpression<Integer> day(TypedExpression<T> expression) {
        return DialectFunction.of("day", Dialect::day, expression, Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> day(Function1<R,T> methodReference) {
        return DialectFunction.of("day", Dialect::day, UnresolvedColumn.of(methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> day(FunctionOptional1<R, T> methodReference) {
        return DialectFunction.of("day", Dialect::day, UnresolvedColumn.of(methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> day(String alias, Function1<R,T> methodReference) {
        return DialectFunction.of("day", Dialect::day, UnresolvedColumn.of(alias, methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> day(String alias, FunctionOptional1<R, T> methodReference) {
        return DialectFunction.of("day", Dialect::day, UnresolvedColumn.of(alias, methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> day(Alias<R> alias, Function1<R,T> methodReference) {
        return DialectFunction.of("day", Dialect::day, ResolvedColumn.of(alias, methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> day(Alias<R> alias, FunctionOptional1<R, T> methodReference) {
        return DialectFunction.of("day", Dialect::day, ResolvedColumn.of(alias, methodReference), Integer.class);
    }

    //--
    public static TypedExpression<Integer> hour(LocalDateTime value) {
        return DialectFunction.of("hour", Dialect::hour, ValueExpression.of(value), Integer.class);
    }

    public static TypedExpression<Integer> hour(ZonedDateTime value) {
        return DialectFunction.of("hour", Dialect::hour, ValueExpression.of(value), Integer.class);
    }

    public static <T extends Temporal> TypedExpression<Integer> hour(TypedExpression<T> expression) {
        return DialectFunction.of("hour", Dialect::hour, expression, Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> hour(Function1<R,T> methodReference) {
        return DialectFunction.of("hour", Dialect::hour, UnresolvedColumn.of(methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> hour(FunctionOptional1<R, T> methodReference) {
        return DialectFunction.of("hour", Dialect::hour, UnresolvedColumn.of(methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> hour(String alias, Function1<R,T> methodReference) {
        return DialectFunction.of("hour", Dialect::hour, UnresolvedColumn.of(alias, methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> hour(String alias, FunctionOptional1<R, T> methodReference) {
        return DialectFunction.of("hour", Dialect::hour, UnresolvedColumn.of(alias, methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> hour(Alias<R> alias, Function1<R,T> methodReference) {
        return DialectFunction.of("hour", Dialect::hour, ResolvedColumn.of(alias, methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> hour(Alias<R> alias, FunctionOptional1<R, T> methodReference) {
        return DialectFunction.of("hour", Dialect::hour, ResolvedColumn.of(alias, methodReference), Integer.class);
    }

    //--
    public static TypedExpression<Integer> minute(LocalDateTime value) {
        return DialectFunction.of("minute", Dialect::minute, ValueExpression.of(value), Integer.class);
    }

    public static TypedExpression<Integer> minute(ZonedDateTime value) {
        return DialectFunction.of("minute", Dialect::minute, ValueExpression.of(value), Integer.class);
    }

    public static <T extends Temporal> TypedExpression<Integer> minute(TypedExpression<T> expression) {
        return DialectFunction.of("minute", Dialect::minute, expression, Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> minute(Function1<R,T> methodReference) {
        return DialectFunction.of("minute", Dialect::minute, UnresolvedColumn.of(methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> minute(FunctionOptional1<R, T> methodReference) {
        return DialectFunction.of("minute", Dialect::minute, UnresolvedColumn.of(methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> minute(String alias, Function1<R,T> methodReference) {
        return DialectFunction.of("minute", Dialect::minute, UnresolvedColumn.of(alias, methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> minute(String alias, FunctionOptional1<R, T> methodReference) {
        return DialectFunction.of("minute", Dialect::minute, UnresolvedColumn.of(alias, methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> minute(Alias<R> alias, Function1<R,T> methodReference) {
        return DialectFunction.of("minute", Dialect::minute, ResolvedColumn.of(alias, methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> minute(Alias<R> alias, FunctionOptional1<R, T> methodReference) {
        return DialectFunction.of("minute", Dialect::minute, ResolvedColumn.of(alias, methodReference), Integer.class);
    }

    //--
    public static TypedExpression<Integer> second(LocalDateTime value) {
        return DialectFunction.of("second", Dialect::second, ValueExpression.of(value), Integer.class);
    }

    public static TypedExpression<Integer> second(ZonedDateTime value) {
        return DialectFunction.of("second", Dialect::second, ValueExpression.of(value), Integer.class);
    }

    public static <T extends Temporal> TypedExpression<Integer> second(TypedExpression<T> expression) {
        return DialectFunction.of("second", Dialect::second, expression, Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> second(Function1<R,T> methodReference) {
        return DialectFunction.of("second", Dialect::second, UnresolvedColumn.of(methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> second(FunctionOptional1<R, T> methodReference) {
        return DialectFunction.of("second", Dialect::second, UnresolvedColumn.of(methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> second(String alias, Function1<R,T> methodReference) {
        return DialectFunction.of("second", Dialect::second, UnresolvedColumn.of(alias, methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> second(String alias, FunctionOptional1<R, T> methodReference) {
        return DialectFunction.of("second", Dialect::second, UnresolvedColumn.of(alias, methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> second(Alias<R> alias, Function1<R,T> methodReference) {
        return DialectFunction.of("second", Dialect::second, ResolvedColumn.of(alias, methodReference), Integer.class);
    }

    public static <R, T extends Temporal> TypedExpression<Integer> second(Alias<R> alias, FunctionOptional1<R, T> methodReference) {
        return DialectFunction.of("second", Dialect::second, ResolvedColumn.of(alias, methodReference), Integer.class);
    }

}
