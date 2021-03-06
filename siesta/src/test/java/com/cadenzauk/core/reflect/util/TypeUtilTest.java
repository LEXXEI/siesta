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

package com.cadenzauk.core.reflect.util;

import com.cadenzauk.core.lang.RuntimeInstantiationException;
import com.cadenzauk.core.reflect.Factory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static com.cadenzauk.core.testutil.FluentAssert.calling;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.params.provider.ObjectArrayArguments.create;

class TypeUtilTest {
    @Test
    void cannotInstantiate() {
        calling(() -> Factory.forClass(TypeUtil.class).get())
            .shouldThrow(RuntimeException.class)
            .withCause(InvocationTargetException.class)
            .withCause(RuntimeInstantiationException.class);
    }

    private static Stream<Arguments> parametersForBoxedType() {
        return Stream.of(
            create(Long.TYPE, Long.class),
            create(Integer.TYPE, Integer.class),
            create(Short.TYPE, Short.class),
            create(Byte.TYPE, Byte.class),
            create(Double.TYPE, Double.class),
            create(Float.TYPE, Float.class),
            create(Character.TYPE, Character.class),
            create(Boolean.TYPE, Boolean.class)
        );
    }

    @ParameterizedTest
    @MethodSource(names = "parametersForBoxedType")
    void boxedType(Class<?> unboxed, Class<?> expected) {
        Class<?> result = TypeUtil.boxedType(unboxed);

        assertThat(result, equalTo(expected));
    }

    @SuppressWarnings("unused")
    private Map<Long,Character> longCharacterMap;
    @SuppressWarnings("unused")
    private Optional<String> optionalString;
    @SuppressWarnings("unused")
    private List<Integer> integerList;

    private static Stream<Arguments> parametersForActualTypeArgument() {
        return Stream.of(
            create(ClassUtil.getDeclaredField(TypeUtilTest.class, "optionalString").getGenericType(), 0, String.class),
            create(ClassUtil.getDeclaredField(TypeUtilTest.class, "integerList").getGenericType(), 0, Integer.class),
            create(ClassUtil.getDeclaredField(TypeUtilTest.class, "longCharacterMap").getGenericType(), 0, Long.class),
            create(ClassUtil.getDeclaredField(TypeUtilTest.class, "longCharacterMap").getGenericType(), 1, Character.class)
        );
    }

    @ParameterizedTest
    @MethodSource(names = "parametersForActualTypeArgument")
    void actualTypeArgument(Type input, int index, Class<?> expected) {
        Class<?> result = TypeUtil.actualTypeArgument((ParameterizedType) input, index);

        assertThat(result, equalTo(expected));
    }

}