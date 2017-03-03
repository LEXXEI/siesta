/*
 * Copyright (c) 2017 Cadenza United Kingdom Limited.
 *
 * All rights reserved.  May not be used without permission.
 */

package com.cadenzauk.core.reflect;

import com.cadenzauk.core.reflect.util.ClassUtil;
import org.junit.Test;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.lang.reflect.Field;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.cadenzauk.core.testutil.FluentAssert.calling;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class FieldInfoTest {
    @Test
    public void optionalGetterOnOptionalField() {
        Field field = optionalStringField();
        FieldInfo<ClassWithField,?> fieldInfo = FieldInfo.of(ClassWithField.class, field);
        ClassWithField target = mock(ClassWithField.class);
        when(target.optionalString()).thenReturn(Optional.of("Bobby"));

        Optional<?> result = fieldInfo.optionalGetter().apply(target);

        assertThat(result, is(Optional.of("Bobby")));
        verify(target).optionalString();
        verifyNoMoreInteractions(target);
    }

    @Test
    public void optionalGetterOnNonOptionalField() {
        Field field = stringField();
        FieldInfo<ClassWithField,?> fieldInfo = FieldInfo.of(ClassWithField.class, field);
        ClassWithField target = mock(ClassWithField.class);
        when(target.string()).thenReturn("Freddy");

        Optional<?> result = fieldInfo.optionalGetter().apply(target);

        assertThat(result, is(Optional.of("Freddy")));
        verify(target).string();
        verifyNoMoreInteractions(target);
    }

    @Test
    public void ofOptionalWithoutType() throws Exception {
        Field field = optionalStringField();

        FieldInfo<ClassWithField,?> fieldInfo = FieldInfo.of(ClassWithField.class, field);

        assertThat(fieldInfo.declaringClass(), equalTo(ClassWithField.class));
        assertThat(fieldInfo.name(), equalTo("optionalString"));
        assertThat(fieldInfo.field(), sameInstance(field));
        assertThat(fieldInfo.fieldType(), equalTo(Optional.class));
        assertThat(fieldInfo.effectiveType(), equalTo(String.class));
    }

    @Test
    public void ofNonOptionalWithoutType() throws Exception {
        Field field = stringField();

        FieldInfo<ClassWithField,?> fieldInfo = FieldInfo.of(ClassWithField.class, field);

        assertThat(fieldInfo.declaringClass(), equalTo(ClassWithField.class));
        assertThat(fieldInfo.name(), equalTo("string"));
        assertThat(fieldInfo.field(), sameInstance(field));
        assertThat(fieldInfo.fieldType(), equalTo(String.class));
        assertThat(fieldInfo.effectiveType(), equalTo(String.class));
    }

    @Test
    public void ofOptionalWithType() throws Exception {
        Field field = optionalStringField();

        FieldInfo<ClassWithField,String> fieldInfo = FieldInfo.of(ClassWithField.class, field, String.class);

        assertThat(fieldInfo.declaringClass(), equalTo(ClassWithField.class));
        assertThat(fieldInfo.name(), equalTo("optionalString"));
        assertThat(fieldInfo.field(), sameInstance(field));
        assertThat(fieldInfo.fieldType(), equalTo(Optional.class));
        assertThat(fieldInfo.effectiveType(), equalTo(String.class));
    }

    @Test
    public void ofNonOptionalWithType() throws Exception {
        Field field = stringField();

        FieldInfo<ClassWithField,String> fieldInfo = FieldInfo.of(ClassWithField.class, field, String.class);

        assertThat(fieldInfo.declaringClass(), equalTo(ClassWithField.class));
        assertThat(fieldInfo.name(), equalTo("string"));
        assertThat(fieldInfo.field(), sameInstance(field));
        assertThat(fieldInfo.fieldType(), equalTo(String.class));
        assertThat(fieldInfo.effectiveType(), equalTo(String.class));
    }

    @Test
    public void ofOptionalByName() throws Exception {
        FieldInfo<ClassWithField,String> fieldInfo = FieldInfo.of(ClassWithField.class, "optionalString", String.class);

        assertThat(fieldInfo.declaringClass(), equalTo(ClassWithField.class));
        assertThat(fieldInfo.name(), equalTo("optionalString"));
        assertThat(fieldInfo.fieldType(), equalTo(Optional.class));
        assertThat(fieldInfo.effectiveType(), equalTo(String.class));
    }

    @Test
    public void ofNonOptionalByName() throws Exception {
        FieldInfo<ClassWithField,String> fieldInfo = FieldInfo.of(ClassWithField.class, "string", String.class);

        assertThat(fieldInfo.declaringClass(), equalTo(ClassWithField.class));
        assertThat(fieldInfo.name(), equalTo("string"));
        assertThat(fieldInfo.fieldType(), equalTo(String.class));
        assertThat(fieldInfo.effectiveType(), equalTo(String.class));
    }

    @Test
    public void ofWrongTypeThrowsException() throws Exception {
        calling(() -> FieldInfo.of(ClassWithField.class, "string", Integer.class))
            .shouldThrow(NoSuchElementException.class)
            .withMessage(is("No field called string of type class java.lang.Integer in class com.cadenzauk.core.reflect.FieldInfoTest$ClassWithField"));
    }

    @Test
    public void ofOptionalGetter() throws Exception {
        FieldInfo<ClassWithField,String> fieldInfo = FieldInfo.ofGetter(MethodInfo.of(ClassWithField::optionalString))
            .orElseThrow(() -> new AssertionError("Should have got FieldInfo for optionalString but didn't"));

        assertThat(fieldInfo.declaringClass(), equalTo(ClassWithField.class));
        assertThat(fieldInfo.name(), equalTo("optionalString"));
        assertThat(fieldInfo.fieldType(), equalTo(Optional.class));
        assertThat(fieldInfo.effectiveType(), equalTo(String.class));
    }

    @Test
    public void ofGetter() throws Exception {
        FieldInfo<ClassWithField,String> fieldInfo = FieldInfo.ofGetter(MethodInfo.of(ClassWithField::string))
            .orElseThrow(() -> new AssertionError("Should have got FieldInfo for string but didn't"));

        assertThat(fieldInfo.declaringClass(), equalTo(ClassWithField.class));
        assertThat(fieldInfo.name(), equalTo("string"));
        assertThat(fieldInfo.fieldType(), equalTo(String.class));
        assertThat(fieldInfo.effectiveType(), equalTo(String.class));
    }

    @Test
    public void annotationPresent() throws Exception {
        FieldInfo<ClassWithField,String> fieldInfo = FieldInfo.ofGetter(MethodInfo.of(ClassWithField::string))
            .orElseThrow(() -> new AssertionError("Should have got FieldInfo for string but didn't"));

        Optional<XmlElement> result = fieldInfo.annotation(XmlElement.class);

        assertThat(result.isPresent(), is(true));
        assertThat(result.map(XmlElement::name), is(Optional.of("horace")));
    }

    @Test
    public void annotationNotPresent() throws Exception {
        FieldInfo<ClassWithField,String> fieldInfo = FieldInfo.ofGetter(MethodInfo.of(ClassWithField::optionalString))
            .orElseThrow(() -> new AssertionError("Should have got FieldInfo for optionalString but didn't"));

        Optional<XmlAttribute> result = fieldInfo.annotation(XmlAttribute.class);

        assertThat(result, is(Optional.empty()));
    }

    private Field stringField() {
        return ClassUtil.getDeclaredField(ClassWithField.class, "string");
    }

    private Field optionalStringField() {
        return ClassUtil.getDeclaredField(ClassWithField.class, "optionalString");
    }

    private static class ClassWithField {
        private Optional<String> optionalString;
        @XmlElement(name = "horace")
        private String string;

        public Optional<String> optionalString() {
            return optionalString;
        }

        public String string() {
            return string;
        }
    }

}
