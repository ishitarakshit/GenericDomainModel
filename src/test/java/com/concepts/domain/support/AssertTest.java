package com.concepts.domain.support;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.junit.Test;


public class AssertTest {

    @Test
    public void testIsNull() {
        Object o = new Object();
        try {
            Assert.isNull(o);
        }
        catch (IllegalArgumentException e) {
            assertEquals("[Assertion failed] - the argument must be null", e.getMessage());
        }
    }

    @Test
    public void testIsNullWithStringMessage() {
        Object o = new Object();
        try {
            Assert.isNull(o, "Expected a null argument.");
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Expected a null argument.", e.getMessage());
        }
    }

    @Test
    public void testIsNullWithNullObject() {
        Assert.isNull(null);
    }

    @Test
    public void testNotNull() {
        try {
            Assert.notNull(null);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("[Assertion failed] - this argument must not be null", e.getMessage());
        }
    }

    @Test
    public void testNotNullWithStringMessage() {
        try {
            Assert.notNull(null, "Expected a non-null argument.");
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Expected a non-null argument.", e.getMessage());
        }
    }

    @Test
    public void testNotNullWithObject() {
        Object o = new Object();
        Assert.notNull(o);
    }

    @Test
    public void testIsTrue() {
        Assert.isTrue(true);
    }

    @Test
    public void testIsTrueWithStringMessage() {
        try {
            Assert.isTrue(false, "Expected true");
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Expected true", e.getMessage());
        }
    }

    @Test
    public void testNotEmptyCollection() {
        Collection<Object> c = new ArrayList<Object>();
        c.add(new Object());

        Assert.notEmpty(c);
    }

    @Test
    public void testNotEmptyCollectionWithStringMessage() {
        try {
            Assert.notEmpty(Collections.EMPTY_LIST, "Expected non-empty");
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Expected non-empty", e.getMessage());
        }
    }

    @Test
    public void testIsInstanceOfWithStringMessage() {
        try {
            Assert.isInstanceOf(String.class, 5, "Expected assignable type");
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Expected assignable type", e.getMessage());
        }
    }

}
