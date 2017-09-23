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

import com.cadenzauk.siesta.dialect.function.FunctionName;
import com.cadenzauk.siesta.dialect.function.FunctionSpec;
import com.cadenzauk.siesta.type.TypeAdapter;

import java.util.Optional;
import java.util.stream.Stream;

public interface Dialect {
    String selectivity(double s);

    boolean requiresFromDual();

    String qualifiedName(String catalog, String schema, String name);

    String dual();

    void registerFunction(FunctionName functionName, FunctionSpec functionSpec);

    FunctionSpec function(FunctionName name);

    <T> void registerType(Class<T> javaClass, TypeAdapter<T> type);

    <T> TypeAdapter<T> type(Class<T> type);

    boolean supportsMultiInsert();

    String concat(Stream<String> sql);

    String fetchFirst(String sql, long n);

    boolean supportsIsolationLevelInQuery();

    String isolationLevelSql(String sql, IsolationLevel level, Optional<LockLevel> keepLocks);

    String tinyintType();

    String smallintType();

    String integerType();

    String bigintType();

    String decimalType(int size, int prec);

    String doubleType();

    String realType();

    String dateType();

    String timestampType(Optional<Integer> prec);

    String varcharType(int size);

    String charType(int len);

    String nextFromSequence(String catalog, String schema, String sequenceName);

}
