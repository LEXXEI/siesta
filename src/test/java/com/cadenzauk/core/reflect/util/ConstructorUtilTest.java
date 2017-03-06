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
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static com.cadenzauk.core.testutil.FluentAssert.calling;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class ConstructorUtilTest {
    @Test
    public void cannotInstantiate() {
        calling(() -> Factory.forClass(ConstructorUtil.class).get())
            .shouldThrow(RuntimeException.class)
            .withCause(InvocationTargetException.class)
            .withCause(RuntimeInstantiationException.class);
    }

    @Test
    public void newInstanceWithNoArgs() throws Exception {
        Constructor<ClassWithMultipleConstructors> constructor = ClassUtil.constructor(ClassWithMultipleConstructors.class)
            .orElseThrow(() -> new AssertionError("Failed to get constructor from " + ClassWithMultipleConstructors.class));

        ClassWithMultipleConstructors result = ConstructorUtil.newInstance(constructor);

        assertThat(result, notNullValue());
        assertThat(result.intValue(), is(501));
    }

    @Test
    public void newInstanceWithArg() throws Exception {
        Constructor<ClassWithMultipleConstructors> constructor = ClassUtil.constructor(ClassWithMultipleConstructors.class, Integer.TYPE)
            .orElseThrow(() -> new AssertionError("Failed to get constructor from " + ClassWithMultipleConstructors.class));

        ClassWithMultipleConstructors result = ConstructorUtil.newInstance(constructor, 123);

        assertThat(result, notNullValue());
        assertThat(result.intValue(), is(123));
    }

    @Test
    public void newInstanceRethrowsIfConstructorThrows() {
        Constructor<ClassWithMultipleConstructors> constructor = ClassUtil.constructor(ClassWithMultipleConstructors.class, String.class)
            .orElseThrow(() -> new AssertionError("Failed to get constructor from " + ClassWithMultipleConstructors.class));

        calling(() -> ConstructorUtil.newInstance(constructor, "Bob"))
            .shouldThrow(RuntimeException.class)
            .withCause(InvocationTargetException.class)
            .withCause(NumberFormatException.class);
    }

    @Test
    public void newInstanceRethrowsIfNotInstantiated() {
        Constructor<AnAbstractClass> constructor = ClassUtil.constructor(AnAbstractClass.class)
            .orElseThrow(() -> new AssertionError("Failed to get constructor from " + AnAbstractClass.class));

        calling(() -> ConstructorUtil.newInstance(constructor))
            .shouldThrow(RuntimeException.class)
            .withCause(InstantiationException.class);
    }


    private static abstract class AnAbstractClass {
    }

    @SuppressWarnings("unused")
    private static class ClassWithMultipleConstructors extends AnAbstractClass {
        private final int intValue;

        private ClassWithMultipleConstructors() {
            intValue = 501;
        }

        private ClassWithMultipleConstructors(int intValue) {
            this.intValue = intValue;
        }

        protected ClassWithMultipleConstructors(String intValue) {
            this.intValue = Integer.parseInt(intValue);
        }

        public int intValue() {
            return intValue;
        }
    }
}
